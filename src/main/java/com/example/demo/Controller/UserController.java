package com.example.demo.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Model.BusDetails;
import com.example.demo.Model.Passenger;
import com.example.demo.Model.User;
import com.example.demo.Repository.PassengerRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserServiceInterface;



@Controller
public class UserController {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	UserServiceInterface userServiceInter;
	
	@Autowired
	PassengerRepository passRepo;
	
	@Autowired
	PassengerController passCont;
	
	@GetMapping("/login")
	public String logIn() {
		
		
		return "Login";
	}
	@GetMapping("/home")
	public String home() {
		
		
		return "Home";
	}
	
	@GetMapping("/userpage")
	public String getUserPage() {
		
		
		return "UserPage";
	}
	
	@GetMapping("/user/myticket")
	public String getMyticket() {
		
		
		return "MyTicket";
		
	}
	
	@GetMapping("/user/printticket")
	public ModelAndView getPrintPage() {
		
		ModelAndView model = new ModelAndView();
		
			
		Passenger passenger = passCont.getPassenger();
		Passenger passengerTwo = passRepo.getOne(passenger.getP_id());
		model.addObject("bus", passengerTwo.getBus());
		model.addObject("passenger", passengerTwo);
		model.setViewName("PrintTicket");
		return model;
		
	}
	
	@GetMapping("/user/passengerpage")
	public ModelAndView getPassengerPage() {
		
		ModelAndView model = new ModelAndView();
		/*User user = userServiceInter.getUser();*/
		BusDetails bus = passCont.getBus();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(bus.getDate());
		System.out.println(date);
		bus.setDate(date);
		model.addObject("bus", bus);
		model.setViewName("PassengerPage");
		model.addObject("passenger", new Passenger());
		return model;
		
	}
	
	
	
	
	@GetMapping("/register")
	public ModelAndView userPage() {
		
		ModelAndView model  = new ModelAndView();
		model.addObject("user", new User());
		model.setViewName("Register");
		return model;
	}
	
	@GetMapping("/admin/home")
	public String adminPage() {
		
		return "Admin";
	}
	
	
	@PostMapping("/registerUser")
	@ResponseBody
	public ModelAndView registerUser(@ModelAttribute @Valid User user,BindingResult bindingResult) {
		
		ModelAndView model = new ModelAndView();
		
		User checkUser = userServiceInter.findByUsername(user.getUserName());
		
		
		if(checkUser != null) {
			
			System.out.println("user already present block");
			model.addObject("userPresent", "User already present");
			model.setViewName("Register");
			model.addObject("user", new User());
			return model;
			
			
			
		}
		
		if(bindingResult.hasErrors()) {
			
			System.out.println("binding block");
			model.setViewName("Register");
			
			return model;
			
			
			
		}
		
		System.out.println("save user block");
		userServiceInter.save(user);
		model.setViewName("Login");
		return model;
		
	
			
			
		
	}
	
	@PostMapping("/user/print")
	@ResponseBody
	public Passenger setPassenger(@ModelAttribute Passenger passenger) {
		
		System.out.println(passenger);
		passCont.setPassenger(passenger);
		return passenger;
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
	}
	
	
	
	

	
	
	
	
	
	
}
