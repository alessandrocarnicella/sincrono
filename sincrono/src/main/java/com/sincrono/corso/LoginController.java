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
import com.sincrono.corso.model.Mailer;

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

		boolean isLogged = false;
			
		/* Aggiunge i parametri necessari in sessione */

		request.getSession().setAttribute("isLogged", isLogged);
		m.addAttribute("error_login", 0);

		return "Login";
	}

	@RequestMapping(value = "/Andamento")
	public String getGrafici(Model m, HttpServletRequest request, 
			@RequestParam("nomeAziendaAndamento") String nomeAzienda){

		/* Blocca accesso alla pagina se non loggato */	

		if(!isLog(request)) 
			return "Login";

		Optional<Azienda> azienda = as.findById(nomeAzienda);
		List<Dipendente> listDipendenti = dip.findAll();
		List<Andamento> andamenti =  getAllAndamenti(listDipendenti);

		List<List<Double>> guadagnoTotaleAzienda = new ArrayList<List<Double>>();

		/* x ogni azienda prendo ogni anno e sommo i guadagni di tutti i mesi */

		List<Double> guadagnoTotalePerAnno = new ArrayList<Double>();
		for(int i=2015; i<=2019; i++) {
			guadagnoTotalePerAnno.add(guadagnoAnnuoAzienda(azienda.get(),i,listDipendenti,andamenti));	
		}
		guadagnoTotaleAzienda.add(guadagnoTotalePerAnno);

		
		List<Andamento> andamentiAzienda = new ArrayList<Andamento>();
		for(int i=0; i<andamenti.size(); i++) {
			if(andamenti.get(i).getAzienda().equals(azienda.get())) {
				andamentiAzienda.add(andamenti.get(i));
			}
		}

		
		/* Aggiunge i parametri necessari in sessione */

		m.addAttribute("error_login", false);
		m.addAttribute("nomeAzienda", nomeAzienda);
		request.getSession().setAttribute("list_andamenti", andamenti);  
		request.getSession().setAttribute("list_andamenti_azienda", andamentiAzienda);  
		request.getSession().setAttribute("list_guadagno_totale_azienda", guadagnoTotaleAzienda);	 

		return "Grafici";
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


		/* Aggiunge i parametri necessari in sessione */

		m.addAttribute("error_login", 0);
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
			m.addAttribute("error_login", 3); // utente non trovato 
			isLogged =  false;
			request.getSession().setAttribute("isLogged", isLogged);
			return "Login";
		}
		else {
			byte zero = 0;
			if(dip.findById(dipId).get().getStatusDip() == zero) {
				m.addAttribute("error_login", 1); //disattivato
				isLogged =  false;
				request.getSession().setAttribute("isLogged", isLogged);
				return "Login";
			}else {
				m.addAttribute("error_login", 2); //attivo
			}
		}

		/* Se l'id non è 0 si ha un utente -> ricava il dipendente dall'id */
		Optional<Dipendente> dipendente;
		dipendente = dip.findById(dipId);



		List<Azienda> listAziende = as.findAll();
		List<Dipendente> listDipendenti = dip.findAll();
		List<Andamento> andamenti =  getAllAndamenti(listDipendenti);

		List<List<Double>> guadagnoTotaleAziende = new ArrayList<List<Double>>();


		/* Aggiunge i parametri necessari in sessione */

		m.addAttribute("error_login", 0);
		isLogged = true;

		request.getSession().setAttribute("list_dipendenti",  listDipendenti);   
		request.getSession().setAttribute("list_aziende", listAziende);	 
		request.getSession().setAttribute("list_andamenti", andamenti);   
		request.getSession().setAttribute("isLogged", isLogged);	
		request.getSession().setAttribute("dipendente", dipendente);		
		return "Dashboard";
	}

	@RequestMapping(value = "/Logout")
	public String getLogout(Model m, HttpServletRequest request) {

		/* Aggiunge i parametri necessari in sessione */

		request.getSession().setAttribute("isLogged", false);	
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("categoria");
		m.addAttribute("error_login", 0);
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

		try {
			int dip_id = dip.existUserByEmail(recupero_email);
			String new_password = getRandomPsw();

			/* Aggiorna la nuova password */

			dip.updatePswDip(dip_id, new_password);
			Mailer mail = new Mailer();
			
			Dipendente dipendente =  dip.findById(dip_id).get();
		
			String msg = "Ciao "+dipendente.getPersona().getNomePersona()+ " "+ dipendente.getPersona().getCognomePersona()+",\n\n la tua nuova password è: "+new_password+ "\n\n";
			
			mail.send("nomec443@gmail.com","sincrono",dipendente.getPersona().getEmailPersona(),"Sincronia recupero PSW",msg);
			
			/* Aggiunge i parametri necessari in sessione */

			m.addAttribute("new_psw", "Password inviata a "+dipendente.getPersona().getEmailPersona());
		}catch(Exception e) {
			return "ForgotPassword";
		}
		return "Login";
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

	/** Ritorna true se l'utente in sessione è loggato **/
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
				try {
					int idCommessa  = coms.findIdRefByPersona(listRil.get(j).getPersona());
					Optional<Commessa> commessa = coms.findById(idCommessa);
					double guadagno;

					Optional<Dipendente> dipendente1 = dip.findById(listRil.get(j).getPersona().getIdPersona());

					guadagno = (commessa.get().getTariffaCliente()-dipendente1.get().getTariffaOraria())*listRil.get(j).getOreCliente();

					andamenti.add(new Andamento(
							commessa.get().getNomeCommessa(),
							commessa.get().getTariffaCliente(),
							commessa.get().getAzienda(),
							dipendente1.get(),
							guadagno,
							dipendente1.get().getTariffaOraria(),
							listRil.get(j).getOreCliente(),
							listRil.get(j).getId()));

				}catch(Exception e) {
				}
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

	private Double guadagnoAnnuoAzienda(Azienda azienda, int anno, List<Dipendente> listDipendenti, List<Andamento> andamenti){
		Double guadagnoAziendaAnno = 0.0;

		for(int x = 0; x<andamenti.size(); x++) {
			if(andamenti.get(x).getAzienda().equals(azienda) && andamenti.get(x).getRilPk().getAnnoRil() == anno) {
				guadagnoAziendaAnno = guadagnoAziendaAnno + andamenti.get(x).getGuadagno();
			}
		}
		return guadagnoAziendaAnno;
	}

}
