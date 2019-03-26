package com.sincrono.corso;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sincrono.corso.model.Mailer;

@Controller
public class ContattiController {
	
	@RequestMapping(value = "/ContactUs")
	public String getContatti(Model m, HttpServletRequest request) {
		
		if(!isLog(request)) 
			return "Login";
	
		return "ContactUS";
	}
	
	@RequestMapping(value = "/ContactUsMsg")
	public String getContattiMsg(Model m, HttpServletRequest request,
			@RequestParam("cognome") String cognomePersona,
			@RequestParam("nome") String nomePersona, 
			@RequestParam("email") String emailPersona,
			@RequestParam("msg") String msg) {
		
		if(!isLog(request)) 
			return "Login";
		
		inviaEmailInfo(cognomePersona,nomePersona,emailPersona,msg);
		
		return "ContactUS";
	}
	
	
	
	/** Ritorna true se l'utente in sessione è loggato **/
	private boolean isLog(HttpServletRequest request) {
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return false;
		}
		return true;
	}
	
	/** Invia email */
	
	private void inviaEmailInfo(String cognomePersona, String nomePersona, String emailPersona, String msg) {

		Mailer mail = new Mailer();
		mail.send("nomec443@gmail.com","sincrono","nomec443@gmail.com","Sincronia "+ nomePersona+cognomePersona+" Problem",msg+ "\n\n\n Rispondere a: "+emailPersona);

	}
}
