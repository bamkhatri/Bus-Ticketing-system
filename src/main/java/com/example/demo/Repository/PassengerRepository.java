package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.BusDetails;
import com.example.demo.Model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{
	
	public Passenger getBySeat(int seat);
	public List<Passenger> getByBus(BusDetails bus);
	public Passenger getBySeatAndBus(String seat,BusDetails bus);

}
