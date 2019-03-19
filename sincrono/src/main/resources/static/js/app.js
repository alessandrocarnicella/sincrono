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
		

		$("#modal-edit-aziende input[name=nomeAzienda]").val(namen);
		$("#modal-edit-aziende input[name=emailAzienda]").val(email);
		$("#modal-edit-aziende input[name=indirizzoAzienda]").val(address);
		$("#modal-edit-aziende input[name=numdipAzienda]").val(numdip);
		$("#modal-edit-aziende input[name=pivaAzienda]").val(piva);
		$("#modal-edit-aziende input[name=societa]").val(societa);
		$("#modal-edit-aziende input[name=statusAzienda]").val(status);
		$("#modal-edit-aziende input[name=telefonoAzienda]").val(telefono);
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
		var status_dip = $(this).data("status_dip");
		var idpersona = $(this).data("idpersona");

		$("#modal-edit-utenti input[name=cognomePersona]").val(cognomep);
		$("#modal-edit-utenti input[name=nomePersona]").val(nomep);
		$("#modal-edit-utenti input[name=emailPersona]").val(emailp);
		$("#modal-edit-utenti input[name=password_dip]").val(password_dip);
		$("#modal-edit-utenti input[name=tariffaoraria]").val(tariffaoraria);
		$("#modal-edit-utenti input[name=nome_cat]").val(nomecat);
		$("#modal-edit-utenti input[name=ruolo_cat] select").val(ruolocat);	
		$("#modal-edit-utenti input[name=idpersona]").val(idpersona);
		
		console.log(status_dip);
		if(status_dip == 1){
			$("#status_dip_true").prop('checked',true);
			console.log("if");
		}else{
			console.log("else");
			$("#status_dip_false").prop('checked',true);
		}

		
	});

	// RECUPERA I CAMPI DAI DATANAME E RIEMPIE LA FORM EDIT CESPITI
	$(".edit-cespiti").on("click", function() {
		var annofunzione = $(this).data("annofunzione");
		var categoria = $(this).data("categoria");
		var descrizione = $(this).data("descrizione");
		var dipendente=$(this).data("dipendente");
		$("#modal-edit-cespiti input[name=annofunzione]").val(annofunzione);
		$("#modal-edit-cespiti input[name=categoria]").val(categoria);
		$("#modal-edit-cespiti input[name=descrizione]").val(descrizione);
		$("#modal-edit-cespiti input[name=dipendente] select").val(dipendente);

	});
		

	//FUNZIONE PER RICERCA IN RAPPORTINI  
	$("#searchRapportini").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#table-gestione-ril tr").not('thead tr').filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});

		

	
	function notify_danger() {
		$.notify({
			// options
			message: 'STUPIDAAAAAAAAAA' 
		},{
			// settings
			type: 'danger',
			z_index: 2000,
			delay: 5000

		}

		);
	};
	
	function notify_warning() {
		$.notify({
			// options
			message: 'Yeah' 
		},{
			// settings
			type: 'warning'
		});

	};

	function notify_success() {
		$.notify({
			// options
			message: 'Yeah' 
		},{
			// settings
			type: 'success'
		});

	};

	
	function sample() {
		alert("This is sample function");
	}
	
	$(function() {
		$("#button").click(function() {
			setTimeout(sample, 2000);
		});
	
	});


});