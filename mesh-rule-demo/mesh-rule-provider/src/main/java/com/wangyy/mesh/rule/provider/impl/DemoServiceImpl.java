package com.wangyy.mesh.rule.provider.impl;

import com.wangyy.mesh.rule.api.DemoService;
import org.apache.dubbo.rpc.RpcContext;

public class DemoServiceImpl implements DemoService {


    public String hello(String str) {
        return  " Hello, " + str + " ......  Provider[" + RpcContext.getServiceContext().getUrl().toFullString() + "]";
    }
}
