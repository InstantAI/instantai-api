# InstantAI API

# Local Development
## Run Server
### build image
```bash
docker build -t instantai/api:<version> .
```
### update config
修改配置文件 `config/application.yml`
### 更新配置
```bash
helm -n cloud upgrade instantai-api chart/instantai-api --set-file config=config/application.yml
```
## Test Api
### Create or update notebook
```bash
curl --location 'localhost:8080/api/notebooks/default' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <jwt token>' \
--data '{
    "image": "kubeflownotebookswg/jupyter-scipy:v1.10.0-rc.1",
    "name": "demo",
    "ports": [
        {
            "containerPort": 8888,
            "name": "notebook-port",
            "protocol": "TCP"
        }
    ],
    "resources": {
        "limits": {
            "cpu": "1",
            "memory": "2Gi"
        },
        "requests": {
            "cpu": "10m",
            "memory": "100Mi"
        }
    }
}'
```
### Delete notebook
```bash
curl --location --request DELETE 'localhost:8080/api/notebooks/default?name=demo' \
--header 'Authorization: Bearer <jwt token>'
```
## Refer
[spring-cloud-gateway-dynamic-routes-from-database](https://medium.com/bliblidotcom-techblog/spring-cloud-gateway-dynamic-routes-from-database-dc938c6665de)
[spring-boot-r2dbc-postgresql](https://www.bezkoder.com/spring-boot-r2dbc-postgresql/)