package com.technology.util.gen;

import com.technology.bean.User;
import com.technology.util.ref.Refs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GenSql {

    private  static  final Logger LOG = LoggerFactory.getLogger(GenSql.class.getName());
    public  static final StringBuilder sqlBuilder = new StringBuilder(100);
    private GenSql(){}

    public static void insert(Object obj){
        if(obj==null){
            return;
        }
        Class<?> clz = obj.getClass();
        List<Refs.FieldEntity> fieldEntities = Refs.getClassFields(obj);
        String tableName = clz.getSimpleName().toLowerCase();
        sqlBuilder.append("insert into ").append(tableName).append("(") ;
        //拼接插入字段
        int size = fieldEntities.size();
        for(int i =0;i<size;i++){
            Refs.FieldEntity fieldEntity = fieldEntities.get(i);
            if(i==size-1){
                sqlBuilder.append(fieldEntity.getFieldName()).append(")");
            }else {
                sqlBuilder.append(fieldEntity.getFieldName()).append(",");
            }
        }
        //拼接输出字段
        sqlBuilder.append(" ").append("values(");
        for(int i =0;i<size;i++){
            Refs.FieldEntity fieldEntity = fieldEntities.get(i);
            if(fieldEntity.getState()!=0){  //不是基本类型
                sqlBuilder.append("'");
            }
            sqlBuilder.append(fieldEntity.getFieldVaule());
            if(fieldEntity.getState()!=0){  //不是基本类型
                sqlBuilder.append("'");
            }
            if(i==size-1){
                sqlBuilder.append(")");
            }else {
                sqlBuilder.append(",");
            }

        }
        System.out.println(sqlBuilder.toString());
        sqlBuilder.delete(0,sqlBuilder.length());
    }


    public static void main(String[] args) {
        Object o = GenTest.genNewObjData(new User());
        insert(o);
    }











}
