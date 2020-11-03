package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.*;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Update("UPDATE USERNAME SET username = #{username}, salt = #{salt}, password = #{password}, firstname = #{firstname} lastname = #{lasttame} WHERE username = #{username}")
    void update(User user);

    @Delete("DELETE FROM USERS WHERE username = #{username}")
    void delete(User username);

}
