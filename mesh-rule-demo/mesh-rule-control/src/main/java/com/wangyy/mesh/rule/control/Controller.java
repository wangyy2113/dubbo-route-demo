package com.wangyy.mesh.rule.control;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mesh-rule-control")
public class Controller {

    @PostMapping("/rule/reload")
    public Object reload() {
        RuleUtil.initClient();
        RuleUtil.generateRule();
        return "mesh-rule conf reload";
    }
}
