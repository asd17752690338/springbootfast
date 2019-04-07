package com.technology.util.print;

import com.technology.bean.User;
import com.technology.util.gen.GenTest;
import com.technology.util.ref.Refs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;

public class Prints {

    private static final String CLASS_FULLNAME = Prints.class.getName();
    private static Logger LOG = LoggerFactory.getLogger(CLASS_FULLNAME);
    private static final StringBuilder printBuilder = new StringBuilder(100);

    private Prints() {
    }


    public static void printObj(Object obj) {
        String printVal = printObj(obj, true, false);//递归扩充参数
        System.out.printf(printVal);
    }

    public static String getObj(Object obj){
        return  printObj(obj, true, false);
    }


    /**
     * @param obj
     * @param isOld  是不是最初的打印对象
     * @param isLast  当不是最初的打印对象才有效 用来标识是不是最后一个打印
     */
    private  static String printObj(Object obj,boolean isOld,boolean isLast){
        if(obj == null){
            return "obj is null";
        }
        List<Refs.FieldEntity> fields = Refs.getClassFields(obj);
        int size = fields.size();
        printBuilder.append(String.format("%s{",obj.getClass().getSimpleName()));
        for(int i = 0;i<size;i++){
            Refs.FieldEntity fieldEntity = fields.get(i);
            Field field = fieldEntity.getField();
            Object fieldVaule = fieldEntity.getFieldVaule();
            if(!field.getType().getName().contains("java")){ //自定义引用类型
                boolean last = false;
                if(i==size-1){
                    last = true; //是最后一个
                }
                String val = printObj(fieldEntity.getFieldVaule(), false, last);//将该引用对象打印出来
                if(i == size-1){
                    return  val;
                }
            }else{
                if(i==size-1){
                    if(isOld){
                        printBuilder.append(String.format("%s='%s'}\n",fieldEntity.getFieldName(), fieldVaule));
                        String val = printBuilder.toString();
                        printBuilder.delete(0,printBuilder.length());
                        return val;
                    }else {
                        if(isLast){
                            printBuilder.append(String.format("%s='%s'}}",fieldEntity.getFieldName(), fieldVaule));
                            String val = printBuilder.toString();
                            printBuilder.delete(0,printBuilder.length());
                            return val;
                        }else{
                            printBuilder.append(String.format("%s='%s'},",fieldEntity.getFieldName(), fieldVaule));
                        }
                    }
                }else{
                    printBuilder.append(String.format("%s='%s',",fieldEntity.getFieldName(), fieldVaule));
                }
            }

        }
        return "printObj error";
    }


    public static void main(String[] args) {
        Object o = GenTest.genNewObjData(new User());
        Prints.printObj(o);
    }

}
