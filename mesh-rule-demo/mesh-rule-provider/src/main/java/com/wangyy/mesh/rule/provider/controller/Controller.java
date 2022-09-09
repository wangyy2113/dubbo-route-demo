package com.wangyy.mesh.rule.provider.controller;

import com.wangyy.mesh.rule.provider.utils.RuleUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mesh-rule-demo/provider")
public class Controller {

    @GetMapping("/ping")
    public Object ping() {
        return "pong";
    }

    @PostMapping("/conf/rule")
    public Object rule() {
        RuleUtil.initClient();
        RuleUtil.generateRule();
        return "mesh-rule conf init";
    }
}
