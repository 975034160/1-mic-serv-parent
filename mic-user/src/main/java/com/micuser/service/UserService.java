package com.micuser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micuser.entity.User;
import com.micuser.mapper.UserMapper;


@Service
public class UserService {

	
	@Autowired 
	UserMapper userMapper;
	
	public List<User> findAllUser() {
		return userMapper.findAllUser();
	}
	
    public User findUserById(int id) {
        return userMapper.findByID(id);
    }
	
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}
	
}
