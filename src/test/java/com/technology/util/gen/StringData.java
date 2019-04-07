package com.technology.util.gen;

import com.technology.util.constant.FileConstant;
import com.technology.util.string.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

public class StringData {

    private  static  final Logger LOG = LoggerFactory.getLogger(StringData.class.getName());

    private StringData() {
    }

    public static final String RESOUCES_POS = String.format("%s%s%s%s", FileConstant.USER_DECTORY, FileConstant.JAVA_POSTION_PREFIX,
            Strings.directory("com.data"), FileConstant.FILE_SEPARATOR);

    public static final Map<String, List<String>> ioCache = new HashMap<>();

    public static final Random random = new Random();



    /**
     * 根据字段名智能生成 数据,文件路径默认是当前文件的txt文件作为数据源,随机取出 以逗号分隔
     * 如 字段名为name  会在当前同路径找到name.txt文件 从中随机取出一个名字为返回值,如果没有找到该文件会返回该字段名
     *
     * @param fieldName
     * @return
     */
    public static final String intellGen(String fieldName) {
        List<String> datas = ioCache.get(fieldName);
        if (datas != null && datas.size() > 0) {
            return datas.get(random.nextInt(datas.size()));
        } else {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(String.format("%s%s%s", RESOUCES_POS, fieldName, ".txt"))));
                String line = null;
                datas = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    String[] strs = line.split(",");
                    if (strs != null) {
                        for (int i = 0; i < strs.length; i++) {
                            String str = strs[i];
                            datas.add(str);
                        }
                    }
                }
                if(datas.size()>0){  //当有数据时 才取
                    ioCache.put(fieldName, datas);
                    return datas.get(random.nextInt(datas.size()));
                }
               return fieldName;
            } catch (Exception e) {
                LOG.debug("{}",e.getMessage());
                 //当系统找定的字段文件时,返回字段文件
                return  fieldName;
            }
        }

    }


    public static void main(String[] args) throws FileNotFoundException {
//        String name = StringData.intellGen("name");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(String.format("%s%s%s", RESOUCES_POS, "test", ".txt"))));
//        System.out.println(name);
        System.out.println(br);
    }


}
