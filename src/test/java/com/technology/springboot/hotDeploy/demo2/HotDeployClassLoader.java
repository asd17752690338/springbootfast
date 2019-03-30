package com.technology.springboot.hotDeploy.demo2;

import java.io.InputStream;

/**
 * 热部署加载类
 *
 * 可以实现任意目录下的监控热部署
 *
 */
public class HotDeployClassLoader extends  ClassLoader{

    /**
     *
     * @param name  name为全类名
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        String className  = String.format("%s%s",name.substring(name.lastIndexOf(".")+1),".class"); //获取该类的字节码
        byte[] data = Readers.getBytes(className);  //获取该类路径下资源的 2进制数据
        return   defineClass(name,data,0,data.length);
    }






}
