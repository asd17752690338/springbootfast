package com.technology.springboot.connDb.mybatis.demo1;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

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

    @Select({"call selectUser"})
    @Options(statementType= StatementType.CALLABLE)
    @Results(
            {@Result(property = "userAccount",column = "user_account")}
    )
    List<User> callProducer();

    @Select({"call selectUserById(#{id,mode=IN,jdbcType=BIGINT})"})
    @Options(statementType= StatementType.CALLABLE)
    @Results(
            {@Result(property = "userAccount",column = "user_account")}
    )
    User callUserById(Long id);  //默认不要输入参数

    @Select({"call selectCount(#{count,mode=OUT,jdbcType=BIGINT})"})
    @Options(statementType= StatementType.CALLABLE)
    Long callCount(@Param("count") Long count);  //这里的返回值没有得到,可能是需要map才会设置


    @Select({"call selectCount(#{count,mode=OUT,jdbcType=BIGINT})"})
    @Options(statementType= StatementType.CALLABLE)
    Long callCount2(MyData myData);






}
