package com.example.demo.Service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService implements UserServiceInterface{

	
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	PasswordEncoder crypt;
	
	@Autowired
	RoleRepository roleRepo;
	
	public void save(User user) {
		
		
		
			
			user.setPassword(crypt.encode(user.getPassword()));
			Role role = roleRepo.findByRole("USER");
			user.setRoles(new HashSet<Role>(Arrays.asList(role)));
			repo.save(user);
			
		

			
			
		
		
		
		
		
	}

	@Override
	public User findByUsername(String userName) {
		
		User user = repo.findByUserName(userName);
		return user;
		
	}
	
	
	public User getUser() {
		
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			String currentPrincipleName = authentication.getName();
			
			System.out.println(currentPrincipleName);
			
			
			
			User user = repo.findByUserName(currentPrincipleName);
			
			return user;
		
		
	}
	
	

	
}
