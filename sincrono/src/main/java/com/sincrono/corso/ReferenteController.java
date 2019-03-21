package com.sincrono.corso;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sincrono.corso.model.Azienda;
import com.sincrono.corso.model.AziendaService;
import com.sincrono.corso.model.Categoria;
import com.sincrono.corso.model.CategoriaPK;
import com.sincrono.corso.model.Dipendente;
import com.sincrono.corso.model.Persona;
import com.sincrono.corso.model.PersonaService;
import com.sincrono.corso.model.Referente;
import com.sincrono.corso.model.ReferenteService;



@Controller
public class ReferenteController {
	
	@Autowired
	AziendaService as;
	
	@Autowired
	ReferenteService ref;
	
	@Autowired
	PersonaService per;
	
	@RequestMapping(value = "/GestioneReferenteAdd")
	public String getAddReferente(Model m, HttpServletRequest request,
			@RequestParam("cognome-ref-add") String cognome_ref_add,
			@RequestParam("nome-ref-add") String nome_ref_add, 
			@RequestParam("email-ref-add") String email_ref_add,
			@RequestParam("azienda-ref-add") String azienda_string_ref_add,
			@RequestParam("telefono-ref-add") String telefono_ref_add) {
		
		/* AGGIUNGERE PRIMA LA PERSONA E POI ASSOCIARLA AL REFERENTE CON L'ID */ 
		/** Blocca l'accesso alla pagina */		
		if(!isLog(request)) 
			return "Login";	

		if(per.findByEmailPersona(email_ref_add).isEmpty()) {
			
			/* RECUPERO L'OGGETTO AZIENDA DAL NOME AZIENDA */
			Azienda azienda_ref_add = as.findById(azienda_string_ref_add).get();
			
			/** Crea la perosna */	
			Persona persona = creaPersona(nome_ref_add,cognome_ref_add,email_ref_add);

			/** Salva la persona */
			per.save(persona);			
			
			/** Crea il referente */	
			Referente referente = creaReferente(azienda_ref_add,persona.getIdPersona(),telefono_ref_add);
			
			/** Salva il referente */
			ref.save(referente);
			
		}else {
			request.getSession().setAttribute("errore_referenti", 2);
		}	
		
		request.getSession().setAttribute("errore_referenti", 1);
		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}

	@RequestMapping(value = "/GestioneReferenteEdit")
	public String getEditReferente(Model m, HttpServletRequest request,
			@RequestParam("cognome-ref-edit") String cognome_ref_edit,
			@RequestParam("nome-ref-edit") String nome_ref_edit, 
			@RequestParam("email-ref-edit") String email_ref_edit,
			@RequestParam("azienda-ref-edit") String azienda_ref_edit,
			@RequestParam("telefono-ref-edit") String telefono_ref_edit,
			@RequestParam("id-ref-edit") int id_ref_edit) {
			
		/** Blocca l'accesso alla pagina */		
		if(!isLog(request)) 
			return "Login";
		
		/** Aggiorna la persona */
		per.updatePersona(id_ref_edit, cognome_ref_edit, nome_ref_edit, email_ref_edit);

		/** Aggiorna il referente */
		ref.updateReferente(id_ref_edit, telefono_ref_edit);
		
		/* Aggiunge i parametri necessari in sessione */
		request.getSession().setAttribute("errore_referenti", 1);
		m.addAttribute("list_az", as.findAll());
		
		return "GestioneAziende";
	}
	
	
	@RequestMapping(value = "/GestioneReferenteDelete")
	public String getDeleteReferente(Model m, HttpServletRequest request,
			@RequestParam("id-ref-delete") int id_ref_delete) {
		
		/** Blocca l'accesso alla pagina */		
		if(!isLog(request)) 
			return "Login";
		
		/** Elimina il referente */
		ref.deleteById(id_ref_delete);

		/** Elimina la persona  */
		per.deleteById(id_ref_delete);
		
		/* Aggiunge i parametri necessari in sessione */
		request.getSession().setAttribute("errore_referenti", 1);
		m.addAttribute("list_az", as.findAll());
		
		return "GestioneAziende";
	}
	
	/** Questo metodo crea referente, ritorna il referente creato **/
	private Referente creaReferente(Azienda azienda_ref_add, int idPersona, String telefono_ref_add) {
		Referente referente = new Referente();
		referente.setAzienda(azienda_ref_add);
		referente.setIdRef(idPersona);
		referente.setTelefonoRef(telefono_ref_add);
		return referente;
	}
	
	/** Questo metodo crea una persona, ritorna la persona creata **/
	private Persona creaPersona(String nomePersona, String cognomePersona, String emailPersona) {
		Persona persona = new Persona();
		persona.setNomePersona(nomePersona);
		persona.setCognomePersona(cognomePersona);
		persona.setEmailPersona(emailPersona);
		return persona;
	}
	
	/** Ritorna true se l'utente in sessione è loggato **/		
	private boolean isLog(HttpServletRequest request) {
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return false;
		}
		return true;
	}
}
