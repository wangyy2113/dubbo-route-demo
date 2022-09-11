package com.wangyy.mesh.rule.control;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MeshRuleControlApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MeshRuleControlApplication.class).run(args);

        initMeshRule();
    }

    private static void initMeshRule() {
        RuleUtil.initClient();
        RuleUtil.generateRule();

        System.out.println("mesh-rule init");
    }

}
