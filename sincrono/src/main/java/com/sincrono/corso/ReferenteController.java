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
	
	@RequestMapping(value = "/Referente")
	public String getQualcosa(Model m, HttpServletRequest request,
			@RequestParam("nomeAzienda") String nomeAzienda) {
		
		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request))
			return "Login";
		
		Optional<Azienda> a_opt = as.findById(nomeAzienda);
		Azienda a = (Azienda)a_opt.get();

		int id_ref = ref.findIdRefByAziendaName(a);	
		if(id_ref>0) {
			System.out.println(id_ref);
			request.getSession().setAttribute("has_ref", true);
			request.getSession().setAttribute("id_ref", id_ref);
			
			m.addAttribute("list_az", as.findAll());
			return "GestioneReferenteEdit";
		}else {
			request.getSession().setAttribute("has_ref", false);
			m.addAttribute("list_az", as.findAll());
			return "GestioneReferenteAdd";
		}

	}
	
	
	@RequestMapping(value = "/AddReferente")
	public String getAddReferente(Model m, HttpServletRequest request,
			@RequestParam("cognome_ref_add") String cognome_ref_add,
			@RequestParam("nome_ref_add") String nome_ref_add, 
			@RequestParam("email_ref_add") String email_ref_add,
			@RequestParam("azienda_ref_add") String azienda_string_ref_add,
			@RequestParam("telefono_ref_add") String telefono_ref_add) {
		
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
			referente.setPersona(persona);
			referente.setTelefonoRef(telefono_ref_add);
			
			ref.save(referente);

		}else {

			error = true;
			m.addAttribute("error_insert_persona", error);
			
			return "Referente";
		}
		
		
		error = false;
		m.addAttribute("error_insert_persona", error);
		m.addAttribute("list_az", as.findAll());
		return "Referente";
	}
	
	
	@RequestMapping(value = "/EditReferente")
	public String getEditReferente(Model m, HttpServletRequest request,
			@RequestParam("cognome_ref_edit") String cognome_ref_edit,
			@RequestParam("nome_ref_edit") String nome_ref_edit, 
			@RequestParam("email_ref_edit") String email_ref_edit,
			@RequestParam("azienda_ref_edit") String azienda_ref_edit,
			@RequestParam("telefono_ref_edit") String telefono_ref_edit,
			@RequestParam("id_ref_edit") int id_ref_edit) {
			
		
		return "Referente";
	}
	
	
	@RequestMapping(value = "/DeleteReferente")
	public String getDeleteReferente(			
			@RequestParam("aziendaref") String aziendaref,
			@RequestParam("idref") int idref) {
		
		/* RIMUVO LA COPPIA IDREF AZIENDA */
		
		return "Referente";
	}
	
	
	
	
	
	
	
	
	
	
	/*Blocco accesso alla pagina se non loggato*/		
	private boolean isLog(HttpServletRequest request) {
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return false;
		}
		return true;
	}
}
