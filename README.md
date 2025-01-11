# InstantAI API

# Local Development
## Run a jupyter by docker
```bash
docker run -d \
    -p 8888:8888 \
    -e GRANT_SUDO=yes \
    quay.io/jupyter/base-notebook:x86_64-ubuntu-24.04 \
    start-notebook.py --NotebookApp.token='' --NotebookApp.allow_origin='*' --NotebookApp.base_url=/notebooks/test
```
[User-related configurations](https://jupyter-docker-stacks.readthedocs.io/en/latest/using/common.html)

## Run Server
## Test Api

## Refer
[spring-cloud-gateway-dynamic-routes-from-database](https://medium.com/bliblidotcom-techblog/spring-cloud-gateway-dynamic-routes-from-database-dc938c6665de)
[spring-boot-r2dbc-postgresql](https://www.bezkoder.com/spring-boot-r2dbc-postgresql/)