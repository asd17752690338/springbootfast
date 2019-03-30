package com.technology.util.java;

import com.technology.springboot.hotDeploy.demo3.Test;
import com.technology.util.constant.FileConstant;
import com.technology.util.file.FileHelper;
import com.technology.util.string.Strings;

import java.io.FileDescriptor;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *
 */
public class Compiler {


    private  Compiler(){}

    /**
     * 重新生成class字节码
     * -d 生成的目录
     * @param clz
     */
    public  static  boolean  genClass(Class clz){
        Runtime runtime = Runtime.getRuntime();
        try {
            String classPos = clz.getResource("").getPath().replace("/", FileConstant.FILE_SEPARATOR)
                    .replace(Strings.directory(clz.getPackage().getName()), "");
            String exec = String.format("javac -d %s %s", classPos,
                    FileHelper.getJavaPos(clz));
            runtime.exec(exec);
            System.out.printf("%s  %s  %s\n", LocalDateTime.now(),clz.getName(),"重新编译成功");
            return  true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  false;
    }


    public static void main(String[] args){
        genClass(Test.class);
    }

}
