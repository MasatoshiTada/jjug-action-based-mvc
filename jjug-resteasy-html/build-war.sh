#!/usr/bin/env bash
export AS_HOME=~/Java/ap-server/wildfly-10.0.0.Final/
mvn clean package -Dmaven.test.skip=true
rm -rf $AS_HOME/standalone/deployments/jjug-*
cp target/jjug-resteasy-html-1.0-SNAPSHOT.war $AS_HOME/standalone/deployments
open -a Safari http://localhost:8080/jjug-resteasy-html-1.0-SNAPSHOT/
$AS_HOME/bin/standalone.sh