#!/bin/bash

echo "
#######################################

zookeeper bootstrap begin

#######################################
"

docker run -e ALLOW_ANONYMOUS_LOGIN=yes --name zkserver --net dubbo-route-demo-bridge --ip 172.19.0.2 -d bitnami/zookeeper:latest

echo "
#######################################

zookeeper bootstrap finish

#######################################
"