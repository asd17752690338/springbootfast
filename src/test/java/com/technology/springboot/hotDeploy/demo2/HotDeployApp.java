package com.technology.springboot.hotDeploy.demo2;

import com.technology.springboot.hotDeploy.demo3.Test;
import com.technology.util.file.FileListen;
import com.technology.util.string.Strings;

import java.lang.reflect.Method;

public class HotDeployApp {
    static  String className = Test.class.getName();

    public static void main(String[] args) {
//        run();
        FileListen.listenFile("src\\test\\java\\com\\technology\\springboot\\hotDeploy\\demo2\\Test.java"
        ,()->{
                    run();
                });

    }

    private static void run() {
        HotDeployApp hotDeployApp = new HotDeployApp();
        hotDeployApp.scannerAndRun(className);
        hotDeployApp = null;
        System.gc();
    }

    /**
     * @param className
     */
    public  void scannerAndRun(String className) {
        if (!Strings.isNullorEmpty(className)) {
            HotDeployClassLoader hotDeployClassLoader = new HotDeployClassLoader();
            try {
                Class<?> resClass = hotDeployClassLoader.findClass(className);
                Method[] methods = resClass.getDeclaredMethods();
                Object obj = resClass.newInstance();
                for (int i = 0; i < methods.length; i++) {
                    Method method = methods[i];
                    method.invoke(obj, null);  //调用运行该class里面的方法
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }finally {
                hotDeployClassLoader = null; //情况上一次的classLoader
            }
        }
    }

    /**
     * 提醒gc清理垃圾
     */
    private static  void remindClearMem(){

    }


}
