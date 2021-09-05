package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Model.Passenger;

@Service
public interface PassengerServiceInterface {
	
	
	public void savePassenger(Passenger passenger);

}
