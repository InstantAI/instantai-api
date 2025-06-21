# -------- Build stage --------
FROM openjdk:17-jdk-slim as build

WORKDIR /app

# 使用缓存挂载下载 Gradle 依赖
COPY gradle gradle
COPY gradlew .
COPY gradlew.bat .
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x gradlew

# 缓存 gradle 依赖，加快构建
RUN --mount=type=cache,target=/root/.gradle \
    ./gradlew build -x test

# 复制源码（避免源码变动影响缓存）
COPY src ./src

# 再次构建（缓存已命中则跳过）
RUN --mount=type=cache,target=/root/.gradle \
    ./gradlew build -x test

# -------- Run stage --------
FROM openjdk:17-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]