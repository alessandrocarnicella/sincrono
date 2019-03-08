package com.sincrono.corso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sincrono.corso.model.AziendaService;

@Controller
public class LoginController {

	@Autowired
	AziendaService as;
	
	@RequestMapping(value = "/")
	public String getHome(Model m) {
	
		return "Login";
	}
	
	@RequestMapping(value = "/Dashboard")
	public String getDashboard(Model m) {
		m.addAttribute("list", as.findAll());
		return "Dashboard";
	}
	
	
}
