#!/usr/bin/env bash
export GF_HOME=~/Java/ap-server/payara-web-ml-4.1.1.162
mvn clean package
$GF_HOME/bin/asadmin start-database
$GF_HOME/bin/asadmin start-domain domain1
$GF_HOME/bin/asadmin undeploy jjug-jersey-mvc
$GF_HOME/bin/asadmin deploy --contextroot=jjug-jersey-mvc --name=jjug-jersey-mvc target/jjug-jersey-mvc-1.0-SNAPSHOT.war
open -a Safari http://localhost:8080/jjug-jersey-mvc/
tail -f $GF_HOME/glassfish/domains/domain1/logs/server.log