package com.lee.plcanalysis.service;

import com.lee.plcanalysis.dao.UserMapper;
import com.lee.plcanalysis.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByUsername(String username){
        return userMapper.findUserByUsername(username);
    }

    public User findUserByUserId(Long id){
        return userMapper.findUserById(id);
    }

    public User addUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(userMapper.save(user) == 1) return user;
        return null;
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() != null){
            String str = authentication.getPrincipal().toString();
            return userMapper.findUserByUsername("admin");
        }else {
            logger.info("user not authenticated");
            return null;
        }
    }
}
