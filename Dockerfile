FROM java:8
VOLUME /tmp
ADD target/tank-0.0.1-SNAPSHOT.jar TankGame.jar
RUN bash -c 'touch /TankGame.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /TankGame.jar"]