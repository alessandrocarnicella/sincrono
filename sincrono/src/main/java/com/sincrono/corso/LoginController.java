package com.sincrono.corso;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sincrono.corso.model.Andamento;
import com.sincrono.corso.model.Azienda;
import com.sincrono.corso.model.AziendaService;
import com.sincrono.corso.model.Commessa;
import com.sincrono.corso.model.CommessaService;
import com.sincrono.corso.model.Dipendente;
import com.sincrono.corso.model.DipendenteService;
import com.sincrono.corso.model.Persona;
import com.sincrono.corso.model.PersonaService;
import com.sincrono.corso.model.Ril;
import com.sincrono.corso.model.RilPK;
import com.sincrono.corso.model.RilService;


@Controller
public class LoginController {

	@Autowired
	DipendenteService dip;

	@Autowired
	CommessaService coms;

	@Autowired
	AziendaService as;

	@Autowired
	PersonaService pers;

	@Autowired
	RilService rils;

	@RequestMapping(value = "/")
	public String getHome(Model m, HttpServletRequest request) {

		/* Crea la sessione */

		HttpSession session = request.getSession();
		boolean isLogged = false;

		/* Aggiunge i parametri necessari in sessione */

		session.setAttribute("isLogged", isLogged);
		m.addAttribute("error_login", false);

		return "Login";
	}

	@RequestMapping(value = "/DashboardHome")
	public String getDashboardHome(Model m, HttpServletRequest request) {

		/* Blocca accesso alla pagina se non loggato */	

		if(!isLog(request)) 
			return "Login";

		List<Azienda> listAziende = as.findAll();
		List<Dipendente> listDipendenti = dip.findAll();
		List<Andamento> andamenti =  getAllAndamenti(listDipendenti);
		
		List<List<Double>> guadagnoTotaleAziende = new ArrayList<List<Double>>();

		/* x ogni azienda prendo ogni anno e sommo i guadagni di tutti i mesi */

		
		for (int k=0; k<listAziende.size(); k++ ) {
			List<Double> guadagnoTotalePerAnno = new ArrayList<Double>();
			for(int i=2015; i<=2019; i++) {
				guadagnoTotalePerAnno.add(guadagnoAnnuoAzienda(listAziende.get(k),i,listDipendenti));	
			}
			guadagnoTotaleAziende.add(guadagnoTotalePerAnno);
		}
				 
		/* Aggiunge i parametri necessari in sessione */

		m.addAttribute("error_login", false);
		request.getSession().setAttribute("list_guadagno_totale_aziende", guadagnoTotaleAziende);	
		request.getSession().setAttribute("list_andamenti", andamenti);   
		
		return "Dashboard";
	}


	@RequestMapping(value = "/Dashboard")
	public String getDashboard(Model m, HttpServletRequest request, 
			@RequestParam("email") String email,
			@RequestParam("password") String password) {	

		boolean isLogged;
		int dipId = 0;

		isLogged = (boolean) request.getSession().getAttribute("isLogged");
		/* Se esiste un utente ritorna il suo id */
		try {
			dipId = dip.existUser(password, email);
		}

		/* Se non esiste un utente mette l'id a 0 */
		catch(Exception e) {
			dipId = 0;
		}

		/* Se l'id è 0 non si ha un utente -> il login non è andato a buon fine */

		if(dipId == 0) {
			m.addAttribute("error_login", true);
			isLogged =  false;
			request.getSession().setAttribute("isLogged", isLogged);
			return "Login";
		}

		/* Se l'id non è 0 si ha un utente -> ricava il dipendente dall'id */
		Optional<Dipendente> dipendente;
		dipendente = dip.findById(dipId);
		
		
		
		List<Azienda> listAziende = as.findAll();
		List<Dipendente> listDipendenti = dip.findAll();
		List<Andamento> andamenti =  getAllAndamenti(listDipendenti);
		
		List<List<Double>> guadagnoTotaleAziende = new ArrayList<List<Double>>();

		/* x ogni azienda prendo ogni anno e sommo i guadagni di tutti i mesi */

		
		for (int k=0; k<listAziende.size(); k++ ) {
			List<Double> guadagnoTotalePerAnno = new ArrayList<Double>();
			for(int i=2015; i<=2019; i++) {
				guadagnoTotalePerAnno.add(guadagnoAnnuoAzienda(listAziende.get(k),i,listDipendenti));	
			}
			guadagnoTotaleAziende.add(guadagnoTotalePerAnno);
		}
		
		


		/* Aggiunge i parametri necessari in sessione */

		m.addAttribute("error_login", false);
		isLogged = true;
		
		request.getSession().setAttribute("list_dipendenti",  listDipendenti);   
		request.getSession().setAttribute("list_aziende", listAziende);	 
		request.getSession().setAttribute("list_guadagno_totale_aziende", guadagnoTotaleAziende);	
		request.getSession().setAttribute("list_andamenti", andamenti);   
		request.getSession().setAttribute("isLogged", isLogged);	
		request.getSession().setAttribute("dipendente", dipendente);		
		return "Dashboard";
	}

	@RequestMapping(value = "/Logout")
	public String getLogout(Model m, HttpServletRequest request) {

		request.getSession().invalidate();
		request.getSession().removeAttribute("isLogged");
		request.getSession().removeAttribute("dipendente");

		m.addAttribute("error_login", false);
		return "Login";
	}


	/* Genera una passwor random */

	protected String getRandomPsw() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	@RequestMapping(value = "/ForgotPassword")
	public String getForgotPassword() {
		return "ForgotPassword";
	}

	@RequestMapping(value = "/RecuperaPsw")
	public String getLoginNewPsw(Model m,@RequestParam("recupero_email") String recupero_email) {

		int dip_id = dip.existUserByEmail(recupero_email);
		String new_password = getRandomPsw();

		/* Aggiorna la nuova password */

		dip.updatePswDip(dip_id, new_password);


		/* Aggiunge i parametri necessari in sessione */

		m.addAttribute("new_psw", new_password);

		return "Login";
	}

	/* Crea una lista di Ril TODO: ce un problema  */

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

	/* Bloca l'accesso alla pagina se non loggato */	

	private boolean isLog(HttpServletRequest request) {
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return false;
		}
		return true;
	}

	
	private List<Andamento> getAllAndamenti(List<Dipendente> listDipendenti){
		List<Andamento> andamenti =  new ArrayList<>();
		
		for(int k=0; k<listDipendenti.size(); k++) {
			List<Ril> listRil =  trovaTuttiRil(listDipendenti.get(k));
			for (int j=0; j<listRil.size(); j++) {

				int idCommessa  = coms.findIdRefByPersona(listRil.get(j).getPersona());
				Optional<Commessa> commessa = coms.findById(idCommessa);
				double guadagno;

				Optional<Dipendente> dipendente1 = dip.findById(listRil.get(j).getPersona().getIdPersona());

				guadagno = (commessa.get().getTariffaCliente()-dipendente1.get().getTariffaOraria())*listRil.get(j).getOreCliente();

				andamenti.add(new Andamento(commessa.get().getNomeCommessa(),commessa.get().getTariffaCliente(),
						commessa.get().getAzienda(),dipendente1.get(),guadagno,dipendente1.get().getTariffaOraria(),
						listRil.get(j).getOreCliente(),listRil.get(j).getId()));
			}
		}


		/* Ordina lista Andamenti */

		Collator collator = Collator.getInstance(Locale.US);
		if (!andamenti.isEmpty()) {
			Collections.sort(andamenti, new Comparator<Andamento>() {
				@Override
				public int compare(Andamento c1, Andamento c2) {
					return collator.compare(c1.getAzienda().getNomeAzienda(), c2.getAzienda().getNomeAzienda());
				}
			});
		}
		return andamenti;
	}


	private Double guadagnoAnnuoAzienda(Azienda azienda, int anno, List<Dipendente> listDipendenti){

		List<Andamento> andamenti = getAllAndamenti(listDipendenti);
		Double guadagnoAziendaAnno = 0.0;

		for(int x = 0; x<andamenti.size(); x++) {
			if(andamenti.get(x).getAzienda().equals(azienda) && andamenti.get(x).getRilPk().getAnnoRil() == anno) {
				guadagnoAziendaAnno = guadagnoAziendaAnno + andamenti.get(x).getGuadagno();
			}
		}
		return guadagnoAziendaAnno;
	}

}
