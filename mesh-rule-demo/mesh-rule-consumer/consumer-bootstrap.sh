#!/bin/bash

echo "
#######################################

consuemr bootstrap begin

#######################################
"

mvn clean install && docker build --no-cache -t mesh-rule-consuemr .

# consumer前台运行，以便观察控制台日志输出
docker run --net dubbo-route-demo-bridge --ip 172.19.0.9 -p 8089:8080 --name consumer -it --rm mesh-rule-consuemr

echo "
#######################################

consuemr finish

#######################################
"