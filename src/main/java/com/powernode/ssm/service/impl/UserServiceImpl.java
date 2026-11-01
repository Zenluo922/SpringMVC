package com.powernode.ssm.service.impl;

import com.powernode.ssm.bean.User;
import com.powernode.ssm.dao.UserDao;
import com.powernode.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getById(Long id){
        return userDao.selectById(id);
    }
}
