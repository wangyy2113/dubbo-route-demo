package com.wangyy.mesh.rule.provider.utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;
import java.io.InputStream;

public class RuleUtil {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "172.19.0.2");
    private static CuratorFramework client;

    private static final String RULE_CONF_NODE_LOCATION = "/dubbo/config/dubbo/mesh-rule-provider.MESHAPPRULE";

    public static void main(String[] args) throws Exception {
        initClient();
        deleteRule();
    }

    public static void initClient() {
        client = CuratorFrameworkFactory.newClient(zookeeperHost + ":2181", 60 * 1000, 60 * 1000,
                new ExponentialBackoffRetry(1000, 3));
        client.start();
    }

    public static void generateRule() {
        try (InputStream yamlStream = RuleUtil.class.getResourceAsStream("/dubbo-routers-mesh-rule.yml")) {
            String path = RULE_CONF_NODE_LOCATION;
            if (client.checkExists().forPath(path) == null) {
                client.create().creatingParentsIfNeeded().forPath(path);
            }
            setData(path, streamToString(yamlStream));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteRule() throws Exception {
        String path = RULE_CONF_NODE_LOCATION;
        if (client.checkExists().forPath(path) == null) {
            client.create().creatingParentsIfNeeded().forPath(path);
        }
        setData(path, "");
    }

    private static String streamToString(InputStream stream) throws IOException {
        byte[] bytes = new byte[stream.available()];
        stream.read(bytes);
        return new String(bytes);
    }

    private static void setData(String path, String data) throws Exception {
        client.setData().forPath(path, data.getBytes());
    }

}
