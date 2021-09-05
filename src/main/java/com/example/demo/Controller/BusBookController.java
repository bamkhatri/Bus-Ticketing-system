package com.example.demo.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Model.BusDetails;
import com.example.demo.Model.Passenger;
import com.example.demo.Model.User;
import com.example.demo.Repository.BusDetailsRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.BusDetailsInterface;

@Controller
public class BusBookController {
	
	
	@Autowired
	BusDetailsInterface busDetail;
	
	@Autowired
	BusDetailsRepository busRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PassengerController passController;
	
	@GetMapping("/findallbus")
	@ResponseBody
	public List<BusDetails> findAll(){
		
		return busRepo.findAll();
	}
	
	@PostMapping("/bookbus")
	@ResponseBody
	public BusDetails bookBus(@ModelAttribute BusDetails bus) {
		
				
		passController.setBus(bus);
		
		return bus;
		
	}
	
	@PostMapping("/saveBus")
	@ResponseBody
	public BusDetails saveBus(@RequestBody BusDetails bus) {
		
		System.out.println(bus.toString());
		return busDetail.saveBus(bus);
	}
	
	@PostMapping("/findbus")
	@ResponseBody
	public List<BusDetails> findUserBus(@ModelAttribute BusDetails bus) {
		
		System.out.println(bus);
		if(bus == null || bus.getDate() == null) {
			
			throw new NullPointerException("Please Fill the details");
		}
		return busDetail.findUserBus(bus);
		
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
	}
	
	@PostMapping("/reduceSeat")
	@ResponseBody
	public BusDetails reduceSeat(@ModelAttribute BusDetails bus) {
		
		
		String stringSeat = bus.getSeats_Available();
		int intSeat = Integer.parseInt(stringSeat);
		int reducedSeat = intSeat - 1;
		String busFinalSeat = String.valueOf(reducedSeat);
		
		busRepo.reduceSeats(busFinalSeat, bus.getBus_Id());
		
		return bus;
		
	}
	
	@GetMapping("/findbookedbus")
	@ResponseBody
	public List<Passenger> findBookedBus() {
		

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String currentPrincipleName = authentication.getName();
		
		User user = userRepo.findByUserName(currentPrincipleName);
		
		/*Set<BusDetails> bus = user.getBus();*/
		List<Passenger> passengerName = user.getPassenger();
		
		
		return passengerName;
		
		
	}
	
	
	

}
