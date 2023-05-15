FROM openjdk:19-jdk-alpine
LABEL authors="Lockegogo"

# 将应用的 jar 包复制到容器中并更名为 app.jar
COPY target/springboot-03-web-0.0.1-SNAPSHOT.jar app.jar

# 暴露 8080 端口
EXPOSE 8080

# 启动命令
ENTRYPOINT ["java", "-jar", "app.jar"]