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

		/** Blocca l'accesso alla pagina */
		if(!isLog(request))
			return "Login";

		Optional<Dipendente> dip = (Optional<Dipendente>) request.getSession().getAttribute("dipendente");
		
		/** Crea la lista di tutti i ril aggiornata */
		List<Ril> listRil = trovaTuttiRil(dip.get());
		
		/* Aggiunge i parametri necessari in sessione */
		request.getSession().setAttribute("errore_rapportini", 0);
		m.addAttribute("list_ril", listRil);
		return "Rapportini";
	}

	@RequestMapping(value = "/RapportiniInserisci")
	public String getRapportiniInserisci(Model m, HttpServletRequest request,
			@RequestParam("meseRil") String meseRil,
			@RequestParam("annoRil") Integer annoRil, 
			@RequestParam("oreCliente") double oreCliente,
			@RequestParam("oreFerie") double oreFerie, 
			@RequestParam("orePermessi") double orePermessi,
			@RequestParam("oreSede") double oreSede) {
		 
		/** Blocca l'accesso alla pagina */
		if(!isLog(request))
			return "Login";
	
		Optional<Dipendente> dip = (Optional<Dipendente>) request.getSession().getAttribute("dipendente");
	
		/** Crea un RilPK */
		RilPK rilPK = creaRilPK(dip.get().getIdPersonadip(),meseRil,annoRil);
	
		/* Controlla se un ril esiste gia  */
		if(rils.findById(rilPK).isPresent()) {
			List<Ril> listRil = trovaTuttiRil(dip.get());
			
			m.addAttribute("list_ril", listRil);
			request.getSession().setAttribute("errore_rapportini", 2);
			
			return "Rapportini";
		}
		
		Ril ril = creaRil(rilPK,oreCliente,oreFerie,orePermessi,oreSede);
		
		/** Aggiunge il Ril */
		rils.save(ril);  
		
		/** Crea la lista di tutti i ril aggiornata */
		List<Ril> listRil = trovaTuttiRil(dip.get());
		
		/* Aggiunge i parametri necessari in sessione */
		request.getSession().setAttribute("errore_rapportini", 1);
		m.addAttribute("list_ril", listRil);
		m.addAttribute("error_insert_ril", true);
		
		return "Rapportini";
	}


	@RequestMapping(value = "/RapportiniElimina")
	public String getRapportiniElimina(Model m, HttpServletRequest request,
			@RequestParam("meseRilElimina") String meseRil,
			@RequestParam("annoRilElimina") Integer annoRil) {

		/** Blocca l'accesso alla pagina */
		if(!isLog(request))
			return "Login";
		
		Optional<Dipendente> dip = (Optional<Dipendente>) request.getSession().getAttribute("dipendente");
		
		/** Crea un RilPK */
		RilPK rilPK = creaRilPK(dip.get().getIdPersonadip(),meseRil,annoRil);
		
		/** Elima un ril utilizzando il rilPK */
		rils.deleteById(rilPK);
		
		/** Crea la lista di tutti i ril */
		List<Ril> listRil = trovaTuttiRil(dip.get());
		
		/* Aggiunge i parametri necessari in sessione */
		request.getSession().setAttribute("errore_rapportini", 1);
		m.addAttribute("list_ril", listRil);
		m.addAttribute("error_insert_ril", true);
		
		return "Rapportini";
	}
	

	/** Questo metodo crea un Ril, ritorna il ril creato **/
	private Ril creaRil(RilPK rilPK, double oreCliente, double oreFerie, double orePermessi, double oreSede) {
		Ril ril = new Ril();
		ril.setId(rilPK);
		ril.setOreCliente(oreCliente);
		ril.setOreFerie(oreFerie);
		ril.setOrePermessi(orePermessi);
		ril.setOreSede(oreSede);
		return ril;
	}

	/** Questo metodo crea un RilPK, ritorna il rilPK creato **/
	private RilPK creaRilPK(int idPersonadip, String meseRil, Integer annoRil) {
		RilPK rilPK = new RilPK();
		rilPK.setIdPersonaril(idPersonadip);
		rilPK.setMeseRil(meseRil);
		rilPK.setAnnoRil(annoRil);
		return rilPK;
	}
	
	/** Ritorna true se l'utente in sessione è loggato **/
	private boolean isLog(HttpServletRequest request) {

		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return false;
		}
		return true;
	}

	/** Questo metodo crea una lista di tutti i RIL per il dipendente*/
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

}
