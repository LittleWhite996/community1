package com.ty.community.service;

import com.ty.community.mapper.UserMapper;
import com.ty.community.model.User;
import com.ty.community.model.UserExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void CreateOrUpdate(User user) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users =userMapper.selectByExample(userExample);

        if (users.size()==0){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            User dbuser=users.get(0);
            User updateuser=new User();
            updateuser.setGmtModified(System.currentTimeMillis());


            updateuser.setAvatarUrl(user.getAvatarUrl());
            updateuser.setName(user.getName());
            updateuser.setToken(user.getToken());
            UserExample example=new UserExample();
            example.createCriteria().andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateuser,example);
        }
    }
}
