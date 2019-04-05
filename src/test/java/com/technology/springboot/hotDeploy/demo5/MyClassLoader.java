package com.technology.springboot.hotDeploy.demo5;

import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends  ClassLoader {



    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException{
        try{
            String fileName = String.format("%s%s",name.substring(name.lastIndexOf("." )+1),".class");
            InputStream is = this.getClass().getResourceAsStream(fileName);
            byte[] b = new byte[is.available()]; //available 可得到的
            is.read(b);
            return defineClass(name, b, 0, b. length);
        } catch(IOException e){
            throw new ClassNotFoundException(name);
        }
    }
}
