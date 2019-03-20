$(document).ready(function(){

	/*===================================================================== RICERCHE =====================================================================*/
	
	// FUNZIONE PER RICERCA PAGINA AZIENDA
	$("#searchAziende").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$(".fieldNameAzienda").filter(function() {
			$(this).closest(".stretch-card").toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});

	// FUNZIONE PER RICERCA PAGINA GESTIONE AZIENDE
	$("#searchGestioneAziende").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#table-gestione-aziende tr").not('thead tr').filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
		  

	//FUNZIONE PER RICERCA PAGINA CESPITI
	$("#searchCespiti").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#table-gestione-cespiti tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	//FUNZIONE PER RICERCA PAGINA RAPPORTINI  
	$("#searchRapportini").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#table-gestione-ril tr").not('thead tr').filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	// FUNZIONE PER RICERCA IN GESTIONE UTENTI
	$("#searchGestioneUtenti").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#table-gestione-utenti tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	// FUNZIONE PER RICERCA IN PAGINA UTENTI
	$("#searchDipendenti").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$(".fieldName").filter(function() {
			$(this).closest(".stretch-card").toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	
	// FUNZIONE PER RICERCA NELLA PAGINA GESTIONE COMMESSE
	$("#searchGestioneCommesse").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#table-gestione-commesse tr").not('thead tr').filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});	
	/*===================================================================== MODIFICHES =====================================================================*/
	// RERCUPERA I CAMPI DAI DATANAME E RIEMPIE LA FORM EDIT AZIENDA
	$(".edit-aziende").on("click", function() {
		var namen = $(this).data("namen");
		var email = $(this).data("email");
		var address = $(this).data("address");
		var numdip = $(this).data("numdip");
		var piva = $(this).data("piva");
		var societa = $(this).data("societa");
		var telefono = $(this).data("telefono");
		var status = $(this).data("status");
		
		console.log(status);
		$("#modal-edit-aziende input[name=nomeAzienda]").val(namen);
		$("#modal-edit-aziende input[name=emailAzienda]").val(email);
		$("#modal-edit-aziende input[name=indirizzoAzienda]").val(address);
		$("#modal-edit-aziende input[name=numdipAzienda]").val(numdip);
		$("#modal-edit-aziende input[name=pivaAzienda]").val(piva);
		$("#modal-edit-aziende input[name=societa]").val(societa);
		$("#modal-edit-aziende input[name=statusAziendaEdit]").val(status);
		$("#modal-edit-aziende input[name=telefonoAzienda]").val(telefono);
		
		
		if(status == 1){
			$("#status_azienda_true_edit").prop('checked',true);
		}else{
			$("#status_azienda_false_edit").prop('checked',true);
		}
	});
	  
	// RECUPERA I DATI DAI DATANAME E RIEMPIE LA FORM EDIT UTENTI
	$(".edit-utente").on("click", function() {
		var nomep = $(this).data("nomep");
		var cognomep = $(this).data("cognomep");
		var emailp = $(this).data("emailp");
		var password_dip = $(this).data("password_dip");
		var tariffaoraria = $(this).data("tariffaoraria");
		var nomecat = $(this).data("nomecat");
		var ruolocat = $(this).data("ruolocat");
		var status_dip_edit = $(this).data("status_dip");
		var idpersona = $(this).data("idpersona");

		$("#modal-edit-utenti input[name=cognomePersona]").val(cognomep);
		$("#modal-edit-utenti input[name=nomePersona]").val(nomep);
		$("#modal-edit-utenti input[name=emailPersona]").val(emailp);
		$("#modal-edit-utenti input[name=password_dip]").val(password_dip);
		$("#modal-edit-utenti input[name=tariffaoraria]").val(tariffaoraria);
		$("#modal-edit-utenti input[name=nome_cat]").val(nomecat);
		$("#modal-edit-utenti input[name=idpersona]").val(idpersona);
		
		
		$('#select-option-utente option').each(function(){ 
			if (this.value == ruolocat) { 
				val_f = this.value;
				$('#select-option-utente option[value='+val_f+']').prop("selected", true);
			} 
		});

		if(status_dip_edit == 1){
			$("#status_dip_true_edit").prop('checked',true);
		}else{
			$("#status_dip_false_edit").prop('checked',true);
		}

		
	});

	// RERCUPERA I CAMPI DAI DATANAME E RIEMPIE LA FORM EDIT CESPITI
	$(".edit-cespiti").on("click", function() {
		var anno_funzione = $(this).data("anno_funzione");
		var categoria = $(this).data("categoria");
		var descrizione = $(this).data("descrizione");
		var dipendente=$(this).data("dipendente");
		var idcespiti = $(this).data("idcespiti");
		var iddipendente = $(this).data("iddipendente");
		
		$("#modal-edit-cespiti input[name=idcespiti]").val(idcespiti);
		$("#modal-edit-cespiti input[name=anno_funzione]").val(anno_funzione);
		$("#modal-edit-cespiti input[name=categoria]").val(categoria);
		$("#modal-edit-cespiti input[name=descrizione]").val(descrizione);
		
		$('#select-option option').each(function(){ 
			if (this.value == iddipendente) { 
				val_f = this.value;
				$('#select-option option[value='+val_f+']').prop("selected", true);
			} 
		});

	});	
		


	// RERCUPERA I CAMPI DAI DATANAME E RIEMPIE LA FORM EDIT COMMESSE
	$(".edit-commesse").on("click", function() {
		var id = $(this).data("id");
		var nome = $(this).data("nome");
		var dipendente = $(this).data("dipendente");
		var aziendac = $(this).data("aziendac");
		var tariffa = $(this).data("tariffa");
	
		$("#modal-edit-commesse input[name=idCommessa]").val(id);
		$("#modal-edit-commesse input[name=nomeCommessa]").val(nome);
		$("#modal-edit-commesse input[name=idDipendente]").val(dipendente);
		$("#modal-edit-commesse input[name=nomeAziendaCommessa]").val(aziendac);
		$("#modal-edit-commesse input[name=tariffaCliente]").val(tariffa);
	});
});