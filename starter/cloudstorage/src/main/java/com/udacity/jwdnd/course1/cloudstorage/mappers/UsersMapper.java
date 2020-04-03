package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.entities.Users;

@Mapper
public interface UsersMapper {
	
    @Select("select * from users")
    List<Users> findAll();

    @Select("select * from users where users.userid = #{id}")
    Users findOne(@Param("id") Integer id);
    
    @Insert("insert into users (username,password,firstname,lastname) values (#{user.username},#{user.password},#{user.firstname},#{user.lastname})")
    Integer register(@Param("user") Users user);
   
    
    
}