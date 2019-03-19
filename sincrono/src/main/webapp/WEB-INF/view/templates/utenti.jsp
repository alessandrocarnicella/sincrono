<div class="row">
	<div class="col-8">
		<h3>Elenco Utenti</h3>
	</div>
	<div class="col-4">
		<div class="form-group">
			<label for="searchDipendenti">Ricerca Dipendenti</label> <input
				type="text" class="form-control" id="searchDipendenti"
				placeholder="Enter ....">
		</div>
	</div>

	<c:forEach items="${list_dip}" var="x" varStatus="count">
		<div
			class="col-xl-3 col-lg-4 col-sm-6 grid-margin stretch-card" id="U${count.count}"
			onclick="launch_modal_utenti(id=${count.count})"
			data-nomep="${x.persona.nomePersona}"  
			data-cognomep="${x.persona.cognomePersona}"
			data-emailp="${x.persona.emailPersona}"
			data-tariffaoraria = "${x.tariffaOraria}"
			data-nomecat ="${x.categoria.id.nomeCat}"
			data-ruolocat ="${x.categoria.id.ruoloCat}"
			data-passworddip ="${x.passwordDip}">

			<c:set var="status_int_dip" scope="page" value="${x.statusDip}"/> 
				<c:choose>
				    <c:when test="${status_int_dip==1}">
						<c:set var="status_string_dip" scope="page" value="success"/> 
				    </c:when>    
				    <c:otherwise>
						<c:set var="status_string_dip" scope="page" value="danger"/>
				    </c:otherwise>
				</c:choose>
				
			<div class="card card-statistics ${status_string_dip}">
				<div class="card-body">
					<div class="clearfix">
			            <div class="float-left">
				            <h3 class="px18 font-weight-medium mb-0 fieldName">${x.persona.cognomePersona} &nbsp ${x.persona.nomePersona}</h3>
				            <p class="mb-0">${x.persona.emailPersona}</p>
			               	<p class="text-muted mt-3 mb-0">${x.categoria.id.nomeCat} &nbsp ${x.categoria.id.ruoloCat}</p>	
	              		</div>
	              		<div class="float-right">
              			<c:choose>
							<c:when test="${x.statusDip eq 1}">
								<i class="fas fa-circle text-success"></i>
							</c:when>
							<c:otherwise>
								<i class="fas fa-circle text-danger"></i>
							</c:otherwise>
						</c:choose>
		              </div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="modalU${count.count}" role="dialog">
			<div class="modal-dialog">
		
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Dettagli Utente</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<div class="auto-form-wrapper">
							<!-- TODO: INSERIRE ACTION E METODO POST -->
							<form action="" method="">
								<div class="form-group">
									<div class="input-group">
										<input type="text" class="form-control"  name="cognomePersona" placeholder="Cognome">
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<input type="text" class="form-control" name="nomePersona" placeholder="Nome">
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<input type="text" class="form-control" name="emailPersona" placeholder="Email">
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<input type="password" class="form-control" name="passwordDip" placeholder="Password">
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<input type="text" class="form-control" name="nome_cat" placeholder="Es. Programmatore, Segretaria ...">
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<input type="text" class="form-control" name="ruolo_cat" placeholder="Es.  ...">
		
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<input type="text" class="form-control" name="tariffaOraria" placeholder="Tariffa Oraria">
									</div>
								</div>
								<!-- TODO: controllare sto cazzo di bottone -->
								<input type="hidden" name="statusDip" value="1">
							</form>
						</div>
					</div>
				</div>
		
			</div>
		</div>
		
	</c:forEach>

</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<script>
function launch_modal_utenti(id){
	
	// RECUPERA I CAMPI DAI DATANAME E RIEMPIE LA FORM SHOW AZIENDA
	var cognomePersona = $("#"+id).data("cognomep");
	var nomePersona = $("#"+id).data("nomep");
	var emailPersona = $("#"+id).data("emailp");
	var passwordDip = $("#"+id).data("passworddip");
	var nome_cat = $("#"+id).data("nomecat");
	var ruolo_cat = $("#"+id).data("ruolocat");
	var tariffa_oraria = $("#"+id).data("tariffaoraria");

	$("#modalU"+id+" input[name=cognomePersona]").val(cognomePersona).prop("disabled",true);
	$("#modalU"+id+" input[name=nomePersona]").val(nomePersona).prop("disabled",true);
	$("#modalU"+id+" input[name=emailPersona]").val(emailPersona).prop("disabled",true);
	$("#modalU"+id+" input[name=passwordDip]").val(passwordDip).prop("disabled",true);
	$("#modalU"+id+" input[name=nome_cat]").val(nome_cat).prop("disabled",true);
	$("#modalU"+id+" input[name=ruolo_cat]").val(ruolo_cat).prop("disabled",true);
	$("#modalU"+id+" input[name=tariffaOraria]").val(tariffa_oraria).prop("disabled",true);
	
	$("#modalU"+id).modal('show');
	
}
</script>