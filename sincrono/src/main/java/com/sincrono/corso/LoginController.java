package com.sincrono.corso;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sincrono.corso.model.AziendaService;
import com.sincrono.corso.model.Dipendente;
import com.sincrono.corso.model.DipendenteService;


@Controller
public class LoginController {

	@Autowired
	DipendenteService dip;
	
	@Autowired
	AziendaService as;
	
	@RequestMapping(value = "/")
	public String getHome(Model m) {
		m.addAttribute("error_login", false);
		return "Login";
	}
	
	@RequestMapping(value = "/Dashboard")
	public String getDashboard(Model m, @RequestParam("email") String email,
            @RequestParam("password") String password) {	
		
		int dipId = 0;
		Optional<Dipendente> dipendente;
		try {
		dipId = dip.existUser(password, email);
		}catch(Exception e) {
			 dipId = 0;
		}
		
		if(dipId == 0) {
			m.addAttribute("error_login", true);
			return "Login";
		}
			
		dipendente = dip.findById(dipId);
	
		m.addAttribute("list_aziende", as.findAll());
		m.addAttribute("list_dipendenti", dip.findAll());
		
		/*dipendente.get().getCategoria().getId().getNomeCat()*/
		
		m.addAttribute("dipendente", dipendente);
		m.addAttribute("error_login", false);
		return "Dashboard";
	}
}
