package com.ty.community.mapper;

import com.ty.community.model.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,avatar_url) values (#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findbytoken(@Param("token") String token);
    @Select("select * from user where id=#{id}")
    User findbyId(@Param("id") Integer id);
    @Select("select * from user where account_id=#{account_id}")
    User findvyaccountid(@Param("account_id") String account_id);
    @Update("update user set name=#{name},avatar_url={avatar_url},gmt_modified=#{gmt_modified},token=#{token} where id=#{id}")
    void update(User dbuser);
}
