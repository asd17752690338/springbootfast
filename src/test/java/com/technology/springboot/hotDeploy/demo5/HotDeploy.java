package com.technology.springboot.hotDeploy.demo5;

import java.io.File;

/**
 * 热部署 实现接口
 */
public interface HotDeploy {


    /**
     * 添加监控
     * @param destFile 这个目标文件可以是文件 也可以是目录
     */
    void addMonitor(File destFile);

    /**
     * 重新编译文件
     */
    void reCompiler();

    /**
     * 当监控的文件改变时 发生的操作
     */
    void exec();





}
