package com.wangyy.mesh.rule.consumer;


import com.wangyy.mesh.rule.api.DemoService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class MeshRuleConsumerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MeshRuleConsumerApplication.class).run(args);

        new Thread(MeshRuleConsumerApplication::sayHello).start();
    }

    public static void sayHello() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mesh-rule-consumer.xml");
        context.start();
        DemoService demoService = context.getBean(DemoService.class);
        while (true) {
            try {
                String entity = ConsumerContext.getENTITY();
                if (entity != null) {
                    RpcContext.getServiceContext().setAttachment("entity", entity);
                }
                String idc = ConsumerContext.getIDC();
                if (idc != null) {
                    RpcContext.getServiceContext().setAttachment("idc", idc);
                }
                String request = "Consumer[entity=" + entity + " idc=" + idc + "]";
                String result = demoService.hello(request);
                System.out.println(result);
                sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
