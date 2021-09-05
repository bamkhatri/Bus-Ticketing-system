package com.example.demo.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int u_id;
	
	@Column
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	
	@Column
	@NotEmpty(message = "Username cannot be empty")
	private String userName;
	
	@Column
	@NotEmpty(message = "please enter number")
	@Size(min = 10,max = 10, message = "10 digits required")
	private String number;
	
	@Column
	@NotEmpty
	@Size(min=8,message = "Minimum 8 characters required")
	private String password;
	
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "u_id"), inverseJoinColumns = @JoinColumn(name = "r_id"))
	public Set<Role> roles;
	
	
	/*@ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinTable(name = "user_busdetails", joinColumns = @JoinColumn(name = "u_id") , inverseJoinColumns = @JoinColumn(name = "bus_Id"))
	public Set<BusDetails> bus = new HashSet<BusDetails>();*/
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
	@JsonIgnore
	public List<Passenger> passenger = new ArrayList<Passenger>();
	
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	





	public List<Passenger> getPassenger() {
		return passenger;
	}









	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}









	public int getU_id() {
		return u_id;
	}





	public void setU_id(int u_id) {
		this.u_id = u_id;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getUserName() {
		return userName;
	}





	public void setUserName(String userName) {
		this.userName = userName;
	}





	public String getNumber() {
		return number;
	}





	public void setNumber(String number) {
		this.number = number;
	}





	public Set<Role> getRoles() {
		return roles;
	}





	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}
	
	
	



	/*public Set<BusDetails> getBus() {
		return bus;
	}

	public void setBus(Set<BusDetails> bus) {
		this.bus = bus;
	}
*/
	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", name=" + name + ", userName=" + userName + ", number=" + number + ", roles="
				+ roles + ", password=" + password + "]";
	}

	
	
	
	
	
	
	
	
	

}
