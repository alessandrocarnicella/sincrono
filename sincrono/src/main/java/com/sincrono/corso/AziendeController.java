package com.sincrono.corso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sincrono.corso.model.Azienda;
import com.sincrono.corso.model.AziendaService;

@Controller
public class AziendeController {

	
	@Autowired
	AziendaService as;
	
	@RequestMapping(value = "/Aziende")
	public String getAziende(Model m) {
		
		m.addAttribute("list_az", as.findAll());
		return "Aziende";
	}
	
	@RequestMapping(value = "/GestioneAziende")
	public String getGestioneAziende(Model m) {
		m.addAttribute("error_insert_azienda", false);

		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}
	
	@RequestMapping(value = "/GestioneAziendeAdd")
	public String getGestioneAziendeAdd(Model m, @RequestParam("nomeAzienda") String nomeAzienda,
            @RequestParam("emailAzienda") String emailAzienda, @RequestParam("indirizzoAzienda") String indirizzoAzienda,
            @RequestParam("numdipAzienda") Integer numdipAzienda, @RequestParam("pivaAzienda") String pivaAzienda,
            @RequestParam("societa") String societa ,@RequestParam("telefonoAzienda") String telefonoAzienda,
            @RequestParam("statusAzienda") byte statusAzienda) {
		
		boolean error = false;
	
		if(as.findByNomeAzienda(nomeAzienda).isEmpty() && as.findByEmailAzienda(emailAzienda).isEmpty() && as.findByPivaAzienda(pivaAzienda).isEmpty()) {
			
			error = false;
			
			Azienda azienda = new Azienda();
			azienda.setNomeAzienda(nomeAzienda);
			azienda.setTelefonoAzienda(telefonoAzienda);
			azienda.setEmailAzienda(emailAzienda);
			azienda.setIndirizzoAzienda(indirizzoAzienda);
			azienda.setSocieta(societa);
			azienda.setStatusAzienda(statusAzienda);
			azienda.setNumdipAzienda(numdipAzienda);
			azienda.setPivaAzienda(pivaAzienda);
			as.save(azienda);
	
		}else {
			
			error = true;
			
			m.addAttribute("error_insert_azienda", error);
			m.addAttribute("list_az", as.findAll());
			return "GestioneAziende";
		}
		
		m.addAttribute("error_insert_azienda", error);
		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}
	
	
	
	@RequestMapping(value = "/GestioneAziendeElimina")
	public String getGestioneAziendeElimina(Model m, @RequestParam("nomeAzienda") String nomeAzienda){
		
		//TODO controlla eliminazione nelle altre tabelle
			
		as.deleteById(nomeAzienda);
		
		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}
	
	@RequestMapping(value = "/GestioneAziendeUpdate")
	public String getGestioneAziendeModifica(Model m, @RequestParam("nomeAzienda") String nomeAzienda,
            @RequestParam("emailAzienda") String emailAzienda, @RequestParam("indirizzoAzienda") String indirizzoAzienda,
            @RequestParam("numdipAzienda") Integer numdipAzienda, @RequestParam("pivaAzienda") String pivaAzienda,
            @RequestParam("societa") String societa ,@RequestParam("telefonoAzienda") String telefonoAzienda,
            @RequestParam("statusAzienda") byte statusAzienda){
	
		as.updateAzienda(nomeAzienda, emailAzienda, indirizzoAzienda, numdipAzienda, pivaAzienda, societa, statusAzienda, telefonoAzienda);
		
		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}
	
	
}
