package com.technology.springboot.hotDeploy.demo3;

import com.technology.util.file.FileHelper;
import com.technology.util.file.FileListen;
import com.technology.util.string.Strings;

import java.io.File;
import java.lang.reflect.Method;

/**
 * 试验失败, 因为字节码没有重写生成
 */
public class HotDeployApp {
    static String className = Test.class.getName();

    public static void main(String[] args) {
        run();
//        FileListen.listenFile(FileHelper.getTestJavaFile(className)
//                , () -> {
//                    //重新生成一下,在进行
//                    File testClassFile = FileHelper.getTestClassFile(className);
//
//                });

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
    public void scannerAndRun(String className) {
        if (!Strings.isNullorEmpty(className)) {
            try {
                Class<?> resClass = Class.forName(className);
                Method[] methods = resClass.getDeclaredMethods();
                Object obj = resClass.newInstance();
                for (int i = 0; i < methods.length; i++) {
                    Method method = methods[i];
                    method.invoke(obj, null);  //调用运行该class里面的方法
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
