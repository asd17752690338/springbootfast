package com.technology.springboot.hotDeploy.demo5;

import java.io.File;

/**
 * 需求 代码 该怎么写 还怎么写 就是要明白java程序是怎么运行的
 * 明白java每次运行的时候 jvm执行的是时候 代码 怎么改
 */
public class App {

    public static void main(String[] args) {
        HotDeploy hotDeploy = new SimpleHotDeployImpl();
        hotDeploy.addMonitor(new File("F:\\idea\\src\\test\\java\\com\\technology\\springboot\\hotDeploy\\demo5"));


    }
}
