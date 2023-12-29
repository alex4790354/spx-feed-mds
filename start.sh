#!/bin/bash
echo "$LOGBACK_JSON" > /app/logback-json.xml
echo "Starting application"
java $JAVA_ARGS -Dlogging.config=/app/logback-json.xml -jar *.jar
