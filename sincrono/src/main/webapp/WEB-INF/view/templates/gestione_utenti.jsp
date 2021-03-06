<c:choose>
	<c:when test="${categoria == 'Amministratore' }">
	<div class="row">
		<div class="col-lg-12 grid-margin stretch-card">
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-7">
							<h4 class="card-title">Gestione utenti</h4>
							<p class="card-description">Puoi modificare le aziende </p>
						</div>
						<div class="col-3 m-auto">
							<div class="form-group">
								<label for="searchDipendenti">Ricerca ...</label> <input type="text" class="form-control" id="searchGestioneUtenti" placeholder="Enter ....">
							</div>
						</div>
						<div class="col-2 btn-center">
							<button type="button" class="btn btn-icons btn-rounded btn-outline-primary  " data-toggle="modal" data-target="#modal-add-utenti">
								<i class="fas fa-plus fa-2x"></i>
	
							</button>
						</div>
					</div>
	
	
					<div class="table-responsive">
						<table class="table table-striped" id="table-gestione-utenti">
							<thead>
								<!-- ROW INTESTAZIONE TABLE -->
								<tr>
									<th>User</th>
									<th>Dipendente</th>
									<th>Tariffa Oraria</th>
									<th class="text-center">Status</th>
									<th></th>
									<th></th>
									
								</tr>
							</thead>
							
							<tbody>
								<!--  ROW ELENCO UTENTI -->
								<div class="row">
									<c:forEach items="${list_dip}" var="x">
										<tr>
											<td class="py-1"><img src="../../images/faces-clipart/pic-1.png" alt="image"></td>
											<td>${x.persona.nomePersona}&nbsp${x.persona.cognomePersona}</td>
											<td class="text-center">${x.tariffaOraria}</td>
											<td class="text-center">
												<c:choose>
													<c:when test="${x.statusDip eq 1}">
														<i class="fas fa-circle text-success fa-2x"></i>
													</c:when>
													<c:otherwise>
														<i class="fas fa-circle text-danger fa-2x"></i>
													</c:otherwise>
												</c:choose>
											</td>
											<td data-toggle="tooltip" data-placement="top"
													title="Edit Utente">
												<button type="button" class="btn btn-secondary btn-fw edit-utente" 
													data-nomep="${x.persona.nomePersona}"  
													data-cognomep="${x.persona.cognomePersona}"
													data-emailp="${x.persona.emailPersona}"
													data-tariffaoraria = "${x.tariffaOraria}"
													data-nomecat ="${x.categoria.id.nomeCat}"
													data-ruolocat ="${x.categoria.id.ruoloCat}"
													data-status_dip = "${x.statusDip}"
													data-idpersona = "${x.idPersonadip}"
													data-password_dip = "${x.passwordDip}"
													data-toggle="modal" 
													data-target="#modal-edit-utenti">
													<i class="fas fa-edit"></i>
												</button>
											</td>
											<td data-toggle="tooltip" data-placement="top"
													title="Delete Utente">
												<!-- START Elimina utente -->
												<form action="GestioneUtentiElimina" method="POST">
							
													<input type="hidden" name=idPersonadip value="${x.idPersonadip}">
											
													<button type="submit" class="btn btn-secondary btn-fw">
														<i class="fas fa-trash-alt fa"></i>
													</button>
												</form>
												<!-- FINE Elimina utente -->
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
	
	
	<!-- MODAL ADD UTENTI -->
	<div class="modal fade" id="modal-add-utenti" role="dialog">
		<div class="modal-dialog">
	
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Inserisci nuovo Utente</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="auto-form-wrapper">
					
						<form action="GestioneUtentiAdd" method="post">
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" name="cognomePersona" placeholder="Cognome" required>
	
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" name="nomePersona" placeholder="Nome" required>
	
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" name="emailPersona" placeholder="Email" required>
	
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" name="password_dip" placeholder="Password" required>
	
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" name="nome_cat" placeholder="Es. Programmatore, Segretaria ..." required>
	
								</div>
							</div>
							<div class="form-group">
			                    <select class="form-control form-control-sm"  name="ruolo_cat"  required>
			                    <option selected disabled="disabled" value="">Ruolo Categoria</option> 
			                      <option value="amministratore">Amministratore</option>
			                      <option value="commerciale">Commerciale</option>
			                      <option value="amministrativo">Amministrativo</option>
			                      <option value="nessuno">Nessuno</option>
			                    </select>
			                  </div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" name="tariffaoraria" placeholder="Tariffa Oraria"  required>
	
								</div>
							</div>
	
							<div class="form-group">
	                          <div class="form-radio form-radio-flat">
	                            <label class="form-check-label">
	                              <input type="radio" class="form-check-input" name="status_dip_add" id="status_dip_true_add" value="1" required> Attivo
	                            <i class="input-helper"></i></label>
	                          </div>
	                          <div class="form-radio form-radio-flat danger">
	                            <label class="form-check-label">
	                              <input type="radio" class="form-check-input" name="status_dip_add" id="status_dip_false_add" value="0" required> Disattivo
	                            <i class="input-helper "></i></label>
	                          </div>
	                        </div>					
	
							<div class="form-group">
								<button type="submit" class="btn btn-success submit-btn btn-block">Inserisci Nuovo Dipendente</button>
							</div>
	
						</form>
					</div>
				</div>
			</div>
	
		</div>
	</div>
	
	<!-- MODAL EDIT UTENTI -->
	<div class="modal fade" id="modal-edit-utenti" role="dialog">
		<div class="modal-dialog">
	
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Edit Utente</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="auto-form-wrapper">
						<!-- TODO: INSERIRE ACTION E METODO POST -->
						<form action="GestioneUtenteUpdate" method="post">
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" name="cognomePersona" placeholder="Cognome" required>
	
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" name="nomePersona" placeholder="Nome" required>
	
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" name="emailPersona" placeholder="Email" required>
	
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" name="password_dip" placeholder="Password" required>
	
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" name="nome_cat" placeholder="Es. Programmatore, Segretaria ..." required>
	
								</div>
							</div>
							<div class="form-group">
			                    <select class="form-control form-control-sm" name="ruolo_cat" id="select-option-utente"  required>   
			                      <option value="amministratore">Amministratore</option>
			                      <option value="commerciale">Commerciale</option>
			                      <option value="amministrativo">Amministrativo</option>
			                      <option value="nessuno">Nessuno</option>
			                    </select>
			                  </div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" name="tariffaoraria" placeholder="Tariffa Oraria" required>
	
								</div>
							</div>
							
							<div class="form-group">
	                          <div class="form-radio form-radio-flat">
	                            <label class="form-check-label">
	                              <input type="radio" class="form-check-input" name="status_dip_edit" id="status_dip_true_edit" value="1" required> Attivo
	                            <i class="input-helper"></i></label>
	                          </div>
	                          <div class="form-radio form-radio-flat danger">
	                            <label class="form-check-label">
	                              <input type="radio" class="form-check-input" name="status_dip_edit" id="status_dip_false_edit" value="0" required> Disattivo
	                            <i class="input-helper "></i></label>
	                          </div>
	                        </div>		
	                        
	                        						
							<div class="form-group">
	                        	<div class="input-group">
									<input type="hidden" class="form-control" name="idpersona" >
								</div>
							</div>
							
	                        
	                        
							<div class="form-group">
								<button type="submit" class="btn btn-success submit-btn btn-block">Modifica</button>
							</div>
	
						</form>
					</div>
				</div>
			</div>
	
		</div>
	</div>
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
<script>
function notify_danger() {
	$.notify({
		// options
		message: 'Error: Utente gia esistente' 
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
<c:choose>
	<c:when test="${errore_dipendenti eq 2}">
		<script>
			$(window).bind("load",function(){
			setTimeout(notify_danger,500);
			});
		</script>
	</c:when>
	<c:when test="${errore_dipendenti eq 1}">
		<script>
			$(window).bind("load",function(){
			setTimeout(notify_success,500);
			});
			setTimeout(function(){ 
				location.href = $("#gestione_utenti_nav").attr("href");
				}, 
				1000);
		</script>
	</c:when>
	<c:otherwise>
	</script>
	</c:otherwise>
</c:choose>