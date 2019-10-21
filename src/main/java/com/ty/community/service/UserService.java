package com.ty.community.service;

import com.ty.community.mapper.UserMapper;
import com.ty.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void CreateOrUpdate(User user) {

        User dbuser=userMapper.findvyaccountid(user.getAccount_id());
        if (dbuser==null){
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
        }else {
            dbuser.setGmt_modified(System.currentTimeMillis());
            dbuser.setAvatar_url(user.getAvatar_url());
            dbuser.setName(user.getName());
            dbuser.setToken(user.getToken());
            userMapper.update(dbuser);
        }
    }
}
