package com.sincrono.corso;

import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String getHome(Model m, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		boolean isLogged = false;
		
		session.setAttribute("isLogged", isLogged);
		m.addAttribute("error_login", false);
		
		return "Login";
	}
	
	@RequestMapping(value = "/Dashboard")
	public String getDashboard(Model m, HttpServletRequest request, 
			@RequestParam("email") String email,
            @RequestParam("password") String password) {	
		
		boolean isLogged = (boolean) request.getSession().getAttribute("isLogged");
		
		int dipId = 0;
		Optional<Dipendente> dipendente;
		try {
		dipId = dip.existUser(password, email);
		}catch(Exception e) {
			 dipId = 0;
		}
		
		if(dipId == 0) {
			m.addAttribute("error_login", true);
			isLogged =  false;
			request.getSession().setAttribute("isLogged", isLogged);
			return "Login";
		}
			
		dipendente = dip.findById(dipId);
	
		m.addAttribute("list_aziende", as.findAll());
		m.addAttribute("list_dipendenti", dip.findAll());
	
		isLogged =  true;
		request.getSession().setAttribute("isLogged", isLogged);	
		request.getSession().setAttribute("dipendente", dipendente);		
		m.addAttribute("error_login", false);
		return "Dashboard";
	}
	
	@RequestMapping(value = "/Logout")
	public String getLogout(Model m, HttpServletRequest request) {
		
		request.getSession().invalidate();
		request.getSession().removeAttribute("isLogged");
		request.getSession().removeAttribute("dipendente");
		
		m.addAttribute("error_login", false);
		return "Login";
	}
	
	protected String getRandomPsw() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	@RequestMapping(value = "/ForgotPassword")
	public String getForgotPassword() {
		return "ForgotPassword";
	}
	
	
	@RequestMapping(value = "/RecuperaPsw")
	public String getLoginNewPsw(Model m,@RequestParam("recupero_email") String recupero_email) {

		int dip_id = dip.existUserByEmail(recupero_email);
		String new_password = getRandomPsw();
		dip.updatePswDip(dip_id, new_password);

		m.addAttribute("new_psw", new_password);
		
		return "Login";
	}
	
	
	
}
