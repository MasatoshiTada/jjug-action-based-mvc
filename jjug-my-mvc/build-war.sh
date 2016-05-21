#!/usr/bin/env bash
export GF_HOME=~/Java/ap-server/payara-web-ml-4.1.1.162
mvn clean package
$GF_HOME/bin/asadmin start-database
$GF_HOME/bin/asadmin start-domain domain1
$GF_HOME/bin/asadmin undeploy jjug-my-mvc
$GF_HOME/bin/asadmin deploy --contextroot=jjug-my-mvc --name=jjug-my-mvc target/jjug-my-mvc-1.0-SNAPSHOT.war
open -a Safari http://localhost:8080/jjug-my-mvc/
tail -f $GF_HOME/glassfish/domains/domain1/logs/server.log
