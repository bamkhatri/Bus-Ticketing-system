package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Repository.BusDetailsRepository;
import com.example.demo.Repository.UserRepository;

@Controller
public class AdminController {
	
	@Autowired
	BusDetailsRepository busRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/admin/adminpage")
	public ModelAndView getAdminPage() {
		
		ModelAndView model = new ModelAndView();
		
		Long totalBus = busRepo.count();
		Long totalUser = userRepo.count();
		
		model.addObject("totalBus",totalBus);
		model.addObject("totalUser", totalUser);
		model.setViewName("AdminPage");
		return model;
	}

}
