package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	public Role findByRole(String role);
	
}
