FROM openjdk:8-jdk-oracle
RUN mkdir /app
ENV SERVER_PORT=8080 \
    JVM_GC="-XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=92 -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=3" \
    JVM_OPTS="-Xss1M -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=256M -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=5 -XX:PretenureSizeThreshold=1M -Xloggc:/logs/grpn-demo/gc/gc-%t.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/logs/grpn-demo/jvm/jvm.dump -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCCause -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"
COPY target/grpn-demo.jar /app/app.jar
EXPOSE $SERVER_PORT
ENTRYPOINT ["sh","-c","java -Djava.security.egd=file:/dev/./urandom ${JVM_XMS} ${JVM_XMX} ${JVM_XMN} ${JVM_OPTS} ${JVM_GC} -jar /app/app.jar"]