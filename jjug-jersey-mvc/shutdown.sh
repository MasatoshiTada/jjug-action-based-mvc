#!/usr/bin/env bash
export GF_HOME=~/Java/ap-server/payara-web-ml-161.1
$GF_HOME/bin/asadmin undeploy jjug-mvc10
$GF_HOME/bin/asadmin stop-domain domain1
