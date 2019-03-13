package com.sincrono.corso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sincrono.corso.model.Azienda;
import com.sincrono.corso.model.AziendaService;

@Controller
public class AziendeController {

	
	@Autowired
	AziendaService as;
	
	@RequestMapping(value = "/Aziende")
	public String getAziende(Model m, HttpServletRequest request) {
				
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
		
		m.addAttribute("list_az", as.findAll());
		return "Aziende";
	}
	
	@RequestMapping(value = "/GestioneAziende")
	public String getGestioneAziende(Model m, HttpServletRequest request) {
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
		m.addAttribute("error_insert_azienda", false);

		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}
	
	@RequestMapping(value = "/GestioneAziendeAdd")
	public String getGestioneAziendeAdd(Model m,HttpServletRequest request, 
			@RequestParam("nomeAzienda") String nomeAzienda,
            @RequestParam("emailAzienda") String emailAzienda, @RequestParam("indirizzoAzienda") String indirizzoAzienda,
            @RequestParam("numdipAzienda") Integer numdipAzienda, @RequestParam("pivaAzienda") String pivaAzienda,
            @RequestParam("societa") String societa ,@RequestParam("telefonoAzienda") String telefonoAzienda,
            @RequestParam("statusAzienda") byte statusAzienda) {
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
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
	public String getGestioneAziendeElimina(Model m, HttpServletRequest request, 
			@RequestParam("nomeAzienda") String nomeAzienda){
		
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
			
		as.deleteById(nomeAzienda);
		
		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}
	
	@RequestMapping(value = "/GestioneAziendeUpdate")
	public String getGestioneAziendeModifica(Model m,HttpServletRequest request,
			@RequestParam("nomeAzienda") String nomeAzienda,
            @RequestParam("emailAzienda") String emailAzienda, @RequestParam("indirizzoAzienda") String indirizzoAzienda,
            @RequestParam("numdipAzienda") Integer numdipAzienda, @RequestParam("pivaAzienda") String pivaAzienda,
            @RequestParam("societa") String societa ,@RequestParam("telefonoAzienda") String telefonoAzienda,
            @RequestParam("statusAzienda") byte statusAzienda){
	
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
		
		as.updateAzienda(nomeAzienda, emailAzienda, indirizzoAzienda, numdipAzienda, pivaAzienda, societa, statusAzienda, telefonoAzienda);
		
		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}
	
	@RequestMapping("/GestioneAziendePrint")
	public String getPrint(Model m, HttpServletRequest request,  
			@RequestParam("nomeAzienda") String nomeAzienda,
			@RequestParam("emailAzienda") String emailAzienda, 
			@RequestParam("indirizzoAzienda") String indirizzoAzienda,
			@RequestParam("numdipAzienda") Integer numdipAzienda, 
			@RequestParam("pivaAzienda") String pivaAzienda,
			@RequestParam("societa") String societa ,
			@RequestParam("telefonoAzienda") String telefonoAzienda) {

		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
		
		Document document = new Document();
		try {
			String home = System.getProperty("user.home");
			home = home + "/Downloads/";
			PdfWriter.getInstance(document, new FileOutputStream(new File(home+"DETTAGLIO_AZIENDA.pdf")));
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}

		document.open();
		
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
		
		
		PdfPTable table2 = new PdfPTable(2);


		//---------------- add header -------
		Font font_header2 = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);

		PdfPCell header2 = new PdfPCell(new Phrase("Dettaglio Azienda",font_header));
		header2.setColspan(2);
		header2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		header2.setBorderWidth(1);
		header2.setHorizontalAlignment(Element.ALIGN_CENTER);
		header2.setPaddingTop(0f);
		header2.setPaddingBottom(7f);
		table2.addCell(header2);

			
		//-----------------Table Cells Label/Value------------------

		Font font_label2  = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);
		Font font_val2 = new Font(FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);
		table2.setSpacingAfter(30);
		table2.setSpacingBefore(30);

		table2.addCell(new Phrase("Nome",font_label));
		table2.addCell(new Phrase(nomeAzienda,font_val));
		table2.addCell(new Phrase("Società",font_label));
		table2.addCell(new Phrase(societa,font_val));
		table2.addCell(new Phrase("Email",font_label));
		table2.addCell(new Phrase(emailAzienda,font_val));
		table2.addCell(new Phrase("Telefono",font_label));
		table2.addCell(new Phrase(telefonoAzienda,font_val));
		table2.addCell(new Phrase("Partita Iva",font_label));
		table2.addCell(new Phrase(pivaAzienda,font_val));
		table2.addCell(new Phrase("Indirizzo Azienda",font_label));
		table2.addCell(new Phrase(indirizzoAzienda,font_val));
		table2.addCell(new Phrase("Numero Dipendenti",font_label));
		table2.addCell(new Phrase(Integer.toString(numdipAzienda),font_val));
		
		
		try {
			document.add(table);
			
			document.add(table2);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		document.close();

		m.addAttribute("list_az", as.findAll());

		return "GestioneAziende";

	}

	
	
	
	
	
}
