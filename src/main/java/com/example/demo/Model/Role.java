package com.example.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int r_id;
	
	@Column
	private String role;

	public int getRole_id() {
		return r_id;
	}

	public void setRole_id(int role_id) {
		this.r_id = role_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [role_id=" + r_id + ", role=" + role + "]";
	}
	
	

}
