#!/usr/bin/env bash
export GF_HOME=~/Java/ap-server/payara-web-ml-161.1
mvn clean package
java -jar ~/Java/ap-server/payara-micro-4.1.1.162.jar --deploy target/jjug-jersey-mvc-1.0-SNAPSHOT.war --outputUberJar target/jjug-jersey-mvc.jar
open -a Safari http://localhost:8080/jjug-jersey-mvc-1.0-SNAPSHOT/
java -jar target/jjug-jersey-mvc.jar