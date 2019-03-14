package com.sincrono.corso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sincrono.corso.model.Dipendente;
import com.sincrono.corso.model.Ril;
import com.sincrono.corso.model.RilPK;
import com.sincrono.corso.model.RilService;

@Controller
public class RapportiniController {

	@Autowired
	RilService rils;


	@RequestMapping(value = "/Rapportini")
	public String getRapportini(Model m, HttpServletRequest request) {

		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request))
			return "Login";

		Optional<Dipendente> dip = (Optional<Dipendente>) request.getSession().getAttribute("dipendente");
		List<Ril> listRil = trovaTuttiRil(dip.get());
		
		m.addAttribute("list_ril", listRil);
		return "Rapportini";
	}

	private List<Ril> trovaTuttiRil(Dipendente dip) {

		List<Ril> listRil = new ArrayList<>();
		int i = 0;
		List<String> mesi = Arrays.asList("gennaio", "febbraio", "marzo", "aprile", "maggio",
				"giugno", "lugio", "agosto", "settembre", "ottobre", "novembre", "dicembre");
		
		for(int y=2015; y<=2019; y++){
			for(int x=0; x<12; x++) {
				
				RilPK rilPK = new RilPK();
				rilPK.setIdPersonaril(dip.getIdPersonadip());
				rilPK.setMeseRil(mesi.get(x));
				rilPK.setAnnoRil(y);
				
				try {
					Optional<Ril> ril = rils.findById(rilPK);
					listRil.add(i, ril.get());
					i++;
				}catch(Exception e) {
					
				}	
			}
		}
		return listRil;
	}

	@RequestMapping(value = "/RapportiniInserisci")
	public String getRapportiniInserisci(Model m, HttpServletRequest request,
			@RequestParam("meseRil") String meseRil,
			@RequestParam("annoRil") Integer annoRil, 
			@RequestParam("oreCliente") double oreCliente,
			@RequestParam("oreFerie") double oreFerie, 
			@RequestParam("orePermessi") double orePermessi,
			@RequestParam("oreSede") double oreSede) {
		 
		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request))
			return "Login";

		
		Optional<Dipendente> dip = (Optional<Dipendente>) request.getSession().getAttribute("dipendente");
	
		RilPK rilPK = new RilPK();
		rilPK.setIdPersonaril(dip.get().getIdPersonadip());
		rilPK.setMeseRil(meseRil);
		rilPK.setAnnoRil(annoRil);
		
		if(rils.findById(rilPK).isPresent()) {
			
			m.addAttribute("error_insert_ril", true);
			return "Rapportini";
		}
		
		Ril ril = new Ril();
		ril.setId(rilPK);
		ril.setOreCliente(oreCliente);
		ril.setOreFerie(oreFerie);
		ril.setOrePermessi(orePermessi);
		ril.setOreSede(oreSede);
		rils.save(ril);
		
		List<Ril> listRil = trovaTuttiRil(dip.get());
		m.addAttribute("list_ril", listRil);
		m.addAttribute("error_insert_ril", true);
		return "Rapportini";
	}


	@RequestMapping(value = "/RapportiniElimina")
	public String getRapportiniElimina(Model m, HttpServletRequest request,
			@RequestParam("meseRilElimina") String meseRil,
			@RequestParam("annoRilElimina") Integer annoRil) {

		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request))
			return "Login";
		
		Optional<Dipendente> dip = (Optional<Dipendente>) request.getSession().getAttribute("dipendente");
		
		RilPK rilPK = new RilPK();
		rilPK.setIdPersonaril(dip.get().getIdPersonadip());
		rilPK.setMeseRil(meseRil);
		rilPK.setAnnoRil(annoRil);
		
		rils.deleteById(rilPK);
		
		List<Ril> listRil = trovaTuttiRil(dip.get());
		m.addAttribute("list_ril", listRil);
		m.addAttribute("error_insert_ril", true);
		return "Rapportini";
	}

	@RequestMapping(value = "/GestioneCommesseDipendenti")
	public String getGestioneCommesseDipendenti(Model m, HttpServletRequest request) {

		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request))
			return "Login";

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
