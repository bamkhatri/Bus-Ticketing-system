package com.example.demo.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Entity
@Component("Passenger")
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int p_id;
	
	@Column
	@NotEmpty(message = "Name cannot be empty")
	public String name_of_the_passenger;
	
	@Column
	@NotEmpty(message = "number cannot be empty")
	@Size(min = 10,max = 10, message = "10 digits required")
	public String mobile_Number;
	
	@Column
	@NotNull(message = "seat cannot be empty")
	public String seat;
	
	@OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
        })
	@JoinColumn(name = "b_id", referencedColumnName = "bus_Id")
	public BusDetails bus;
	
	@ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
        }, fetch = FetchType.EAGER)
	@JoinColumn(name = "u_id", referencedColumnName = "u_Id")
	public User user;
	
	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getName_of_the_passenger() {
		return name_of_the_passenger;
	}

	public void setName_of_the_passenger(String name_of_the_passenger) {
		this.name_of_the_passenger = name_of_the_passenger;
	}

	public String getMobile_Number() {
		return mobile_Number;
	}

	public void setMobile_Number(String mobile_Number) {
		this.mobile_Number = mobile_Number;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}
	
	

	
	
	

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BusDetails getBus() {
		return bus;
	}

	public void setBus(BusDetails bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return "Passenger [p_id=" + p_id + ", name_of_the_passenger=" + name_of_the_passenger + ", mobile_Number="
				+ mobile_Number + ", seat=" + seat + "]";
	}
	
	

}
