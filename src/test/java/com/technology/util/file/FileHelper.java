package com.technology.util.file;

import com.technology.util.constant.FileConstant;
import com.technology.util.string.Strings;

import java.io.File;

/**
 * 文件帮助类,专门用于交互其他类
 * 得到 文件
 * 加了\就是盘符 F:/ 没加就是工程目录下 F:/idea
 */
public class FileHelper {

    private FileHelper() {
    }


    /**
     * 获取 test目录下面的java文件
     * 默认是maven目录结构
     *
     * 未完成:不知道怎么识别出 在main下面 还是在src文件下面 该java文件
     * @param className 文件全类名
     * @return
     */
    public static File getJavaFile(String className) {
        return new File(String.format("%s%s%s%s",FileConstant.USER_DECTORY,FileConstant.JAVA_POSTION_PREFIX,
                Strings.directory(className), ".java"));
    }

    /**
     * 获取 test目录下面的class文件
     *
     * @param className 文件全类名
     * @return
     */
    public static File getClassFile(String className) {
        return new File(String.format("%s%s",FileHelper.class.getResource(FileConstant.FILE_SEPARATOR).getPath(),  //获取当前字节码生成的跟目录
                Strings.directory(className), ".class"));
    }


    public static void main(String[] args) {
        File testJavaFile = getJavaFile(FileHelper.class.getName());
        System.out.println( testJavaFile.getAbsolutePath());
        System.out.println(testJavaFile.exists());
    }

    private static void test1() {
        //        File testJavaFile = FileHelper.getTestJavaFile(FileHelper.class.getName());
//        File testClassFile = FileHelper.getTestClassFile(FileHelper.class.getName());
//        System.out.println(testJavaFile.exists());
//        System.out.println(testJavaFile.getAbsolutePath());
//        System.out.println(testClassFile.exists());
//        System.out.println(testClassFile.getAbsolutePath());
//        FileHelper.class.getResource("");
//        Properties properties = System.getProperties();
//        Set<String> set = properties.stringPropertyNames();
//        Iterator<String> iterator = set.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next()+"======>"+properties.getProperty(iterator.next()));
//        }
////        Map<String, String> map = System.getenv();
////        Set<Map.Entry<String, String>> entries = map.entrySet();
////        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
////        while(iterator.hasNext()){
////            Map.Entry<String, String> entry = iterator.next();
////            System.out.println(entry.getKey()+"=====>"+entry.getValue());
////        }
//        //获取文件信息
//        File file = new File("/");
//        System.out.println(file.getAbsolutePath());
//        String path = FileHelper.class.getResource("/").getPath();
//        System.out.println(path);
    }


}
