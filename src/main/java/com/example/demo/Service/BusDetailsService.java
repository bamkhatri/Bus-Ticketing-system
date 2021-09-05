package com.example.demo.Service;


import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.Model.BusDetails;
import com.example.demo.Model.User;
import com.example.demo.Repository.BusDetailsRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class BusDetailsService implements BusDetailsInterface {
	
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BusDetailsRepository repo;
	
	public BusDetails bookBus(BusDetails bus) {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String currentPrincipleName = authentication.getName();
		
				
		
		User user = userRepo.findByUserName(currentPrincipleName);
		
		
		/*user.getBus().add(bus);*/ // use to add the bus to the user.
		System.out.println(user);
		System.out.println(bus);
		
		
    	userRepo.save(user);
		
		
		
		return bus;
		
		
	}
	
	public BusDetails saveBus(BusDetails bus) {
		
		repo.save(bus);
		
		return bus;
		
	}
	
	public List<BusDetails> findUserBus(BusDetails bus) {
		
		String toDestination = bus.getToDestination();
		String fromDestination = bus.getFromDestination();
		Date date = bus.getDate();
		
		
		
		LocalDateTime now =  LocalDateTime.now();
		System.out.println(now);
		Date currentDate = Date.from( now.atZone( ZoneId.systemDefault()).toInstant());
		System.out.println(currentDate);
		
		
		
		List<BusDetails> userBus = repo.findUserBus(fromDestination, toDestination);
		
		
		
		if(userBus == null || userBus.isEmpty() == true) {
			
			throw new NullPointerException("Sorry!! no busses for this route");
			
		}else if(date.before(currentDate)) {
			
			throw new DateTimeException("The date has already passed");
			
			
		}
		else {
			
			List<BusDetails> totalBus = compareDate(userBus, date);
			System.out.println(totalBus);
			return totalBus;
				
		}
		
		
		
		
		
	}
	
	
	
	public List<BusDetails> compareDate(List<BusDetails> userBus,Date date){
		
		List<BusDetails> totalBus = new ArrayList<BusDetails>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String userDate = sdf.format(date);
		
		for(int i = 0;i<userBus.size();i++) {
			
		
			String dbDate = sdf.format(userBus.get(i).getDate());
			
			
			if(dbDate.equals(userDate)) {
				
				totalBus.add(userBus.get(i));
				System.out.println(userBus.get(i));
			}
		}
		
		if(totalBus.isEmpty()) {
			
			throw new NullPointerException("Sorry!! no available buses on this date!!");
			
		}
		
		return totalBus;
		
		
		
	}
	
	
}
