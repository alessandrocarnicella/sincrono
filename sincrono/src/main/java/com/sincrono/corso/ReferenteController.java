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
		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request)) 
			return "Login";
		boolean error = false;
		

		if(per.findByEmailPersona(email_ref_add).isEmpty()) {
			
		
			/* RECUPERO L'OGGETTO AZIENDA DAL NOME AZIENDA */
			Azienda azienda_ref_add = as.findById(azienda_string_ref_add).get();
						
			Persona persona = new Persona();
			persona.setNomePersona(nome_ref_add);
			persona.setCognomePersona(cognome_ref_add);
			persona.setEmailPersona(email_ref_add);
	
			per.save(persona);			
			
			Referente referente = new Referente();
			referente.setAzienda(azienda_ref_add);
			referente.setIdRef(persona.getIdPersona());
			referente.setTelefonoRef(telefono_ref_add);
			
			ref.save(referente);
			
		}else {
			error = true;
		}	
		
		System.out.println("ciao add");
		
		m.addAttribute("error_insert_ref", error);
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
			
		/*Blocca l'accesso alla pagina se non loggato*/		
		if(!isLog(request)) 
			return "Login";
		
		/* Aggiorno la persona */
		per.updatePersona(id_ref_edit, cognome_ref_edit, nome_ref_edit, email_ref_edit);

		/* Aggiorno il referente */
		ref.updateReferente(id_ref_edit, telefono_ref_edit);
		
		System.out.println("ciao edit");
		
		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}
	
	
	@RequestMapping(value = "/GestioneReferenteDelete")
	public String getDeleteReferente(Model m, HttpServletRequest request,
			@RequestParam("id-ref-delete") int id_ref_delete) {
		
	
		/*Blocca l'accesso alla pagina se non loggato*/		
		if(!isLog(request)) 
			return "Login";
		
		/* Elimino pure il referente */
		ref.deleteById(id_ref_delete);

		/* Elimina la persona  */
		per.deleteById(id_ref_delete);

		System.out.println("ciao delete");
		
		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}
	
	
	/*Blocco accesso alla pagina se non loggato*/		
	private boolean isLog(HttpServletRequest request) {
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return false;
		}
		return true;
	}
}
