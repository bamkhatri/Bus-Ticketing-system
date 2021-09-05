package com.example.demo.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Model.BusDetails;
import com.example.demo.Model.Passenger;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.BusDetailsInterface;
import com.example.demo.Service.PassengerServiceInterface;

@Controller
public class PassengerController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PassengerServiceInterface passengerInter;
	
	@Autowired
	private BusDetailsInterface busDetailsInter;
	
	@Autowired
	@Qualifier("BusDetails")
	private BusDetails bus;
	
	@Autowired
	@Qualifier("Passenger")
	private Passenger passenger;
	
	

	@PostMapping("/savePassenger")
	@ResponseBody
	@Transactional
	public Passenger savePassenger(@Valid @ModelAttribute Passenger passenger,BindingResult bindingResult) {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String currentPrincipleName = authentication.getName();
		
	
		if(bindingResult.hasFieldErrors("name_of_the_passenger") || bindingResult.hasFieldErrors("seat")) {
			
			throw new RuntimeException("Please fill the details!!");
			
			
		}
		
		if(bindingResult.hasFieldErrors("mobile_Number")) {
			
			throw new RuntimeException("PLease enter 10 digit number!!");
			
			
		}
		
		User user = userRepo.findByUserName(currentPrincipleName);
			
		BusDetails busBook = getBus();	
		passenger.setBus(busBook);
		passenger.setUser(user);
		
		passengerInter.savePassenger(passenger);
			
			
			
		return passenger;

	}
	
	@GetMapping("/getFare")
	@ResponseBody
	public String getBusFare() {
		
		BusDetails bus = getBus();
		
		return bus.getFare();
		
	}
	
	public void setBus(BusDetails bus) {
		
		this.bus = bus;
		
	}
	
	public BusDetails getBus() {
		
		return bus;
	}
	
	public void setPassenger(Passenger passenger) {
		
		this.passenger = passenger;
	}
	
	public Passenger getPassenger() {
		
		return passenger;
	}
}
