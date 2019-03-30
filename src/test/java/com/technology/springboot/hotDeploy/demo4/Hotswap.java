package com.technology.springboot.hotDeploy.demo4;

import com.technology.util.file.FileHelper;
import com.technology.util.file.FileListen;
import com.technology.util.java.Compiler;

import java.lang.reflect.Method;

/**
 * 1.file的文件目录默认是 项目根路径      如我的项目根路径是 F:\idea   [F:\idea\HelloWorld.class]
 * 2. fileV2.renameTo(fileV1)  会将fileV2 里面的文件 移动到fileV1 这个绝对路径的地方去,并且删除fileV2里面的内容
 */
public class Hotswap {

    public static void main(String[] args) {
        FileListen.listenFile(FileHelper.getJavaFile(HelloWorld.class)
                , () -> {
                    // 这里等待几秒 cpu会去最新的结果 而不是去取缓存
                    Compiler.genClass(HelloWorld.class);
                    try {
                        Thread.sleep(1700);   //编译生成一个文件 等待时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    loadHelloWorld();
                });
    }



    public static void loadHelloWorld() {
        MyClassLoader myLoader = new MyClassLoader(); //自定义类加载器
        Object obj1 = null; //生成新的对象
        try {
            Class<?> class1 = myLoader
                    .findClass(HelloWorld.class.getName());//类实例
            obj1 = class1.newInstance();
            Method method = class1.getMethod("say");
            method.invoke(obj1);//执行方法say

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}