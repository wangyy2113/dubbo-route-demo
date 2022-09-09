package com.wangyy.mesh.rule.provider;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:${DUBBO_PROVIDER_CONF_F}")
public class MeshRuleProviderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MeshRuleProviderApplication.class).run(args);
        System.out.println("dubbo provider start");
    }
}
