<div class="row">
	<div class="col-lg-12 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-7">
						<h4 class="card-title">Gestione Aziende</h4>
						<p class="card-description">In questa sezione puoi aggiungere, modificare, eliminare le aziende</p>
					</div>
					<div class="col-3 m-auto">
						<div class="form-group">
							<label for="searchAziende">Cerca Aziende</label>
							<input type="text" class="form-control" id="searchGestioneAziende" placeholder="Enter...">

						</div>
					</div>

					<div class="col-2 btn-center">
						<button type="button" class="btn btn-icons btn-rounded btn-outline-primary btn-center" data-toggle="modal" data-target="#modal-add-aziende">
							<i class="fas fa-plus fa-2x"></i>
						</button>
					</div>
				</div>
				<div class="table-responsive">
					<table class="table table-striped" id="table-gestione-aziende">
						<thead>
							<tr>
								<th>Nome Azienda</th>
								<th>Email</th>
								<th>Partita iva</th>
								<th>Referente</th>
								<th class="text-center">Status</th>
								<th></th>
							</tr>
						</thead>
						<tbody>

							<!--  ROW ELENCO AZIENDE -->
							<div class="row">
								<c:forEach items="${list_az}" var="x">
										<tr>
											<td>${x.nomeAzienda}</td>
											<td>${x.emailAzienda}</td>
											<td>${x.pivaAzienda}</td>
											<td>
												<form action="Referente" method="post">
													<input type="hidden" name="nomeAzienda" value="${x.nomeAzienda}">
													<button type="submit" class="btn btn-secondary btn-fw">
														<i class="fas fa-user-astronaut"></i>
													</button>
												</form>
											<c:forEach items="${x.referentes}" var="y">
												<input type="hidden" id="ref-aziende" data-toggle="modal" data-target="#modal-ref-aziende" 
													data-idref="${y.persona.idPersona}" 
													data-nomeref="${y.persona.nomePersona}" 
													data-cognomeref="${y.persona.cognomePersona}" 
													data-emailref="${y.persona.emailPersona}"
													data-telefonoref="${y.telefonoRef}" 
													data-aziendaref="${y.azienda.nomeAzienda}" />
												</c:forEach>
											</td>

											<!-- DANGER OR SUCCESS ICON -->
											<td class="text-center">
												<c:choose>
													<c:when test="${x.statusAzienda eq 1}">
														<button type="button" class="btn btn-icons btn-rounded btn-success"></button>
													</c:when>
													<c:otherwise>
														<button type="button" class="btn btn-icons btn-rounded btn-danger"></button>
													</c:otherwise>
												</c:choose>
											</td>
											
											<td>
												<button type="button" class="btn btn-secondary btn-fw edit-aziende" data-toggle="modal" data-target="#modal-edit-aziende" 
													data-namen="${x.nomeAzienda}" 
													data-email="${x.emailAzienda}"
													data-address="${x.indirizzoAzienda}"
													data-numdip="${x.numdipAzienda}"
													data-piva="${x.pivaAzienda}" 
													data-societa="${x.societa}" 
													data-telefono="${x.telefonoAzienda}" 
													data-status="${x.statusAzienda}">
														<i class="fas fa-edit"></i>
												</button>
											</td>
											
											<td>
												<form action="GestioneAziendeElimina" method="POST">
													<input type="hidden" name="nomeAziendaElimina" value="${x.nomeAzienda}">
													<button type="submit" class="btn btn-secondary btn-fw">
														<i class="fas fa-trash-alt fa"></i>
													</button>
												</form>
											</td>
											<td>
												<form action="GestioneAziendePrint" method="POST">
													<input type="hidden" name="nomeAziendaPrint" value="${x.nomeAzienda}">
													<input type="hidden" name="emailAzienda" value="${x.emailAzienda}">
													<input type="hidden" name="indirizzoAzienda" value="${x.indirizzoAzienda}">
													<input type="hidden" name="numdipAzienda" value="${x.numdipAzienda}">
													<input type="hidden" name="telefonoAzienda" value="${x.telefonoAzienda}">
													<input type="hidden" name="societa" value="${x.societa}">
													<input type="hidden" name="pivaAzienda" value="${x.pivaAzienda}">
													<button type="submit" class="btn btn-secondary btn-fw">
														<i class="fas fa-print"></i>
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



<!--  QUESTO è IL MODAL PER L'ADD AZIENDE -->
<div class="modal fade" id="modal-add-aziende" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Inserisci Azienda</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<div class="auto-form-wrapper">
					<form action="GestioneAziendeAdd" method="POST">
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Nome Azienda" name="nomeAzienda" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Email Azienda" name="emailAzienda" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Indirizzo" name="indirizzoAzienda" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Numero dipendenti" name="numdipAzienda" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Partita IVA" name="pivaAzienda" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Nome Società" name="societa" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Telefono" name="telefonoAzienda" required>
							</div>
						</div>
						<!-- Di default setto Status Azienda ad 1 quando lo registro -->
						<input type="hidden" name="statusAzienda" value="1">
						<div class="form-group">
							<button type="submit" class="btn btn-success submit-btn btn-block">Inserisci</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- CLOSE MODAL ADD AZIENDE -->



<!--  QUESTO è IL MODAL PER L'EDIT AZIENDE-->
<div class="modal fade" id="modal-edit-aziende" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Edit Azienza</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<div class="auto-form-wrapper">
					<form action="GestioneAziendeUpdate" method="POST">
						<div class="form-group">
							<div class="input-group">
								<input type="hidden" class="form-control" placeholder="Nome Azienda" name="nomeAzienda" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Email Azienda" name="emailAzienda" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Indirizzo" name="indirizzoAzienda" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Numero dipendenti" name="numdipAzienda" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Partita IVA" name="pivaAzienda" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Nome Società" name="societa" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Telefono" name="telefonoAzienda" required>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<div class="form-group">
									<div class="form-radio form-radio-flat">
										<label class="form-check-label">
											<input type="radio" class="form-check-input" name="statusAzienda" id="flatRadios1" value="1" required> Attivo
											<i class="input-helper"></i>
										</label>
									</div>
									<div class="form-radio form-radio-flat danger">
										<label class="form-check-label">
											<input type="radio" class="form-check-input" name="statusAzienda" id="flatRadios2" value="0" required> Disattivo
											<i class="input-helper"></i>
										</label>
									</div>
								</div>
							</div>
						</div>
						<button type="submit" class="btn btn-success submit-btn btn-block">Modifica</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- CLOSE MODAL EDIT AZIENDA -->


<!-- START MODAL PER IL REFERENTE CHE CAMBIA MAGICAMENTE -->
<div class="modal fade" id="modal-ref-aziende" role="dialog">
	<div class="modal-dialog">
		<c:set var="ref" scope="session" value="${has_ref}" />
		<!-- SE L'AZIENDA NON HA UN REFERENTE , LA MODAL HA I CAMPI VUOTI PER AGGIUNGERE UN REFERENTE -->
		<c:if test="${ref eq false}">
			<div class="modal-content ">
				<div class="modal-header">
					<h4 class="modal-title">Inserisci Referente</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="auto-form-wrapper">
						<form action="GestioneReferenteAdd" method="POST">
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Nome" name="nome_ref_add" required>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Cognome" name="cognome_ref_add" required>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Email" name="email_ref_add" required>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Telefono" name="telefono_ref_add" required>
								</div>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-success submit-btn btn-block">Inserisci</button>
							</div>
						</form>
					</div>
				</div>
			</div> <!-- CLOSE MODAL CONTENT ADD REFERENTE -->

		</c:if>
		
		<c:if test="${ref eq true}">
		<!-- SE L'AZIENDA HA UN REFERENTE , LA MODAL HA I CAMPI PIENI PER VEDERE E MODIFICARE UN REFERENTE -->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Visualizza Referente</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="auto-form-wrapper">
						<form action="GestioneReferenteEdit" method="POST">
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Nome" name="nome_ref_edit" required>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Cognome" name="cognome_ref_edit" required>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Email" name="email_ref_edit" required>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Telefono" name="telefono_ref_edit" required>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-4">
									<button id="edit-ref" type="button" class="btn btn-warning submit-btn btn-block">Modifica</button>
								</div>
								<div class="col-4">
									<button id="save-ref" type="submit" class="btn btn-success submit-btn btn-block">Salva</button>
								</div>
								<div class="col-4">
									<button id="delete-ref" type="submit" class="btn btn-danger submit-btn btn-block">Elimina</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div> <!--CLOSE MODAL CONTENT EDIT REFERENTE -->
		</c:if>
	</div>
</div>
<!-- END MODAL PER IL REFERENTE CHE CAMBIA MAGICAMENTE -->

<c:set var="count" scope="session" value="${count}" />
<c:if test="${count eq 1}">
	<!-- jQuery -->
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript">
		$(window).load(function() {
			$("#ref-aziende").click();
		});
	</script>
	<c:set var="count" scope="session" value="0" />
</c:if>