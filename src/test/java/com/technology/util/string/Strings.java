package com.technology.util.string;

import com.technology.springboot.hotDeploy.demo4.HelloWorld;
import com.technology.util.constant.FileConstant;
import com.technology.util.file.FileHelper;

import java.io.File;

/**
 * string工具类
 */
public class Strings {

    private Strings() {
    }

    public static boolean isNullorEmpty(String str) {
        return str == null && str.trim().equals("");
    }

    /**
     * 将一个 全类名 转换为一个目录
     *
     * @param className
     * @return
     */
    public static String directory(String className) {
        return isNullorEmpty(className)?null:className.replace(".", FileConstant.FILE_SEPARATOR);
    }









    public static void main(String[] args) {
        String name = Strings.class.getName();
        System.out.println(directory(name));
        System.out.println(new File(directory(name)).exists());
//        String fileJavaHome = FileConstant.TEST_JAVA_PREFIX+directory(name)+".java";
//        System.out.println(new File(fileJavaHome).exists());

        boolean delete = FileHelper.getClassFile(HelloWorld.class).delete();
        System.out.println(delete);
    }


}
