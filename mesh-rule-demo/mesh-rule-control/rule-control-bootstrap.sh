#!/bin/bash

echo "
#######################################

rule-control bootstrap begin

#######################################
"

mvn clean install && docker build --no-cache -t mesh-rule-control .

docker run --net dubbo-route-demo-bridge --ip 172.19.0.10 -p 8190:8080 --name rule-control -it -d --rm mesh-rule-control

echo "
#######################################

rule-control finish

#######################################
"