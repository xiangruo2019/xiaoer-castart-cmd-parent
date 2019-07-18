FROM registry.cn-hangzhou.aliyuncs.com/xiaoer_docker/java:8.77.1
ADD app.jar /app/app.jar
ENV APP_ENV=test
HEALTHCHECK --interval=1m --timeout=3s CMD curl -f http://localhost:8080/health/dialing/monitor || exit 1
WORKDIR /app
CMD [ "java", "-server", "-jar", "app.jar", "--spring.profiles.active=${APP_ENV}", "--node.ip=${NODE.IP}",  "--server.port=8080", "--server.context-path=/", "--server.tomcat.max-threads=1000" ]
