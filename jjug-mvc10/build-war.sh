#!/usr/bin/env bash
export GF_HOME=~/Java/ap-server/payara-web-ml-4.1.1.162
mvn clean package
$GF_HOME/bin/asadmin start-database
$GF_HOME/bin/asadmin start-domain domain1
$GF_HOME/bin/asadmin undeploy jjug-mvc10
$GF_HOME/bin/asadmin deploy --contextroot=jjug-mvc10 --name=jjug-mvc10 target/jjug-mvc10-1.0-SNAPSHOT.war
open -a Safari http://localhost:8080/jjug-mvc10/
tail -f $GF_HOME/glassfish/domains/domain1/logs/server.log
