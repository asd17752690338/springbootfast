package com.technology.springboot.hotDeploy.demo1;

import java.io.File;
import java.lang.reflect.Method;

/**
 *  1.file的文件目录默认是 项目根路径      如我的项目根路径是 F:\idea   [F:\idea\HelloWorld.class]
 * 2. fileV2.renameTo(fileV1)  会将fileV2 里面的文件 移动到fileV1 这个绝对路径的地方去,并且删除fileV2里面的内容
 *
 */
public class Hotswap {
     public static void main(String[] args) throws Exception {
            loadHelloWorld();
//            // 回收资源,释放HelloWorld.class文件，使之可以被替换
           System. gc();
           Thread. sleep(1000);// 等待资源被回收
           File fileV2 = new File( "HelloWorld.class");
//         System.out.println(fileV2.getAbsolutePath());
           File fileV1 = new File(
                      "target\\test-classes\\com\\technology\\springboot\\hotDeploy\\demo1\\HelloWorld.class" );
//         System.out.println(fileV1.getAbsolutePath());
           fileV1.delete(); //删除V1版本
           fileV2.renameTo(fileV1); //更新V2版本 将根目录下的文件内容
           System. out.println( "Update success!");
            loadHelloWorld();
     }

     public static void loadHelloWorld() throws Exception {
           MyClassLoader myLoader = new MyClassLoader(); //自定义类加载器
           Class<?> class1 = myLoader
                     .findClass( "com.technology.springboot.hotDeploy.demo1.HelloWorld");//类实例
           Object obj1 = class1.newInstance(); //生成新的对象
           Method method = class1.getMethod( "say");
           method.invoke(obj1); //执行方法say
           System. out.println(obj1.getClass()); //对象
           System. out.println(obj1.getClass().getClassLoader()); //对象的类加载器
     }
}