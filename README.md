# dubbo-route-demo
dubbo3 服务治理demo

## mesh-rule-demo
triple | docker | mesh-route | 统一路由配置 | 同机房路由demo

* 环境准备
docker & jdk1.8 & maven & git

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

2. start zookeeper
```sh
sh zookeeper-bootstrap.sh
```

3. start provider
```sh
cd mesh-rule-provider && sh provider-bootstrap.sh && cd ..
```

4. start consumer (consumer前台运行，以便观察控制台日志输出)
```sh
cd mesh-rule-consumer && sh consumer-bootstrap.sh && cd ..
```

5. 修改consumer请求attachment观察consumer控制台日志返回路由变化
```sh
curl -X POST 127.0.0.1:8089/mesh-rule-demo/consumer/context?entity=TBNZ&idc=n1

curl -X POST 127.0.0.1:8089/mesh-rule-demo/consumer/context?entity=TBNZ&idc=n2

curl -X POST 127.0.0.1:8089/mesh-rule-demo/consumer/context?entity=TBSG&idc=s1
```

6. 停止服务
```sh
sh stop-all.sh
```

#### 说明
> * entity代表牌照信息: TBNZ TBSG
> * idc代表机房信息: n1 n2 s1
> * 路由策略见 /dubbo-route-demo/mesh-rule-demo/mesh-rule-provider/src/main/resources/dubbo-routers-mesh-rule.yml （路由策略生命周期是独立与服务的，不该放到provider里，这里单纯是因为方便demo执行）
> * consumer请求attachment中写入entity与idc信息，根据此信息选择路由策略并执行请求

![image](https://github.com/wangyy2113/dubbo-route-demo/blob/main/mesh-rule-demo/mesh-rule-demo.png)
