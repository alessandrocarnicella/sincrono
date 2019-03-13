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
import com.sincrono.corso.model.Categoria;
import com.sincrono.corso.model.CategoriaPK;
import com.sincrono.corso.model.CategoriaService;
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

	@RequestMapping(value = "/Utenti")
	public String getUtenti(Model m, HttpServletRequest request) {
		
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
		
		m.addAttribute("list_dip", dip.findAll());
		return "Utenti";
	}

	@RequestMapping(value = "/GestioneUtenti")
	public String getGestioneUtent(Model m,HttpServletRequest request) {
		
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
		
		m.addAttribute("list_dip", dip.findAll());
		return "GestioneUtenti";
	}

	@RequestMapping(value = "/GestioneUtentiAdd")
	public String getGestioneAziendeAdd(Model m, HttpServletRequest request,
			@RequestParam("cognomePersona") String cognomePersona,
			@RequestParam("nomePersona") String nomePersona, @RequestParam("emailPersona") String emailPersona,
			@RequestParam("passwordDip") String passwordDip, @RequestParam("nome_cat") String nome_cat,
			@RequestParam("ruolo_cat") String ruolo_cat ,@RequestParam("tariffaOraria") double tariffaOraria,
			@RequestParam("statusDip") byte statusDip) {

		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
		
		boolean error = false;

		if(per.findByEmailPersona(emailPersona).isEmpty()) {
		
			error = false;

			CategoriaPK categoriaPk = new  CategoriaPK();
			categoriaPk.setNomeCat(nome_cat);
			categoriaPk.setRuoloCat(ruolo_cat);


			Persona persona = new Persona();
			persona.setNomePersona(nomePersona);
			persona.setCognomePersona(cognomePersona);
			persona.setEmailPersona(emailPersona);

			per.save(persona);

			Dipendente dipendente =  new Dipendente();
			dipendente.setStatusDip(statusDip);
			dipendente.setPasswordDip(passwordDip);
			dipendente.setTariffaOraria(tariffaOraria);
			dipendente.setIdPersonadip(persona.getIdPersona());

			Categoria categoria = new Categoria();

			try {
				Optional<Categoria> opCat = cat.findById(categoriaPk);
				categoria = opCat.get();



			}catch(Exception e) {

				categoria.setId(categoriaPk);
				cat.save(categoria);
			}

			dipendente.setCategoria(categoria);

			dip.save(dipendente);

		}else {

			error = true;
			m.addAttribute("error_insert_persona", error);
			m.addAttribute("list_dip", dip.findAll());
			return "GestioneUtenti";
		}

		m.addAttribute("error_insert_persona", error);
		m.addAttribute("list_dip", dip.findAll());
		return "GestioneUtenti";
	}	
	
	@RequestMapping(value = "/GestioneUtentiElimina")
	public String getGestioneUtentiElimina(Model m, HttpServletRequest request,
			@RequestParam("idPersonadip") Integer idPersonadip){
		
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
		
		dip.deleteById(idPersonadip);
		per.deleteById(idPersonadip);
		
		m.addAttribute("list_dip", dip.findAll());
		
		return "GestioneUtenti";
	}
	
	@RequestMapping(value = "/GestioneUtenteUpdate")
	public String getGestioneAziendeModifica(Model m, HttpServletRequest request,
			@RequestParam("idpersona") Integer idPersonadip,
			@RequestParam("cognomePersona") String cognomePersona,
			@RequestParam("nomePersona") String nomePersona, 
			@RequestParam("emailPersona") String emailPersona,
			@RequestParam("passwordDip") String passwordDip, 
			@RequestParam("nome_cat") String nome_cat,
			@RequestParam("ruolo_cat") String ruolo_cat ,
			@RequestParam("tariffaOraria") double tariffaOraria,
			@RequestParam("statusDip") byte statusDip){
			
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
		
		CategoriaPK categoriaPk = new  CategoriaPK();
		categoriaPk.setNomeCat(nome_cat);
		categoriaPk.setRuoloCat(ruolo_cat);
		
		Categoria categoria = new Categoria();

		try {
			Optional<Categoria> opCat = cat.findById(categoriaPk);
			categoria = opCat.get();
		}catch(Exception e) {
			categoria.setId(categoriaPk);
			cat.save(categoria);
		}
		
		per.updatePersona(idPersonadip, cognomePersona, nomePersona, emailPersona);
		
		dip.updateDipendente(idPersonadip, passwordDip, nome_cat, ruolo_cat, tariffaOraria, statusDip);
		
		m.addAttribute("list_dip", dip.findAll());
		return "GestioneUtenti";
	}
	
}
