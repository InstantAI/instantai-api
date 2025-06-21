FROM openjdk:17-slim

WORKDIR /app

# 只复制已构建的 JAR 文件
COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]