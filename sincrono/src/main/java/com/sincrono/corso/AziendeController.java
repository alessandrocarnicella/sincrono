package com.sincrono.corso;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sincrono.corso.model.Azienda;
import com.sincrono.corso.model.AziendaService;
import com.sincrono.corso.model.Commessa;
import com.sincrono.corso.model.CommessaService;
import com.sincrono.corso.model.Referente;
import com.sincrono.corso.model.ReferenteService;

@Controller
public class AziendeController {


	@Autowired
	AziendaService as;

	@Autowired
	ReferenteService ref;

	@Autowired
	CommessaService com;

	@RequestMapping(value = "/Aziende")
	public String getAziende(Model m, HttpServletRequest request) {

		/** Blocca l'accesso alla pagina */		
		if(!isLog(request))
			return "Login";

		m.addAttribute("list_az", as.findAll());

		return "Aziende";
	}

	@RequestMapping(value = "/GestioneAziende")
	public String getGestioneAziende(Model m, HttpServletRequest request) {

		/** Blocca l'accesso alla pagina */		
		if(!isLog(request)) 
			return "Login";

		request.getSession().setAttribute("errore_aziende", 0);
		m.addAttribute("list_az", as.findAll());

		return "GestioneAziende";
	}

	@RequestMapping(value = "/GestioneAziendeAdd")
	public String getGestioneAziendeAdd(Model m,HttpServletRequest request, 
			@RequestParam("nomeAzienda") String nomeAzienda,
			@RequestParam("emailAzienda") String emailAzienda, 
			@RequestParam("indirizzoAzienda") String indirizzoAzienda,
			@RequestParam("numdipAzienda") Integer numdipAzienda, 
			@RequestParam("pivaAzienda") String pivaAzienda,
			@RequestParam("societa") String societa,
			@RequestParam("telefonoAzienda") String telefonoAzienda,
			@RequestParam("statusAziendaAdd") byte statusAzienda) {

		/** Blocca l'accesso alla pagina */	
		if(!isLog(request))
			return "Login";

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

			request.getSession().setAttribute("errore_aziende", 2);
			m.addAttribute("list_az", as.findAll());
			return "GestioneAziende";
		}

		request.getSession().setAttribute("errore_aziende", 1);
		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}



	@RequestMapping(value = "/GestioneAziendeElimina")
	public String getGestioneAziendeElimina(Model m, HttpServletRequest request, 
			@RequestParam("nomeAziendaElimina") String nomeAzienda){

		/** Blocca l'accesso alla pagina */		
		if(!isLog(request))
			return "Login";

		/** Elimina l'azienda, richiede l'identificativo */
		as.deleteById(nomeAzienda);

		request.getSession().setAttribute("errore_aziende", 1);
		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}

	@RequestMapping(value = "/GestioneAziendeUpdate")
	public String getGestioneAziendeModifica(Model m, HttpServletRequest request,
			@RequestParam("nomeAzienda") String nomeAzienda,
			@RequestParam("emailAzienda") String emailAzienda, 
			@RequestParam("indirizzoAzienda") String indirizzoAzienda,
			@RequestParam("numdipAzienda") Integer numdipAzienda, 
			@RequestParam("pivaAzienda") String pivaAzienda,
			@RequestParam("societa") String societa,
			@RequestParam("telefonoAzienda") String telefonoAzienda,
			@RequestParam("status_azienda_edit") byte statusAzienda){

		/** Blocca l'accesso alla pagina */
		if(!isLog(request))
			return "Login";

		as.updateAzienda(nomeAzienda, emailAzienda, indirizzoAzienda, numdipAzienda, pivaAzienda, societa, statusAzienda, telefonoAzienda);

		request.getSession().setAttribute("errore_aziende", 1);
		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}

	@RequestMapping("/GestioneAziendePrint")
	public String getPrint(Model m, HttpServletRequest request,  
			@RequestParam("nomeAziendaPrint") String nomeAzienda,
			@RequestParam("emailAzienda") String emailAzienda, 
			@RequestParam("indirizzoAzienda") String indirizzoAzienda,
			@RequestParam("numdipAzienda") Integer numdipAzienda, 
			@RequestParam("pivaAzienda") String pivaAzienda,
			@RequestParam("societa") String societa ,
			@RequestParam("telefonoAzienda") String telefonoAzienda) {

		/*Blocco accesso alla pagina se non loggato*/		
		if(!isLog(request))
			return "Login";

		createPDF(nomeAzienda, emailAzienda, indirizzoAzienda,
				numdipAzienda, pivaAzienda, societa ,
				telefonoAzienda );

		request.getSession().setAttribute("errore_aziende", 3);
		m.addAttribute("list_az", as.findAll());

		return "GestioneAziende";

	}

	/** Questo metodo crea il PDF con i dettagli dell'azienda, del referente e delle commesse */

	private void createPDF(String nomeAzienda,String emailAzienda, String indirizzoAzienda,
			Integer numdipAzienda, String pivaAzienda, String societa ,
			String telefonoAzienda ) {
		Document document = new Document();
		try {
			String home = System.getProperty("user.home");
			home = home + "/Downloads/";
			PdfWriter.getInstance(document, new FileOutputStream(new File(home+"DETTAGLIO_AZIENDA_"+nomeAzienda+".pdf")));
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}

		document.open();

		Image image1;
		try {

			image1 = Image.getInstance("src/main/resources/static/images/logo.PNG");
			image1.setAbsolutePosition(450f, 750f);
			//Scale to new height and new width of image
			image1.scaleAbsolute(110, 80);
			//Add to document
			document.add(image1);
			document.add(new Paragraph("\n\n\n"));

		} catch (Exception e2) {
		}


		PdfPTable table = new PdfPTable(2);

		//---------------- add header -------
		Font font_header = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);

		PdfPCell header = new PdfPCell(new Phrase("Dettaglio Azienda",font_header));
		header.setColspan(2);
		header.setBackgroundColor(BaseColor.LIGHT_GRAY);
		header.setBorderWidth(1);
		header.setHorizontalAlignment(Element.ALIGN_CENTER);
		header.setPaddingTop(0f);
		header.setPaddingBottom(7f);
		table.addCell(header);

		//-----------------Table Cells Label/Value------------------

		Font font_label  = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);
		Font font_val = new Font(FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);
		table.setPaddingTop(0f);

		table.addCell(new Phrase("Nome",font_label));
		table.addCell(new Phrase(nomeAzienda,font_val));
		table.addCell(new Phrase("Società",font_label));
		table.addCell(new Phrase(societa,font_val));
		table.addCell(new Phrase("Email",font_label));
		table.addCell(new Phrase(emailAzienda,font_val));
		table.addCell(new Phrase("Telefono",font_label));
		table.addCell(new Phrase(telefonoAzienda,font_val));
		table.addCell(new Phrase("Partita Iva",font_label));
		table.addCell(new Phrase(pivaAzienda,font_val));
		table.addCell(new Phrase("Indirizzo Azienda",font_label));
		table.addCell(new Phrase(indirizzoAzienda,font_val));
		table.addCell(new Phrase("Numero Dipendenti",font_label));
		table.addCell(new Phrase(Integer.toString(numdipAzienda),font_val));

		try {
			document.add(table);
		} catch (DocumentException e1) {

		}

		//-------------------TABELLA REFERENTE--------------

		PdfPTable table2 = new PdfPTable(2);

		Azienda azienda = new Azienda();

		azienda.setNomeAzienda(nomeAzienda);
		azienda.setEmailAzienda(emailAzienda);
		azienda.setNumdipAzienda(numdipAzienda);
		azienda.setIndirizzoAzienda(indirizzoAzienda);
		azienda.setPivaAzienda(pivaAzienda);
		azienda.setSocieta(societa);
		azienda.setTelefonoAzienda(telefonoAzienda);

		try {

			int idRef = ref.findIdRefByAziendaName(azienda);
			Optional<Referente> referente = ref.findById(idRef);

			//---------------- add header -------
			//		Font font_header2 = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);

			PdfPCell header2 = new PdfPCell(new Phrase("Dettaglio Referente",font_header));
			header2.setColspan(2);
			header2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header2.setBorderWidth(1);
			header2.setHorizontalAlignment(Element.ALIGN_CENTER);
			header2.setPaddingTop(0f);
			header2.setPaddingBottom(7f);
			table2.addCell(header2);

			//-----------------Table Cells Label/Value------------------

			table2.addCell(new Phrase("Id Referente",font_label));
			table2.addCell(new Phrase(Integer.toString(referente.get().getIdRef()),font_val));
			table2.addCell(new Phrase("Nome",font_label));
			table2.addCell(new Phrase(referente.get().getPersona().getNomePersona(),font_val));
			table2.addCell(new Phrase("Cognome",font_label));
			table2.addCell(new Phrase(referente.get().getPersona().getCognomePersona(),font_val));
			table2.addCell(new Phrase("Email",font_label));
			table2.addCell(new Phrase(referente.get().getPersona().getEmailPersona(),font_val));
			table2.addCell(new Phrase("Telefono",font_label));
			table2.addCell(new Phrase(referente.get().getTelefonoRef(),font_val));

			table2.setSpacingAfter(30);
			table2.setSpacingBefore(30);	
			try {
				document.add(table2);
			} catch (DocumentException e) {		
			}	
		}catch(Exception e) {
		}

		//-------------------TABELLA COMMESSA--------------

		List<Integer> idCommessaList = com.findIdCommessaByAziendaName(azienda);

		for (Integer id : idCommessaList) {

			PdfPTable table3 = new PdfPTable(2);

			Optional<Commessa> commessa = com.findById(id);
			//---------------- add header -------

			PdfPCell header3 = new PdfPCell(new Phrase("Dettaglio Commessa n° " + Integer.toString(id),font_header));
			header3.setColspan(2);
			header3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header3.setBorderWidth(1);
			header3.setHorizontalAlignment(Element.ALIGN_CENTER);
			header3.setPaddingTop(0f);
			header3.setPaddingBottom(7f);
			table3.addCell(header3);

			//-----------------Table Cells Label/Value------------------

			table3.addCell(new Phrase("Id Commessa",font_label));
			table3.addCell(new Phrase(Integer.toString(commessa.get().getIdCommessa()),font_val));
			table3.addCell(new Phrase("Nome",font_label));
			table3.addCell(new Phrase(commessa.get().getNomeCommessa(),font_val));
			table3.addCell(new Phrase("Dipendente associato",font_label));
			table3.addCell(new Phrase(commessa.get().getPersona().getNomePersona() + " " + commessa.get().getPersona().getCognomePersona() ,font_val));
			table3.addCell(new Phrase("Tariffa cliente",font_label));
			table3.addCell(new Phrase(Double.toString(commessa.get().getTariffaCliente()),font_val));
			table3.addCell(new Phrase("Azienda",font_label));
			table3.addCell(new Phrase(commessa.get().getAzienda().getNomeAzienda(),font_val));
			table3.setSpacingAfter(10);
			table3.setSpacingBefore(10);
			try {
				document.add(table3);
			} catch (DocumentException e) {
			}
		}
		document.close();	


		String home = System.getProperty("user.home");
		home = home + "/Downloads/";
		File file = new File(home+"DETTAGLIO_AZIENDA_"+nomeAzienda+".pdf");
		if (file.toString().endsWith(".pdf"))
			try {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/** Ritorna true se l'utente in sessione è loggato **/		
	private boolean isLog(HttpServletRequest request) {
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return false;
		}
		return true;
	}



}
