FROM openjdk:17-jdk-slim as build

WORKDIR /app

# 将gradle wrapper和源代码复制到容器中
COPY gradle gradle
COPY gradlew .
COPY gradlew.bat .
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

RUN chmod +x gradlew

RUN ./gradlew build -x test

FROM openjdk:17-slim

WORKDIR /app
COPY wildcard.instant.ai.crt /usr/local/share/ca-certificates/
RUN update-ca-certificates
RUN keytool -importcert -alias mycert -file /usr/local/share/ca-certificates/wildcard.instant.ai.crt -keystore /usr/local/openjdk-17/lib/security/cacerts -storepass changeit -noprompt

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]