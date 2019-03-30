package com.technology.util.java;

import com.technology.springboot.hotDeploy.demo2.HotDeployApp;

import java.io.IOException;

/**
 *
 */
public class Compiler {


    private  Compiler(){}

    public  static  void  genClass(String className){
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("javac -d  F:\\javacode\\test\\2 F:\\javacode\\test\\TestDemo.java");
//            runtime.exec("javac -d 2 -cp F:/javacode/test TestDemo.java");
//            runtime.exec("javac -d F:\\javacode\\test\\2 -classpath  F:\\javacode\\test TestDemo.java");
//            runtime.exec("calc");
            System.out.println("exec  11122");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        genClass(HotDeployApp.class.getName());
    }

}
