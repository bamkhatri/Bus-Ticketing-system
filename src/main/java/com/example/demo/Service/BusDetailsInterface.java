package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Model.BusDetails;

@Service
public interface BusDetailsInterface {
	
	public BusDetails bookBus(BusDetails bus);
	public BusDetails saveBus(BusDetails bus);
	public List<BusDetails> findUserBus(BusDetails bus);

}
