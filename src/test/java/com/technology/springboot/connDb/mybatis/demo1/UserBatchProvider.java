package com.technology.springboot.connDb.mybatis.demo1;

import java.util.List;
import java.util.Map;

/**
 * 用户批量操作提供类
 */
public class UserBatchProvider {

    public String batchInsert(Map map) {
       StringBuffer batchSql = new StringBuffer(100);
       List<User> users = (List<User>) map.get("users");
       batchSql.append( "insert into user(name,email,user_account) values");
       for(int i = 0;i<users.size();i++){
           User user = users.get(i);
           batchSql.append("(")
                   .append("'")
                   .append(user.getName())
                   .append("'")
                   .append(",")
                   .append("'")
                   .append(user.getEmail())
                   .append("'")
                   .append(",")
                   .append("'")
                   .append(user.getUserAccount())
                   .append("'")
                   .append(")");
           if(i == users.size() - 1){
               batchSql.append(";");
           }else{
               batchSql.append(",");
           }
       }
        return  batchSql.toString();
    }


}
