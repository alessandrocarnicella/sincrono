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

	<c:forEach items="${list_dip}" var="x">
		<div
			class="col-xl-3 col-lg-3 col-md-3 col-sm-6 grid-margin stretch-card" id="stretch-card-utenti"
			data-nomep="${x.persona.nomePersona}"  
			data-cognomep="${x.persona.cognomePersona}"
			data-emailp="${x.persona.emailPersona}"
			data-tariffaoraria = "${x.tariffaOraria}"
			data-nomecat ="${x.categoria.id.nomeCat}"
			data-ruolocat ="${x.categoria.id.ruoloCat}"
			data-passwordDip ="${x.passwordDip}"
			data-toggle="modal" 
			data-target="#modal-detail-utenti">
			
			<div class="card card-statistics">
				<div class="card-body">
					<div class="clearfix">
						<div class="float-left">
											<c:choose>
												<c:when test="${x.statusDip eq 1}">
													<button type="button" class="btn btn-icons btn-rounded btn-success"></button>
												</c:when>
												<c:otherwise>
													<button type="button" class="btn btn-icons btn-rounded btn-danger"></button>
												</c:otherwise>
											</c:choose>
							<i class="mdi mdi-cube text-danger icon-lg"></i>
						</div>
						<div class="float-right">
							<p class="mb-0 text-right">
											
							<div class="fluid-container">
								<h3 class="font-weight-medium text-right mb-0 fieldName" >${x.persona.nomePersona}</h3>
							</div>
						</div>
					</div>
					<p class="text-muted mt-3 mb-0">
						<i class="mdi mdi-alert-octagon mr-1" aria-hidden="true"></i>
					</p>
				</div>
			</div>
		</div>
	</c:forEach>

</div>


<div class="modal fade" id="modal-detail-utenti" role="dialog">
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
						<input type="hidden" name="statusDip" value="1">
										

					</form>
				</div>
			</div>
		</div>

	</div>
</div>


