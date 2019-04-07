package com.technology.util.ref;

import com.technology.util.gen.GenTest;
import com.technology.util.gen.StringData;
import com.technology.util.print.Prints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 反射工具类
 */
public class Refs {

    private static final String CLASS_FULLNAME = Refs.class.getName();
    private static Logger LOG = LoggerFactory.getLogger(CLASS_FULLNAME);
    private static final Random random = new Random();

    private Refs() {
    }

    public static List<FieldEntity> getClassFields(Object obj) {
        if (obj == null) {
            LOG.error("{}---{}.getClassFields---{}", LocalDateTime.now(), CLASS_FULLNAME, "obj is not null");
            return null;
        }
        Class clz = obj.getClass();
        List<FieldEntity> fieldsList = new ArrayList<>();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            FieldEntity fieldEntity = new FieldEntity();
            field.setAccessible(true);
            fieldEntity.setFieldName(field.getName());
            fieldEntity.setFieldType(field.getType().getSimpleName());
            fieldEntity.setField(field);
            try {
                fieldEntity.setFieldVaule(field.get(obj));
            } catch (IllegalAccessException e) {
                LOG.error("{}---{}.getClassFields---{}:{}", LocalDateTime.now(), CLASS_FULLNAME, "IllegalAccessException", e.getMessage());
            }
            fieldsList.add(fieldEntity);
        }
        return fieldsList;
    }


    public static final class FieldEntity {
        private String fieldName;
        private String fieldType;
        private Object fieldVaule;
        private Field field;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldType() {
            return fieldType;
        }

        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }

        public Object getFieldVaule() {
            return fieldVaule;
        }

        public void setFieldVaule(Object fieldVaule) {
            this.fieldVaule = fieldVaule;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }

        /**
         * 返回值为0 基本类型
         * 返回值为1  string类型
         * 返回值为2  java其他引用类型
         * 返回值为3 自定义引用类型
         * @return
         */
        public  Integer getState(){
            if("Byte".equalsIgnoreCase(fieldType)||"Short".equalsIgnoreCase(fieldType) || "Integer".equalsIgnoreCase(fieldType)
            ||"Long".equalsIgnoreCase(fieldType)||"Float".equalsIgnoreCase(fieldType)||"Double".equalsIgnoreCase(fieldType)
            ||"Boolean".equalsIgnoreCase(fieldType)||"Char".equalsIgnoreCase(fieldType) || "Character".equalsIgnoreCase(fieldType)){
                return 0;   //基本引用类型或者包装类型
            }else if("String".equalsIgnoreCase(fieldType)){
                return  1; //string类型
            }
            else if(field.getType().getName().contains("java")){
                return  2;   //java引用类型,
            }else {
                return  3;  //自定义引用类型
            }
        }



    }


    /**
     *  主要用于测试数据的生成
     * @param fieldEntity
     * @param obj
     */
    public static void setGenValue(FieldEntity fieldEntity, Object obj) { //为一个对象的域 动态赋值
        Integer idFlag = random.nextInt(100); //随机100以内的数字
        try {
            String fieldType = fieldEntity.getFieldType();
            Field field = fieldEntity.getField();
            if ("Byte".equalsIgnoreCase(fieldType)) {
                byte val = idFlag.byteValue();
                field.set(obj, val);
            } else if ("Short".equalsIgnoreCase(fieldType) || "Integer".equalsIgnoreCase(fieldType)) {
                field.set(obj, idFlag);
            } else if ("Long".equalsIgnoreCase(fieldType)) {
                long val = idFlag.longValue();
                field.set(obj, val);
            } else if ("Float".equalsIgnoreCase(fieldType)) {
                float val = idFlag.floatValue();
                field.set(obj, val);
            } else if ("Double".equalsIgnoreCase(fieldType)) {
                double val = idFlag.doubleValue();
                field.set(obj, val);
            } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                Boolean val = true;
                field.set(obj, val);
            } else if ("Char".equalsIgnoreCase(fieldType) || "Character".equalsIgnoreCase(fieldType)) {
                char val = Character.highSurrogate(idFlag);
                field.set(obj, val);
            } else if ("String".equalsIgnoreCase(fieldType)) {
                String fieldName = fieldEntity.getFieldName();
                String val = String.format("%s", StringData.intellGen(fieldName));
                field.set(obj, val);
            } else {  //这里就不是基本类型,是引用类型
                String className = field.getType().getName();  //得到该引用类型的值
                Class<?> clz = Class.forName(className);
                if (clz != null) {
                    Constructor<?> declaredConstructor = clz.getDeclaredConstructor();
                    if (declaredConstructor != null) {
                        Object o = declaredConstructor.newInstance();
                        if (!clz.getName().contains("java")) { //不是java里面的包
                            List<FieldEntity> fieldEntities = getClassFields(o);
                            if (fieldEntities.size() > 0) {
                                fieldEntities.forEach(item -> {
                                    setGenValue(item, o); //将这个引用对象在进行初始化
                                });
                            }
                        }
                        field.set(obj, o); //设置对象
                    }
                }
            }
        } catch (IllegalAccessException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }


    public static class Test {
        private String name;
        private Integer age;
        private  String address;
        private  String assd;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Test() {
        }

        public Test(String name) {
            this.name = name;
        }

        public Test(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        Object obj = GenTest.genNewObjData(new Test());
        Prints.printObj(obj);
    }


}
