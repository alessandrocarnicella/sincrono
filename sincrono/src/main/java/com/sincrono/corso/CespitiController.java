package com.sincrono.corso;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sincrono.corso.model.Cespiti;
import com.sincrono.corso.model.CespitiService;
import com.sincrono.corso.model.Dipendente;

@Controller
public class CespitiController {

	@Autowired
	CespitiService ces;
	
	@RequestMapping(value = "/Cespiti")
	public String getUtenti(Model m, HttpServletRequest request) {

		/*Blocca l'accesso alla pagina se non loggato */		

		if(!isLog(request)) 
			return "Login";

		/* Aggiunge i parametri necessari in sessione */

		m.addAttribute("list_cespiti",ces.findAll());
		return "Cespiti";
	}
	
	@RequestMapping(value = "/CespitiElimina")
	public String getCespitiElimina(Model m, HttpServletRequest request,
			@RequestParam("idcespiti") Integer idcespiti){

		/*Blocca l'accesso alla pagina se non loggato*/	
		
		if(!isLog(request)) 
			return "Login";
		
		/* Elimina la commessa associata alla persona */

		ces.deleteById(idcespiti);
	
	
		m.addAttribute("list_cespiti",ces.findAll());
		return "Cespiti";
	}
	
	@RequestMapping(value = "/CespitiAdd")
	public String getGestioneUtentiAdd(Model m, HttpServletRequest request,
			@RequestParam("idcespiti") Integer  idcespiti,
			@RequestParam("annoFunzione") Integer annoFunzione, 
			@RequestParam("categoria") String categoria,
			@RequestParam("descrizione") String descrizione, 
			@RequestParam("dipendente") Dipendente dipendente) {

		/*Blocca l'accesso alla pagina se non loggato */		
		if(!isLog(request)) 
			return "Login";

		boolean error = false;

		/* Controlla l'esistena della persona tramite email */
		
		if(!ces.findById(idcespiti).isPresent()) {
			
			Cespiti cespite = new Cespiti();
			cespite.setAnnoFunzione(annoFunzione);
			cespite.setCategoria(categoria);
			cespite.setDescrizione(descrizione);
			cespite.setDipendente(dipendente);
			cespite.setIdcespiti(idcespiti);
			
			/* Aggiungo il cespite */
			ces.save(cespite);
		}else {
			error = true;
			
			/* Aggiunge i parametri necessari in sessione */
			
			m.addAttribute("error_insert_cespiti", error);
			m.addAttribute("list_dip", ces.findAll());
			return "GestioneUtenti";
		}
		error = false;
		
		/* Aggiunge i parametri necessari in sessione */
		
		m.addAttribute("error_insert_cespitia", error);
		m.addAttribute("list_dip", ces.findAll());
		return "Cespiti";
	}	
	
	@RequestMapping(value = "/CespitiUpdate")
	public String getCespitiUpdate(Model m, HttpServletRequest request,
			@RequestParam("idcespiti") Integer  idcespiti,
			@RequestParam("annoFunzione") Integer annoFunzione, 
			@RequestParam("categoria") String categoria,
			@RequestParam("descrizione") String descrizione, 
			@RequestParam("dipendente") Dipendente dipendente){

		/* Blocca l'accesso alla pagina se non loggato */	
		
		if(!isLog(request)) 
			return "Login";

		/* Aggiorno il cespite */
		
		ces.updateCespite(idcespiti, annoFunzione, categoria, descrizione, dipendente);

		/* Aggiunge i parametri necessari in sessione */
		
		m.addAttribute("list_dip", ces.findAll());
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
