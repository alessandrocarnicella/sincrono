package com.sincrono.corso;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.sincrono.corso.model.Azienda;
import com.sincrono.corso.model.AziendaService;
import com.sincrono.corso.model.Commessa;
import com.sincrono.corso.model.CommessaService;
import com.sincrono.corso.model.Persona;
import com.sincrono.corso.model.PersonaService;

@Controller
public class CommessaController {

	@Autowired
	CommessaService com;

	@Autowired
	AziendaService as;

	@Autowired
	PersonaService ps;

	@RequestMapping(value = "/GestioneCommesseDipendenti")
	public String getGestioneCommessa(Model m, HttpServletRequest request) {
	
		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request)) 
			return "Login";

		m.addAttribute("error_insert_commessa", false);

		m.addAttribute("list_com", com.findAll());

		

		return "GestioneCommesseDipendenti";
	}

	//@RequestParam("idCommessa") int idCommessa,
	
	@RequestMapping(value = "/GestioneCommesseAdd")
	public String getGestioneCommesseAdd(Model m, HttpServletRequest request, 
			@RequestParam("nomeCommessa") String nomeCommessa,
			@RequestParam("nomeAziendaCommessa") String nomeAziendaCommessa, 
			@RequestParam("tariffaCliente") double tariffaCliente,
			@RequestParam("idDipendente") int idDipendente) {

		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request))
			return "Login";

		boolean error = false;

		Azienda aziendaCom = new Azienda();
		aziendaCom.setNomeAzienda(nomeAziendaCommessa);

		Optional<Persona> pers = ps.findById(idDipendente);
		
		if(com.findByNomeCommessaAndNomeAzienda(nomeCommessa, aziendaCom).isEmpty()) {

			error = false;

			try {
				Commessa commessa = new Commessa();
				commessa.setNomeCommessa(nomeCommessa);
				commessa.setTariffaCliente(tariffaCliente);
				commessa.setAzienda(aziendaCom);
				commessa.setPersona(pers.get());
				com.save(commessa);
				
			} catch (Exception e) {
				
				System.out.println("l'azienda o l'utente inserito non esistono!!!");
				
			}
			

		}else {

			error = true;
			System.out.println("la commessa esiste già!!!");
			m.addAttribute("error_insert_azienda", error);
			m.addAttribute("list_com", com.findAll());

		}


		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request)) {
			return "Login";
		}

		m.addAttribute("list_com", com.findAll());

		return "GestioneCommesseDipendenti";
	}

	
	@RequestMapping(value = "/GestioneCommesseElimina")
	public String getGestioneCommesseDelete(Model m, HttpServletRequest request, 
			@RequestParam("idCommessa") Integer idCommessa) {

		com.deleteById(idCommessa);

		m.addAttribute("list_com", com.findAll());

		return "GestioneCommesseDipendenti";

	}

	@RequestMapping(value = "/GestioneCommesseUpdate")
	public String getGestioneCommesseUpdate(Model m, HttpServletRequest request, 
			@RequestParam("idCommessa") int idCommessa,
			@RequestParam("nomeCommessa") String nomeCommessa,
			@RequestParam("nomeAziendaCommessa") String nomeAziendaCommessa, 
			@RequestParam("tariffaCliente") double tariffaCliente,
			@RequestParam("idDipendente") int idDipendente) {
		
		
		Azienda aziendaCom = new Azienda();
		aziendaCom.setNomeAzienda(nomeAziendaCommessa);

		Optional<Persona> pers = ps.findById(idDipendente);

		com.updateCommessa(idCommessa, tariffaCliente, nomeCommessa, pers.get(), aziendaCom);

		//System.out.println(com.updateCommessa(idCommessa, tariffaCliente, nomeCommessa, pers.get(), aziendaCom));
		
		m.addAttribute("list_com", com.findAll());

		return "GestioneCommesseDipendenti";

	}


	/*Blocco accesso alla pagina se non loggato*/		
	private boolean isLog(HttpServletRequest request) {
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return false;
		}
		return true;
	}
}
