package com.technology.util.file;

import com.technology.springboot.hotDeploy.demo3.Test;
import com.technology.util.string.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * file 工具类
 */
public class Files {

    private Logger LOG = LoggerFactory.getLogger(Files.class.getName());


    private Files() {
    }



    /**
     * 递归找出目录下的所有文件
     * @param directoryName
     * @return
     */
    public static List<File> findFiles(String directoryName) {
        if (!Strings.isNullorEmpty(directoryName)) {
            System.err.printf("%s  %s----->%s  %s", LocalDateTime.now(), Files.class.getName(),
                    "findFiles", "目录名为空,结束程序");
            return null;
        }
        List<File> list = new LinkedList<>();
        recursiveFindFile(directoryName,list);
        return list;
    }


    /**
     * 递归找出目录的所有文件
     * @param directoryName
     * @param list
     */
    private static void recursiveFindFile(String directoryName, List<File> list) {
        File file = new File(directoryName);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                //getAbsolutePath 获取文件的绝对路径
                recursiveFindFile(files[i].getAbsolutePath(), list); //继续找
            }
        } else {  //是一个文件
            list.add(file);
        }
    }






    public static void main(String[] args) {
//        test1();
        //根据全类名来获取file 可以用/ 来代替\\
//        String name = Test.class.getName();
//        File file = new File(name);
//        System.out.println(file);
//        System.out.println(file.exists());
    }

    private static void test1() {
        String directoryName="F:\\javacode";
        List<File> list = new LinkedList<>();
        recursiveFindFile(directoryName,list);
        list.forEach(v->{
            System.out.println(v.getName());
        });
    }


}
