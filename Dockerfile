FROM nexus.spimex.pdd:8484/jre-17

ARG TARGET

WORKDIR /app

COPY start.sh /app/start.sh
RUN ["chmod", "+x", "/app/start.sh"]

ADD target/*.jar /app/

EXPOSE 7474

ENTRYPOINT ["/app/start.sh"]
