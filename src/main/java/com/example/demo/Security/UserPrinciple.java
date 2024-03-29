package com.example.demo.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;





import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.example.demo.Model.User;


public class UserPrinciple implements UserDetails{
	

	User user;
	
	public UserPrinciple(User user	) {
		
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		
		
		user.getRoles().forEach(p -> {
			
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+p.getRole());
			
			authorities.add(authority);
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
