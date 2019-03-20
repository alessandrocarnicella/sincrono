package com.sincrono.corso;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sincrono.corso.model.Cespiti;
import com.sincrono.corso.model.CespitiService;
import com.sincrono.corso.model.Dipendente;
import com.sincrono.corso.model.DipendenteService;

@Controller
public class CespitiController {

	@Autowired
	CespitiService ces;
	
	@Autowired
	DipendenteService des;
	
	@RequestMapping(value = "/Cespiti")
	public String getUtenti(Model m, HttpServletRequest request) {

		/*Blocca l'accesso alla pagina se non loggato */		

		if(!isLog(request)) 
			return "Login";

		/* Aggiunge i parametri necessari in sessione */

		request.getSession().setAttribute("errore_cespiti", 0);
		m.addAttribute("list_cespiti",ces.findAll());
		m.addAttribute("list_dip", des.findAll());

		return "Cespiti";
	}
	@Transactional
	@RequestMapping(value = "/CespitiElimina")
	public String getCespitiElimina(Model m, HttpServletRequest request,
			@RequestParam("idcespiti") Integer idcespiti){

		/*Blocca l'accesso alla pagina se non loggato*/	
		
		if(!isLog(request)) 
			return "Login";
		
		/* Elimina la commessa associata alla persona */

		ces.deleteById(idcespiti);
		
		request.getSession().setAttribute("errore_cespiti", 1);
		m.addAttribute("list_dip", des.findAll());
		m.addAttribute("list_cespiti",ces.findAll());
		return "Cespiti";
	}
	@Transactional
	@RequestMapping(value = "/CespitiAdd")
	public String getGestioneUtentiAdd(Model m, HttpServletRequest request,
			@RequestParam("annofunzione-add") Integer annoFunzione, 
			@RequestParam("categoria-add") String categoria,
			@RequestParam("descrizione-add") String descrizione, 
			@RequestParam (value="dipendente-add", required = false) Dipendente dipendente) {

		/*Blocca l'accesso alla pagina se non loggato */		
		if(!isLog(request)) 
			return "Login";

		boolean error = false;

		/* Controlla l'esistena della persona tramite email */
		
			
			Cespiti cespite = new Cespiti();
			cespite.setAnnoFunzione(annoFunzione);
			cespite.setCategoria(categoria);
			cespite.setDescrizione(descrizione);
			if (dipendente!=null)
				{
				cespite.setDipendente(dipendente);
				}else {
					cespite.setDipendente(null);
				}
			
			/* Aggiungo il cespite */
			ces.save(cespite);
		
			
			/* Aggiunge i parametri necessari in sessione */
			request.getSession().setAttribute("errore_cespiti", 1);
			m.addAttribute("error_insert_cespiti", error);
			m.addAttribute("list_cespiti", ces.findAll());
			m.addAttribute("list_dip", des.findAll());

			return "Cespiti";
	}	
	@Transactional
	@RequestMapping(value = "/CespitiUpdate")
	public String getCespitiUpdate(Model m, HttpServletRequest request,
			@RequestParam("idces") Integer  idcespiti,
			@RequestParam("annoFunzione") Integer annoFunzione,
			@RequestParam("categoria") String categoria,
			@RequestParam("descrizione") String descrizione, 
			@RequestParam (value="dipendente", required = false) Dipendente dipendente){

		/* Blocca l'accesso alla pagina se non loggato */	
		
		if(!isLog(request)) {
			return "Login";
		}
		
		ces.updateCespite(idcespiti, annoFunzione, categoria, descrizione, dipendente);
		
		/* Aggiunge i parametri necessari in sessione */
		request.getSession().setAttribute("errore_cespiti", 1);
		m.addAttribute("list_dip", des.findAll());
		m.addAttribute("list_cespiti", ces.findAll());
		return "Cespiti";
	}
	
	
	
	/*Blocca l'accesso alla pagina se non loggato*/
	
	private boolean isLog(HttpServletRequest request) {
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return false;
		}
		return true;
	}
}
