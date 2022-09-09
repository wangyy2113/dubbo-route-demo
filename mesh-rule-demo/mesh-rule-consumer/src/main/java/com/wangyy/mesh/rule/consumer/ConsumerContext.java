package com.wangyy.mesh.rule.consumer;

public class ConsumerContext {

    private static volatile String ENTITY = "TBNZ";

    private static volatile String IDC = "n1";

    public static String getENTITY() {
        return ENTITY;
    }

    public static String getIDC() {
        return IDC;
    }

    public static void setENTITY(String entity) {
        ENTITY = entity;
    }

    public static void setIDC(String idc) {
        IDC = idc;
    }


}
