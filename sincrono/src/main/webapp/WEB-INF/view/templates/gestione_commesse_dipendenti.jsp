<c:choose>
	<c:when test="${categoria == 'Amministratore' or categoria =='Amministrativo'}">
	<div class="row">
		<div class="col-lg-12 grid-margin stretch-card">
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-7">
							<h4 class="card-title">Gestione Commesse</h4>
							<p class="card-description">In questa sezione puoi aggiungere, modificare, eliminare le commesse</p>
						</div>
						<div class="col-3 m-auto">
							<div class="form-group">
								<label for="searchCommesse">Ricerca ...</label> <input type="text" class="form-control" id="searchGestioneCommesse" placeholder="Enter ....">
							</div>
						</div>
						<div class="col-2 btn-center">
							<button type="button" class="btn btn-icons btn-rounded btn-outline-primary  " data-toggle="modal" data-target="#modal-add-commessa">
								<i class="fas fa-plus fa-2x"></i>
	
							</button>
						</div>
					</div>
						
						
						
						
						<div class="table-responsive">
							<table class="table table-striped" id="table-gestione-commesse">
								<thead>
									<tr>
										<th>Nome Commessa</th>
										<th>Dipendente Associato</th>
										<th>Nome Azienda</th>
										<th>Tariffa Oraria</th>
									</tr>
								</thead>
								<tbody>
	
									<!--  ROW ELENCO COMMESSA -->
									<div class="row">
										<c:forEach items="${list_com}" var="x">
											<tr>
												<td>${x.nomeCommessa}</td>
												<td>${x.persona.nomePersona}&nbsp${x.persona.cognomePersona}</td>
												<td>${x.azienda.nomeAzienda}</td>
												<td>${x.tariffaCliente}</td>
												<td>
													<button type="button"
														class="btn btn-secondary btn-fw edit-commesse"
														data-toggle="modal" data-target="#modal-edit-commesse"
														data-id="${x.idCommessa}" 
														data-nome="${x.nomeCommessa}"
														data-dipendente="${x.persona.idPersona}"
														data-aziendac="${x.azienda.nomeAzienda}"
														data-tariffa="${x.tariffaCliente}">
														<i class="fas fa-edit"></i>
													</button>
												</td>
												<td>
													<form action="GestioneCommesseElimina" method="POST">
	
														<input type="hidden" name="idCommessa"
															value="${x.idCommessa}">
	
														<button type="submit" class="btn btn-secondary btn-fw">
															<i class="fas fa-trash-alt fa"></i>
														</button>
													</form>
												</td>
											</tr>
										</c:forEach>
									</div>
	
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
	</div>
	<button onclick="topFunction()" id="myBtn" title="Go to top">
		<i class="fas fa-arrow-up fa-2x text-primary"></i>
	</button>
	
	<!--  START MODAL ADD COMMESSE -->
	<div class="modal fade" id="modal-add-commessa" role="dialog">
		<div class="modal-dialog">

			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Inserisci Commessa</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>

				</div>
				<div class="modal-body">
					<div class="auto-form-wrapper">
						<form action="GestioneCommesseAdd" method="POST">

							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="Nome Commessa" name="nomeCommessa" required>
								</div>
							</div>
							
							
							<div class="form-group">
		                    <select class="form-control form-control-sm" name="idDipendente" required>
	                    	  <option selected disabled="disabled" value="">Dipendente Associato</option>    
		                     	<c:forEach items="${list_dip}" var="x">
		                     		<option value="${x.persona.idPersona}">${x.persona.nomePersona}&nbsp${x.persona.cognomePersona}</option> 
		                     	</c:forEach>
		                    </select>
		                  </div>

						
						
						<div class="form-group">
		                    <select class="form-control form-control-sm" name="nomeAziendaCommessa" required>
	                    	  <option selected disabled="disabled" value="">Identificativo Azienda</option>    
		                     	<c:forEach items="${list_az}" var="x">
		                     		<option value="${x.nomeAzienda}">${x.nomeAzienda}</option> 
		                     	</c:forEach>
		                    </select>
		                  </div>


							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="Tariffa Cliente" name="tariffaCliente" required>
								</div>
							</div>



							<div class="form-group">
								<button type="submit" onclick="prova_d()"
									class="btn btn-success submit-btn btn-block">Inserisci</button>
							</div>

						</form>

					</div>
				</div>

			</div>

		</div>
	</div>
	<!--  END MODAL ADD COMMESSE -->
	
	<!--  START MODAL EDIT COMMESSE -->
	<div class="modal fade" id="modal-edit-commesse" role="dialog">
		<div class="modal-dialog">

			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Edit Commessa</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>

				</div>
				<div class="modal-body">
					<div class="auto-form-wrapper">
						<form action="GestioneCommesseUpdate" method="POST">

							<div class="form-group">
								<div class="input-group">
									<input type="hidden" class="form-control"
										placeholder="ID Commessa" name="idCommessa">
								</div>
							</div>


							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="Nome Commessa" name="nomeCommessa">
								</div>
							</div>


						<div class="form-group">
							<select class="form-control form-control-sm" name="idDipendente" id="select-option-commessadip" required>
								<option selected disabled="disabled" value="">Identificativo Dipendente</option>
								<c:forEach items="${list_dip}" var="x">
									<option value="${x.persona.idPersona}">${x.persona.nomePersona}&nbsp${x.persona.cognomePersona}</option>
								</c:forEach>
							</select>
						</div>

							<div class="form-group">
		                    <select class="form-control form-control-sm" name="nomeAziendaCommessa" id ="select-option-commessaaz"required>
	                    	  <option selected disabled="disabled" value="">Nome Azienda</option>    
		                     	<c:forEach items="${list_az}" var="x">
		                     		<option value="${x.nomeAzienda}">${x.nomeAzienda}</option> 
		                     	</c:forEach>
		                    </select>
		                  </div>


							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="Tariffa Cliente" name="tariffaCliente" required>
								</div>
							</div>

							<button type="submit"
								class="btn btn-success submit-btn btn-block">Modifica</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  END MODAL EDIT COMMESSE -->
	
	</c:when>
	
	<c:otherwise>
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                 	  <img src="images/sirena.gif" class="w-50px" >
                  <h4 class="card-title d-inline">Non fare il furbo!</h4>
                  <div class="media">
                    <div class="media-body">
                      <p class="card-text">Non hai le autorizzazioni per entrare in questa pagina.</p>
                    </div>
                  </div>
                  
                </div>
              </div>
           	</div>
		</div>
	</c:otherwise>
</c:choose>




<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<c:choose>
	<c:when test="${errore_commesse eq 2}">
		<script>
			$(window).bind("load",function(){
			setTimeout(notify_danger,500);
			});
		</script>
	</c:when>
	<c:when test="${errore_commesse eq 1}">
		<script>
			$(window).bind("load",function(){
			setTimeout(notify_success,500);
			});
		</script>
	</c:when>
	<c:otherwise>
	</script>
	</c:otherwise>
</c:choose>






<script>
function notify_danger() {
	$.notify({
		// options
		message: 'Error: Commessa errata' 
	},{
		// settings
		type: 'danger',
		z_index: 2000,
		delay: 5000

	}

	);
};

function notify_success() {
	$.notify({
		// options
		message: 'Success: Evento andato a buon fine' 
	},{
		// settings
		type: 'success'
	});

};
</script>


<script>
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    document.getElementById("myBtn").style.display = "block";
  } else {
    document.getElementById("myBtn").style.display = "none";
  }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}

</script>