package com.example.demo.Model;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Component("BusDetails")
public class BusDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bus_Id;
	
	
	@Column
	public String fromDestination;
	
	@Column
	public String toDestination;
	
	@Column
	@DateTimeFormat
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column
	private String travels;
	
	@Column
	private String bus_Type;
	
	@Column
	private String seats_Available;
	
	@Column
	public String fare;
	
	@Column
	private String departure;
	
	@Column
	private int total_Seats;
	
	
	

	

	public int getBus_Id() {
		return bus_Id;
	}

	public void setBus_Id(int bus_Id) {
		this.bus_Id = bus_Id;
	}

	
	public String getFromDestination() {
		return fromDestination;
	}

	public void setFromDestination(String fromDestination) {
		this.fromDestination = fromDestination;
	}

	public int getTotal_Seats() {
		return total_Seats;
	}

	public void setTotal_Seats(int total_Seats) {
		this.total_Seats = total_Seats;
	}

	public String getToDestination() {
		return toDestination;
	}

	public void setToDestination(String toDestination) {
		this.toDestination = toDestination;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(String date) {
		try {
			
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
	}

	public String getTravels() {
		return travels;
	}

	public void setTravels(String travels) {
		this.travels = travels;
	}

	public String getBus_Type() {
		return bus_Type;
	}

	public void setBus_Type(String bus_Type) {
		this.bus_Type = bus_Type;
	}

	public String getSeats_Available() {
		return seats_Available;
	}

	public void setSeats_Available(String seats_Available) {
		this.seats_Available = seats_Available;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	@Override
	public String toString() {
		return "BusDetails [bus_Id=" + bus_Id + ", fromDestination=" + fromDestination + ", toDestination="
				+ toDestination + ", date=" + date + ", travels=" + travels + ", bus_Type=" + bus_Type
				+ ", seats_Available=" + seats_Available + ", fare=" + fare + ", departure=" + departure + "]";
	}

	
	
	
	
	

}
