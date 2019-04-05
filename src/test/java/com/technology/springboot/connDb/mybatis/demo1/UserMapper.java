package com.technology.springboot.connDb.mybatis.demo1;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    //   测试别名
    @Select("select * from user where id = #{id}")
    User getById(Integer id);

    @Select("select * from user where id = #{id}")
    @Results(
            {@Result(property = "userAccount",column = "user_account")}
    )
    User getById2(Integer id);

    //可以省略前缀,直接调用
    @Insert("insert into user(name,email,user_account) values (#{name},#{email},#{userAccount}) ")
    void insert(User user);

    @InsertProvider(type =UserBatchProvider.class,method = "batchInsert")
    void  batchInsert(@Param("users") List<User> users);










}
