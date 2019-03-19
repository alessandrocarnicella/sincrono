<div class="row">
	<div class="col-lg-12 grid-margin stretch-card">
	
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-7">
						<h4 class="card-title">Gestione Cespiti</h4>
						<p class="card-description">Puoi visualizzare i cespiti, aggiungerli o modificarli </p>
					</div>
					<div class="col-3 m-auto">
						<div class="form-group">
							<label for="searchcespiti">Ricerca ...</label> <input type="text" class="form-control" id="searchCespiti" placeholder="Enter ....">
						</div>
					
					</div>
					<div class="col-2 btn-center">
						<button type="button" class="btn btn-icons btn-rounded btn-outline-primary  " data-toggle="modal" data-target="#modal-add-cespiti">
							<i class="fas fa-plus fa-2x"></i>

						</button>
					</div>
				</div>


				<div class="table-responsive">
					<table class="table table-striped" id="table-gestione-cespiti">
						<thead>
							<!-- ROW INTESTAZIONE TABLE -->
							<tr>
								<th>Anno Aggiunta</th>
								<th>Categoria</th>
								<th>Descrizione</th>
								<th>Nome</th>
								<th>Cognome</th>
								<th></th>
								<th></th>
				
							</tr>
						</thead>
						
						<tbody>
							<!--  ROW ELENCO UTENTI -->
							<div class="row">
								<c:forEach items="${list_cespiti}" var="x">
									<tr>
									
										<td>${x.annoFunzione}</td>
										<td>${x.categoria}</td>
										<td>${x.descrizione}</td>
										<td>${x.dipendente.persona.nomePersona}</td>
										<td>${x.dipendente.persona.cognomePersona}</td>
										<td>
									
											<button type="button" class="btn btn-secondary btn-fw edit-cespiti" 
												data-idces="${x.idcespiti}"
												data-annofunzione="${x.annoFunzione}"
												data-categoria="${x.categoria}"
												data-descrizione = "${x.descrizione}"
												data-dipendente="${x.dipendente}"
												data-toggle="modal" 
												data-target="#modal-edit-cespiti">
												<i class="fas fa-edit"></i>
											</button>
										</td>
										
										<td>
											<!-- START Elimina cespiti -->
											<form action="CespitiElimina" method="POST">
						
												<input type="hidden" name=idcespiti value="${x.idcespiti}">
										
												<button type="submit" class="btn btn-secondary btn-fw">
													<i class="fas fa-trash-alt fa"></i>
												</button>
											</form>
											<!-- FINE Elimina cespiti -->
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


<!-- MODAL ADD CESPITE  -->
<div class="modal fade" id="modal-add-cespiti" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Inserisci Cespite</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<div class="auto-form-wrapper">
				
					<form action="CespitiAdd" method="post">
					
						
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" name="annofunzione-add" placeholder="Anno" required>
							</div>
						</div>
						
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" name="categoria-add" placeholder="Categoria" required>

							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" name="descrizione-add" placeholder="Descrizione" required>

							</div>
						</div>
						
						<div class="form-group">
		                    <select class="form-control form-control-sm" name="dipendente-add">
	                    	  <option selected disabled="disabled">Dipendente Responsabile</option>    
		                     	<c:forEach items="${list_dip}" var="y">
		                     		<option value="${y.idPersonadip}">${y.persona.nomePersona}&nbsp${y.persona.cognomePersona}</option> 
		                     	</c:forEach>
		                    </select>
		                  </div>
										

						<div class="form-group">
							<button type="submit" class="btn btn-success submit-btn btn-block">Inserisci Nuovo Cespite</button>
						</div>

					</form>
				</div>
			</div>
		</div>

	</div>
</div>

<!-- MODAL EDIT CESPITE -->
<div class="modal fade" id="modal-edit-cespiti" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Modifica Cespite</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<div class="auto-form-wrapper">
				
					<form action="CespitiUpdate" method="post">
						
					<input type="hidden" name=idcespiti-edit value="${x.idcespiti}">

						
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" name="annofunzione-edit" placeholder="Anno" required>
							</div>
						</div>
						
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" name="categoria-edit" placeholder="Categoria" required>
							</div>
						</div>
						
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" name="descrizione-edit" placeholder="Descrizione" required>
							</div>
						</div>
						
						<div class="form-group">
		                    <select class="form-control form-control-sm" name="dipendente-edit">
	                    	  <option selected disabled="disabled">Dipendente Responsabile</option>    
		                     	<c:forEach items="${list_dip}" var="y">
		                     		<option value="${y.idPersonadip}">${y.persona.nomePersona}&nbsp${y.persona.cognomePersona}</option> 		                     		 
		                     	</c:forEach>
		                    </select>
		                  </div>
						
						<div class="form-group">
							<button type="submit" class="btn btn-success submit-btn btn-block">Modifica Cespite</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>



