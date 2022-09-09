#!/bin/bash

echo "
#######################################

provider bootstrap begin

#######################################
"

cd ..
mvn clean install

cd mesh-rule-provider
docker build --no-cache -t mesh-rule-provider .

echo "----------provider-server begin start------------"
#TBNZ下n1机房 1台实例
docker run --net dubbo-route-demo-bridge --ip 172.19.0.3 -e DUBBO_PROVIDER_CONF_F=mesh-rule-provider-nz-n1-1.xml -e DUBBO_IP_TO_REGISTRY=172.19.0.3 -p 20881:20880 -p 8081:8080 --name TBNZ-n1-1 -it -d --rm mesh-rule-provider
#TBNZ下n2机房 2台实例
docker run --net dubbo-route-demo-bridge --ip 172.19.0.4 -e DUBBO_PROVIDER_CONF_F=mesh-rule-provider-nz-n2-1.xml -e DUBBO_IP_TO_REGISTRY=172.19.0.4 -p 20882:20880 -p 8082:8080 --name TBNZ-n2-1 -it -d --rm mesh-rule-provider
docker run --net dubbo-route-demo-bridge --ip 172.19.0.5 -e DUBBO_PROVIDER_CONF_F=mesh-rule-provider-nz-n2-2.xml -e DUBBO_IP_TO_REGISTRY=172.19.0.5 -p 20883:20880 -p 8083:8080 --name TBNZ-n2-2 -it -d --rm mesh-rule-provider
#TBSG下s1机房 1台实例
docker run --net dubbo-route-demo-bridge --ip 172.19.0.6 -e DUBBO_PROVIDER_CONF_F=mesh-rule-provider-sg-s1-1.xml -e DUBBO_IP_TO_REGISTRY=172.19.0.6 -p 20884:20880 -p 8084:8080 --name TBSG-s1-1 -it -d --rm mesh-rule-provider

sleep 30
echo "-------------------------------------------------"

echo "
----------inital rule conf begin------------"
## 请求任意实例初始化mesh-rule(非正常主流方式，这里只是为了简单)，上报至配置中心
curl 127.0.0.1:8081/mesh-rule-demo/provider/conf/rule -X POST
echo "
----------inital rule conf end--------------"

echo "
#######################################

provider bootstrap finish

#######################################
"