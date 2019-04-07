package com.technology.util.gen;

import com.technology.bean.User;
import com.technology.util.print.Prints;
import com.technology.util.ref.Refs;

import java.util.List;

/**
 * 生成测试数据
 */
public class GenTest {

    private GenTest(){}

    /**
     * 返回该对象的测试数据
     * @param obj
     * @return
     */
    public static  String genNewObjStr(Object obj){
        if(obj == null){
            return null;
        }
        List<Refs.FieldEntity> fieldEntities = Refs.getClassFields(obj); //获取所有的字段类型
        StringBuilder javaGrammar = new StringBuilder(100);


        return "";
    }



    public static  Object genNewObjData(Object obj){
        if(obj == null){
            return null;
        }
        List<Refs.FieldEntity> fieldEntities = Refs.getClassFields(obj); //获取所有的字段类型
        fieldEntities.forEach(item->{
            Refs.setGenValue(item,obj);
        });
        return obj;
    }








    public static void main(String[] args) {
        Object obj =  GenTest.genNewObjData(new User());
        Prints.printObj(obj);
    }









}
