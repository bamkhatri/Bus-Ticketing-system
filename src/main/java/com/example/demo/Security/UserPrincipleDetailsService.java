package com.example.demo.Security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

@Service

public class UserPrincipleDetailsService  implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		User user = userRepo.findByUserName(username);
		
		if(user != null) {
				
			
			UserPrinciple userPrinciple = new UserPrinciple(user);
			
			return userPrinciple;
			
		}else {
			
			throw new UsernameNotFoundException("Invalid Username");
		}
		
		
	}

	

}
