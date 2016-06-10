#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip=true
# Uber JAR
#java -jar ~/Java/ap-server/payara-micro-4.1.1.162.jar --deploy target/jjug-mvc10-1.0-SNAPSHOT.war --outputUberJar target/jjug-mvc10.jar
open -a Safari http://localhost:8080/jjug-mvc10-1.0-SNAPSHOT/
java -jar ~/Java/ap-server/payara-micro-4.1.1.162.jar --deploy target/jjug-mvc10-1.0-SNAPSHOT.war
#java -jar target/jjug-mvc10.jar