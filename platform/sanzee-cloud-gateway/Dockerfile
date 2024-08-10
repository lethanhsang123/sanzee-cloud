FROM eclipse-temurin:8-jre

RUN mkdir -p /sanzee-cloud-gateway
WORKDIR /sanzee-cloud-gateway
COPY ./target/sanzee-cloud-gateway.jar app.jar

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms512m -Xmx512m"

EXPOSE 48080

CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar app.jar