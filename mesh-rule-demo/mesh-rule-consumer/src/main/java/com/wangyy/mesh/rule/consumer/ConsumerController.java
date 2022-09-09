package com.wangyy.mesh.rule.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mesh-rule-demo/consumer")
public class ConsumerController {

    @GetMapping("/ping")
    public Object ping() {
        return "pong";
    }

    @PostMapping("/context")
    public Object context(String entity, String idc) {
        ConsumerContext.setENTITY(entity);
        ConsumerContext.setIDC(idc);
        return "context [entity:" + entity + " idc:" + idc + "]";
    }
}
