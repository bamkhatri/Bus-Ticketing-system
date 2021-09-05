package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Model.User;

@Service
public interface UserServiceInterface {
	
	
	public void save(User user);
	
	public User findByUsername(String userName);
	
	public User getUser();

}
