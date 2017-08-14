FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD cacerts cacerts
ADD truststore truststore
ADD digital-banking-vccb-service-cif-1.0.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djavax.net.ssl.trustStore=/truststore","-Djavax.net.ssl.trustStorePassword=changeit","-jar","/app.jar"]