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
	public String getRapportiniInserisci(Model m, HttpServletRequest request) {

		// mi recupero la persona 
		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request))
			return "Login";

		// lista tutti rapportini persona 
		return "Rapportini";
	}


	@RequestMapping(value = "/RapportiniElimina")
	public String getRapportiniElimina(Model m, HttpServletRequest request) {

		// mi recupero la persona 
		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request))
			return "Login";

		// lista tutti rapportini persona 
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
