# dubbo-route-demo
dubbo3 服务治理demo

## mesh-rule-demo
triple | docker | spring-boot | mesh-route | 统一路由配置 | 同机房路由demo

* 环境准备
docker & jdk1.8 & maven & git

#### 说明
> * *路由策略*见 /dubbo-route-demo/mesh-rule-demo/mesh-rule-control/src/main/resources/dubbo-routers-mesh-rule.yml (相同机房优先路由, fallback牌照路由)
> * entity代表牌照信息: TBNZ TBSG
> * idc代表机房信息: n1 n2 s1
> * consumer请求attachment中写入entity与idc信息，根据此信息选择路由策略并执行请求

![image](https://github.com/wangyy2113/dubbo-route-demo/blob/main/mesh-rule-demo/mesh-rule-demo.png)


#### 运行

1. clone
```sh
git clone https://github.com/wangyy2113/dubbo-route-demo.git
cd dubbo-route-demo/mesh-rule-demo
```

2. 提前预置 docker network bridge
```sh
docker network create --subnet=172.19.0.0/16 dubbo-route-demo-bridge
```

3. start zookeeper
```sh
sh zookeeper-bootstrap.sh
```

4. init mesh-rule (路由规则初始化，将路由规则上传config-center)
```sh
cd mesh-rule-control && sh rule-control-bootstrap.sh && cd ..
```

5. start provider
```sh
cd mesh-rule-provider && sh provider-bootstrap.sh && cd ..
```

6. start consumer (consumer前台运行，以便观察控制台日志输出，consumer初始默认entity=TBNZ idc=n1)
```sh
cd mesh-rule-consumer && sh consumer-bootstrap.sh && cd ..
```

7. 修改consumer请求attachment(entity & idc)观察consumer控制台日志返回路由变化; 期间也可以停掉部分provider，观察consumer请求路由变化
```sh
curl 127.0.0.1:8089/mesh-rule-demo/consumer/context -X POST -d 'entity=TBNZ&idc=n1'

curl 127.0.0.1:8089/mesh-rule-demo/consumer/context -X POST -d 'entity=TBNZ&idc=n2'

curl 127.0.0.1:8089/mesh-rule-demo/consumer/context -X POST -d 'entity=TBSG&idc=s1'
```

8. 停止服务
```sh
sh stop-all.sh
```