$(document).ready(function(){
	console.log("ciaooooooooooooooooooooo");
	
	// FUNZIONE PER RICERCA NELLA PAGINA AZIENDA
	$("#searchAziende").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$(".fieldNameAzienda").filter(function() {
			$(this).closest(".stretch-card").toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});

	// FUNZIONE PER RICERCA NELLA PAGINA GESTIONE AZIENDE
	$("#searchGestioneAziende").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#table-gestione-aziende tr").not('thead tr').filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
		  
	
	// RECUPERA I CAMPI DAI DATANAME E RIEMPIE LA FORM SHOW AZIENDA
	$("#stretch-card-aziende").on("click", function() {
		var namen = $(this).data("namen");
		var email = $(this).data("email");
		var address = $(this).data("address");
		var numdip = $(this).data("numdip");
		var piva = $(this).data("piva");
		var societa = $(this).data("societa");
		var telefono = $(this).data("telefono");
		var status = $(this).data("status");

		$("#modal-detail-aziende input[name=nomeAzienda]").val(namen).prop("disabled",true);
		$("#modal-detail-aziende input[name=emailAzienda]").val(email).prop("disabled",true);
		$("#modal-detail-aziende input[name=indirizzoAzienda]").val(address).prop("disabled",true);
		$("#modal-detail-aziende input[name=numdipAzienda]").val(numdip).prop("disabled",true);
		$("#modal-detail-aziende input[name=pivaAzienda]").val(piva).prop("disabled",true);
		$("#modal-detail-aziende input[name=societa]").val(societa).prop("disabled",true);
		$("#modal-detail-aziende input[name=status]").val(status).prop("disabled",true);
		$("#modal-detail-aziende input[name=telefonoAzienda]").val(telefono).prop("disabled",true);
	});
		


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
	  

	// FUNZIONE PER RICERCA IN GESTIONE UTENTI
	$("#searchGestioneUtenti").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#table-gestione-utenti tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	// FUNZIONE PER RICERCA IN UTENTI
	$("#searchDipendenti").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$(".fieldName").filter(function() {
			$(this).closest(".stretch-card").toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	

	// RECUPERA I DATI DAI DATANAME E RIEMPIE LA FORM EDIT UTENTI
	$(".edit-utente").on("click", function() {
		var nomep = $(this).data("nomep");
		var cognomep = $(this).data("cognomep");
		var emailp = $(this).data("emailp");
		var passwordDip = "**************";
		var tariffaoraria = $(this).data("tariffaoraria");
		var nomecat = $(this).data("nomecat");
		var ruolocat = $(this).data("ruolocat");
		var statusdip = $(this).data("statusdip");
		var idpersona = $(this).data("idpersona");

		$("#modal-edit-utenti input[name=cognomePersona]").val(cognomep);
		$("#modal-edit-utenti input[name=nomePersona]").val(nomep);
		$("#modal-edit-utenti input[name=emailPersona]").val(emailp);
		$("#modal-edit-utenti input[name=passwordDip]").val(passwordDip);
		$("#modal-edit-utenti input[name=tariffaoraria]").val(tariffaoraria);
		$("#modal-edit-utenti input[name=nome_cat]").val(nomecat);
		$("#modal-edit-utenti input[name=ruolo_cat] select").val(ruolocat);
		$("#modal-edit-utenti input[name=statusDip]").val(statusdip);
		$("#modal-edit-utenti input[name=idpersona]").val(idpersona);


	});
	
	
	// RECUPERA I DATI DA DATA E RIEMPIE LA FORM SHOW UTENTI
	$("#stretch-card-utenti").on("click", function() {
		var nomep = $(this).data("nomep");
		var cognomep = $(this).data("cognomep");
		var emailp = $(this).data("emailp");
		var passwordDip = "**************";
		var tariffaOraria = $(this).data("tariffaOraria");
		var nomecat = $(this).data("nomecat");
		var ruolocat = $(this).data("ruolocat");
		var tariffaoraria = $(this).data("tariffaoraria");

		$("#modal-detail-utenti input[name=cognomePersona]").val(cognomep).prop( "disabled", true ).css('textTransform', 'capitalize');
		$("#modal-detail-utenti input[name=nomePersona]").val(nomep).prop( "disabled", true ).css('textTransform', 'capitalize');
		$("#modal-detail-utenti input[name=emailPersona]").val(emailp).prop( "disabled", true );
		$("#modal-detail-utenti input[name=passwordDip]").val(passwordDip).prop( "disabled", true );
		$("#modal-detail-utenti input[name=tariffaOraria]").val(tariffaOraria).prop( "disabled", true );
		$("#modal-detail-utenti input[name=nome_cat]").val(nomecat).prop("disabled", true ).css('textTransform', 'capitalize');
		$("#modal-detail-utenti input[name=ruolo_cat]").val(ruolocat).prop( "disabled", true ).css('textTransform', 'capitalize');
		$("#modal-detail-utenti input[name=tariffaOraria]").val(tariffaoraria + "Euro").prop( "disabled", true );


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