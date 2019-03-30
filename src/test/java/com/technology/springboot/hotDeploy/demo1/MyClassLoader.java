package com.technology.springboot.hotDeploy.demo1;

import java.io.IOException;
import java.io.InputStream;
/**
 * 自定义类加载器，并override findClass方法
 *
 * name.substring(name.lastIndexOf("." )+1) 截取你传入过来的全类名的字节码
 * getResourceAsStream 从跟路径项目下 加载该文件的资源 这里只用传文件名
 * defineClass  生成该类的数据 用全类名
 */
public class MyClassLoader extends ClassLoader{
     @Override
     public Class<?> findClass(String name) throws ClassNotFoundException{
            try{
                String fileName = name.substring(name.lastIndexOf("." )+1) + ".class" ;
                InputStream is = this.getClass().getResourceAsStream(fileName);
                 byte[] b = new byte[is.available()]; //available 可得到的
                is.read(b);
                 return defineClass(name, b, 0, b. length);
           } catch(IOException e){
                 throw new ClassNotFoundException(name);
           }
     }
}