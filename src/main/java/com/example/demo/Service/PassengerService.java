package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Passenger;
import com.example.demo.Repository.PassengerRepository;

@Service
public class PassengerService implements PassengerServiceInterface{
	
	@Autowired
	PassengerRepository repo;
	
	public void savePassenger(Passenger passenger) {
		
	
		List<Passenger> all_Passengers = repo.getByBus(passenger.getBus());
		
		for(int i = 0; i< all_Passengers.size();i++) {
				
				Passenger passengerOne = all_Passengers.get(i);
				
				if(passengerOne.getSeat().equals(passenger.getSeat()) || passengerOne.getSeat().contains(passenger.getSeat())){
					
					throw new RuntimeException("Seat Already Taken");
					
				}
			
		}
		
		
		
		
		repo.save(passenger);
		
			
		
	}
	
	 

}
