#!/usr/bin/env bash
mvn clean package
java -jar ~/Java/ap-server/payara-micro-4.1.1.162.jar --deploy target/jjug-my-mvc-1.0-SNAPSHOT.war --outputUberJar target/jjug-my-mvc.jar
open -a Safari http://localhost:8080/jjug-my-mvc-1.0-SNAPSHOT/
java -jar target/jjug-my-mvc.jar