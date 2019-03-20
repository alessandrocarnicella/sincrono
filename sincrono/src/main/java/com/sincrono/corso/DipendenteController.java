package com.sincrono.corso;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sincrono.corso.model.Categoria;
import com.sincrono.corso.model.CategoriaPK;
import com.sincrono.corso.model.CategoriaService;
import com.sincrono.corso.model.CommessaService;
import com.sincrono.corso.model.Dipendente;
import com.sincrono.corso.model.DipendenteService;
import com.sincrono.corso.model.Persona;
import com.sincrono.corso.model.PersonaService;

@Controller
public class DipendenteController {

	@Autowired
	DipendenteService dip;

	@Autowired
	CategoriaService cat;

	@Autowired
	PersonaService per;

	@Autowired
	CommessaService coms;

	@RequestMapping(value = "/Utenti")
	public String getUtenti(Model m, HttpServletRequest request) {

		/*Blocca l'accesso alla pagina se non loggato */		
		
		if(!isLog(request)) 
			return "Login";

		/* Aggiunge i parametri necessari in sessione */
		
		m.addAttribute("list_dip", dip.findAll());
		return "Utenti";
	}

	@RequestMapping(value = "/GestioneUtenti")
	public String getGestioneUtent(Model m,HttpServletRequest request) {

		/* Blocca l'accesso alla pagina se non loggato */	
		
		if(!isLog(request))
			return "Login";

		/* Aggiunge i parametri necessari in sessione */
		request.getSession().setAttribute("errore_dipendenti", 0);
		m.addAttribute("list_dip", dip.findAll());
		return "GestioneUtenti";
	}

	@RequestMapping(value = "/GestioneUtentiAdd")
	public String getGestioneUtentiAdd(Model m, HttpServletRequest request,
			@RequestParam("cognomePersona") String cognomePersona,
			@RequestParam("nomePersona") String nomePersona, 
			@RequestParam("emailPersona") String emailPersona,
			@RequestParam("password_dip") String passwordDip, 
			@RequestParam("nome_cat") String nome_cat,
			@RequestParam("ruolo_cat") String ruolo_cat,
			@RequestParam("tariffaoraria") double tariffaOraria,
			@RequestParam("statusDip") byte statusDip) {

		/*Blocca l'accesso alla pagina se non loggato */		
		if(!isLog(request)) 
			return "Login";

		boolean error = false;

		/* Controlla l'esistena della persona tramite email */
		
		if(per.findByEmailPersona(emailPersona).isEmpty()) {

			CategoriaPK categoriaPk = new  CategoriaPK();
			categoriaPk.setNomeCat(nome_cat);
			categoriaPk.setRuoloCat(ruolo_cat);

			/* Se non esiste crea la perosna */
			
			Persona persona = new Persona();
			persona.setNomePersona(nomePersona);
			persona.setCognomePersona(cognomePersona);
			persona.setEmailPersona(emailPersona);
			
			/* Aggiungo la persona */
			per.save(persona);

			/* Crea il dipendente associato alla persona */
			
			Dipendente dipendente =  new Dipendente();
			dipendente.setStatusDip(statusDip);
			dipendente.setPasswordDip(passwordDip);
			dipendente.setTariffaOraria(tariffaOraria);
			dipendente.setIdPersonadip(persona.getIdPersona());

			Categoria categoria = new Categoria();
			
			/* Controlla se esiste la categoria e se non esiste la crea */
			
			try {
				Optional<Categoria> opCat = cat.findById(categoriaPk);
				categoria = opCat.get();
			}catch(Exception e) {
				categoria.setId(categoriaPk);
				cat.save(categoria);
			}

			dipendente.setCategoria(categoria);
			
			/* Aggiungo la persona */
			dip.save(dipendente);
		}else {
			error = true;
			
			/* Aggiunge i parametri necessari in sessione */
			request.getSession().setAttribute("errore_dipendenti", 2);
			m.addAttribute("error_insert_persona", error);
			m.addAttribute("list_dip", dip.findAll());
			return "GestioneUtenti";
		}
		error = false;
		
		/* Aggiunge i parametri necessari in sessione */
		request.getSession().setAttribute("errore_dipendenti", 1);
		m.addAttribute("error_insert_persona", error);
		m.addAttribute("list_dip", dip.findAll());
		return "GestioneUtenti";
	}	

	@RequestMapping(value = "/GestioneUtentiElimina")
	public String getGestioneUtentiElimina(Model m, HttpServletRequest request,
			@RequestParam("idPersonadip") Integer idPersonadip){

		/*Blocca l'accesso alla pagina se non loggato*/	
		
		if(!isLog(request)) 
			return "Login";

		
		Optional<Persona> pers = (Optional<Persona>)per.findById(idPersonadip);
		
		/* Elimina la commessa associata alla persona */
		try {
			int idCommessa = coms.findIdRefByPersona(pers.get());
			coms.deleteById(idCommessa);
		}catch(Exception e) {

		}

		/* Elimina il dipendente associato alla persona */
		dip.deleteById(idPersonadip);
		
		/* Elimina la persona  */
		per.deleteById(idPersonadip);

		/* Aggiunge i parametri necessari in sessione */
		request.getSession().setAttribute("errore_dipendenti", 1);
		m.addAttribute("list_dip", dip.findAll());
		return "GestioneUtenti";
	}

	@RequestMapping(value = "/GestioneUtenteUpdate")
	public String getGestioneAziendeModifica(Model m, HttpServletRequest request,
			@RequestParam("idpersona") Integer idPersonadip,
			@RequestParam("cognomePersona") String cognomePersona,
			@RequestParam("nomePersona") String nomePersona, 
			@RequestParam("emailPersona") String emailPersona,
			@RequestParam("password_dip") String passwordDip, 
			@RequestParam("nome_cat") String nome_cat,
			@RequestParam("ruolo_cat") String ruolo_cat ,
			@RequestParam("tariffaoraria") double tariffaOraria,
			@RequestParam("status_dip_edit") byte statusDip){

		/*Blocca l'accesso alla pagina se non loggato*/	
		
		if(!isLog(request)) 
			return "Login";

		CategoriaPK categoriaPk = new  CategoriaPK();
		categoriaPk.setNomeCat(nome_cat);
		categoriaPk.setRuoloCat(ruolo_cat);

		Categoria categoria = new Categoria();

		/* Controlla se esiste la categoria e se non esiste la crea */
		try {
			Optional<Categoria> opCat = cat.findById(categoriaPk);
			categoria = opCat.get();
		}catch(Exception e) {
			categoria.setId(categoriaPk);
			cat.save(categoria);
		}
		
		/* Aggiorno la persona */
		per.updatePersona(idPersonadip, cognomePersona, nomePersona, emailPersona);

		/* Aggiorno il dipendente */
		dip.updateDipendente(idPersonadip, passwordDip, nome_cat, ruolo_cat, tariffaOraria, statusDip);

		/* Aggiunge i parametri necessari in sessione */
		request.getSession().setAttribute("errore_dipendenti", 1);
		m.addAttribute("list_dip", dip.findAll());
		return "GestioneUtenti";
	}

	/*Blocco accesso alla pagina se non loggato*/
	
	private boolean isLog(HttpServletRequest request) {
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return false;
		}
		return true;
	}

}
