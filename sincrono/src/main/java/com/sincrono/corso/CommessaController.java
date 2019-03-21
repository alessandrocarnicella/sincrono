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
	
		/** Blocca l'accesso alla pagina */		
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
			@RequestParam(value="nomeAziendaCommessa", required = false) String nomeAziendaCommessa, 
			@RequestParam("tariffaCliente") double tariffaCliente,
			@RequestParam(value="idDipendente", required = false) Integer idDipendente) {

		/** Blocca l'accesso alla pagina */		
		if(!isLog(request))
			return "Login";

		Azienda aziendaCom = new Azienda();
		aziendaCom.setNomeAzienda(nomeAziendaCommessa);

		if(com.findByNomeCommessaAndNomeAzienda(nomeCommessa, aziendaCom).isEmpty()) {

			try {	
				
				Optional<Persona> pers = ps.findById(idDipendente);
				
				/** Crea la commessa */
				Commessa commessa = creaCommessa(nomeCommessa,tariffaCliente,aziendaCom,pers.get());
			
				/** Aggiunge la commessa */
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

		/** Elimina la commessa */
		com.deleteById(idCommessa);
		
		/* Aggiunge i parametri necessari in sessione */
		
		request.getSession().setAttribute("errore_commesse", 1);
		m.addAttribute("list_az", as.findAll());
		m.addAttribute("list_dip", ds.findAll());
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
			
			/** Aggiorna la commessa */
			com.updateCommessa(idCommessa, tariffaCliente, nomeCommessa, pers.get(), aziendaCom);
			
			request.getSession().setAttribute("errore_commesse", 1);
		}catch(Exception e) {
			request.getSession().setAttribute("errore_commesse", 2);
		}
		
		/* Aggiunge i parametri necessari in sessione */
		
		m.addAttribute("list_com", com.findAll());
		m.addAttribute("list_az", as.findAll());
		m.addAttribute("list_dip", ds.findAll());

		return "GestioneCommesseDipendenti";
	}

	/** Questo metodo crea una commessa, ritorna la commessa creata**/	
	private Commessa creaCommessa(String nomeCommessa, double tariffaCliente, Azienda aziendaCom, Persona persona) {
		Commessa commessa = new Commessa();
		commessa.setNomeCommessa(nomeCommessa);
		commessa.setTariffaCliente(tariffaCliente);
		commessa.setAzienda(aziendaCom);
		commessa.setPersona(persona);
		return commessa;
	}
	
	/** Ritorna true se l'utente in sessione è loggato **/	
	private boolean isLog(HttpServletRequest request) {
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return false;
		}
		return true;
	}
}
