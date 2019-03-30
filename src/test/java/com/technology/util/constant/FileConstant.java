package com.technology.util.constant;

import java.io.File;

/**
 * 文件操作的常量,路径后面自动带有文件分割路径
 *
 *
 */
public interface FileConstant {

    /**
     *  文件分割目录符号
     */
    String FILE_SEPARATOR = File.separator;

    /**
     *  用户编程跟目录
     */
    String USER_DECTORY=String.format("%s%s",System.getProperty("user.dir"),FILE_SEPARATOR);

    /**
     * maven生成的main下面放java的目录结构
     */
    String MAVEN_MAIN_PREFIX=String.format("src%smain%sjava%s",FILE_SEPARATOR,FILE_SEPARATOR,FILE_SEPARATOR);

    /**
     * maven生成的test下面放java的目录结构
     */
    String MAVEN_TEST_PREFIX=String.format("src%stest%sjava%s",FILE_SEPARATOR,FILE_SEPARATOR,FILE_SEPARATOR);

    /**
     *  普通项目下放置java文件的目录
     */
    String SRC_PREFIX = String.format("%s%s","src",FILE_SEPARATOR);

    /**
     *  用于告诉别人java文件 放置在哪个目录下
     *  完善:主要是不知道怎么通过全类名得知java文件的全路径
     */
    enum Position{
        MAVEN_MAIN(MAVEN_MAIN_PREFIX),MAVEN_TEST(MAVEN_TEST_PREFIX),SRC(SRC_PREFIX);
        private String position;
        public String getPosition() {
            return position;
        }
        public void setPosition(String position) {
            this.position = position;
        }
        Position(String position){
            this.position = position;
        }
    }

    /**
     *  放置java文件的位置 默认是maven的test里面的东西
     */
    String JAVA_POSTION_PREFIX = Position.MAVEN_TEST.getPosition();





}
