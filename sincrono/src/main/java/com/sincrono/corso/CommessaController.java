package com.sincrono.corso;


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
import com.sincrono.corso.model.DipendenteService;
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
	
	@Autowired
	DipendenteService ds;

	@RequestMapping(value = "/GestioneCommesseDipendenti")
	public String getGestioneCommessa(Model m, HttpServletRequest request) {
	
		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request)) 
			return "Login";

		/* Aggiunge i parametri necessari in sessione */
		
		request.getSession().setAttribute("errore_commesse", 0);
		m.addAttribute("list_com", com.findAll());
		m.addAttribute("list_az", as.findAll());
		m.addAttribute("list_dip", ds.findAll());
		
		return "GestioneCommesseDipendenti";
	}

	@RequestMapping(value = "/GestioneCommesseAdd")
	public String getGestioneCommesseAdd(Model m, HttpServletRequest request, 
			@RequestParam("nomeCommessa") String nomeCommessa,
			@RequestParam("nomeAziendaCommessa") String nomeAziendaCommessa, 
			@RequestParam("tariffaCliente") double tariffaCliente,
			@RequestParam("idDipendente") int idDipendente) {

		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request))
			return "Login";

		Azienda aziendaCom = new Azienda();
		aziendaCom.setNomeAzienda(nomeAziendaCommessa);

		Optional<Persona> pers = ps.findById(idDipendente);
		
		if(com.findByNomeCommessaAndNomeAzienda(nomeCommessa, aziendaCom).isEmpty()) {


			try {
				Commessa commessa = new Commessa();
				commessa.setNomeCommessa(nomeCommessa);
				commessa.setTariffaCliente(tariffaCliente);
				commessa.setAzienda(aziendaCom);
				commessa.setPersona(pers.get());
				
				/* Aggiunge la commessa */
				
				com.save(commessa);
				request.getSession().setAttribute("errore_commesse", 1);
				
			} catch (Exception e) {
				request.getSession().setAttribute("errore_commesse", 2);
			}
			

		}else {		
			/* Aggiunge i parametri necessari in sessione */
			
			request.getSession().setAttribute("errore_commesse", 2);
			m.addAttribute("list_com", com.findAll());
			m.addAttribute("list_az", as.findAll());
			m.addAttribute("list_dip", ds.findAll());

		}

		/* Aggiunge i parametri necessari in sessione */
		
		m.addAttribute("list_com", com.findAll());
		m.addAttribute("list_az", as.findAll());
		m.addAttribute("list_dip", ds.findAll());
		return "GestioneCommesseDipendenti";
	}

	@RequestMapping(value = "/GestioneCommesseElimina")
	public String getGestioneCommesseDelete(Model m, HttpServletRequest request, 
			@RequestParam("idCommessa") Integer idCommessa) {

		/* Elimina la commessa */
		
		com.deleteById(idCommessa);
		
		/* Aggiunge i parametri necessari in sessione */
		
		request.getSession().setAttribute("errore_commesse", 1);
		m.addAttribute("list_com", com.findAll());
		
		return "GestioneCommesseDipendenti";
	}

	@RequestMapping(value = "/GestioneCommesseUpdate")
	public String getGestioneCommesseUpdate(Model m, HttpServletRequest request, 
			@RequestParam("idCommessa") int idCommessa,
			@RequestParam("nomeCommessa") String nomeCommessa,
			@RequestParam(value="nomeAziendaCommessa", required = false) String nomeAziendaCommessa, 
			@RequestParam("tariffaCliente") double tariffaCliente,
			@RequestParam(value="idDipendente", required = false) Integer idDipendente) {
		
		
		Azienda aziendaCom = new Azienda();
		aziendaCom.setNomeAzienda(nomeAziendaCommessa);
	
		

		try {
			
			Optional<Persona> pers = ps.findById(idDipendente);
			
			/* Aggiorna la commessa */
			com.updateCommessa(idCommessa, tariffaCliente, nomeCommessa, pers.get(), aziendaCom);
			
		}catch(Exception e) {
			request.getSession().setAttribute("errore_commesse", 2);
		}
		
		/* Aggiunge i parametri necessari in sessione */
		
		request.getSession().setAttribute("errore_commesse", 1);
		m.addAttribute("list_com", com.findAll());
		m.addAttribute("list_az", as.findAll());
		m.addAttribute("list_dip", ds.findAll());

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
