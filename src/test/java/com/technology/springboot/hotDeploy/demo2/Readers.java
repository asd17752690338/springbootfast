package com.technology.springboot.hotDeploy.demo2;


import java.io.IOException;
import java.io.InputStream;

/**
 * 读取工具类
 */
public class Readers {

    private Readers() {
    }

    public static byte[] getBytes(String className) {
        InputStream is =
                Readers.class.getResourceAsStream(className);
        byte[] data = null;
        try {
            data = new byte[is.available()];
            is.read(data, 0, is.available());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null!=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        System.out.printf("[byte[]%s=====]\n",data);
        return data;
    }


}
