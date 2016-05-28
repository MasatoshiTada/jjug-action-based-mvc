#!/usr/bin/env bash
mvn clean package -Pswarm -Dmaven.test.skip=true
open -a Safari http://localhost:8080/jjug-resteasy-html-1.0-SNAPSHOT/
java -jar target/jjug-resteasy-html-1.0-SNAPSHOT-swarm.jar