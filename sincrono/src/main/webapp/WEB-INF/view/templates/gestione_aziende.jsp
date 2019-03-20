<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function notify_danger() {
	$.notify({
		// options
		message: 'Error: Rapportino gia esistente' 
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
	<c:when test="${errore_aziende eq 2}">
		<script>
			$(window).bind("load",function(){
			setTimeout(notify_danger,500);
			});
		</script>
	</c:when>
	<c:when test="${errore_aziende eq 1}">
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
								<c:set var="c" scope="page" value="1" />
								<c:forEach items="${list_az}" var="x">
									<tr>
										<td>${x.nomeAzienda}</td>
										<td>${x.emailAzienda}</td>
										<td>${x.pivaAzienda}</td>
										<td>
											<%@ page import="com.sincrono.corso.model.Azienda"%>
											<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
											<c:choose>
												<c:when test="${fn:length(x.referentes) gt 0}">
													<%
														boolean has_ref = true;
														request.getSession().setAttribute("has_ref", has_ref);
													%>
												</c:when>
												<c:otherwise>
													<%
														boolean has_ref = false;
														request.getSession().setAttribute("has_ref", has_ref);
													%>
												</c:otherwise>
											</c:choose>
											<c:set var="has_ref" scope="page" value="${requestScope.has_ref}" /> 
											<c:choose>
												<c:when test="${has_ref eq true}">
													<c:forEach items="${x.referentes}" var="y">
														<!-- onclick  -->
														<a class="btn btn-secondary btn-fw" id="${c}"
															onclick="launch_modal(id=${c})"
															data-ref="${has_ref}"
															data-idref="${y.persona.idPersona}"
															data-nomeref="${y.persona.nomePersona}"
															data-cognomeref="${y.persona.cognomePersona}"
															data-emailref="${y.persona.emailPersona}"
															data-telefonoref="${y.telefonoRef}"
															data-aziendaref="${y.azienda.nomeAzienda}"> 
															<i class="fas fa-user-astronaut text-success" ></i>
														</a>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<a class="btn btn-secondary btn-fw" id="${c}"
														onclick="launch_modal(id=${c})"
														data-ref="${has_ref}"> 
														<i class="fas fa-user-astronaut text-danger"></i>
													</a>
												</c:otherwise>
											</c:choose> 
											
											<!-- START MODAL REFERENTE -->
											<div class="modal fade" id="modal${c}" role="dialog">
												<div class="modal-dialog">
													<c:set var="ref" scope="session" value="${has_ref}" />													
													<!-- SE L'AZIENDA NON HA UN REFERENTE , LA MODAL HA I CAMPI VUOTI PER AGGIUNGERE UN REFERENTE -->
													<c:if test="${ref eq false}">
														<!-- START MODAL CONTENTE ADD REFERENTE -->
														<div class="modal-content ">
															<div class="modal-header">
																<h4 class="modal-title">Inserisci Referente</h4>
																<button type="button" class="close" data-dismiss="modal">&times;</button>
															</div>
															<div class="modal-body">
																<div class="auto-form-wrapper">
																	
																	<!-- START FORM ADD REFERENTE -->
																	<form action="GestioneReferenteAdd" method="POST" id="formA${c}">
																		<div class="form-group">
																			<div class="input-group">
																				<input type="text" class="form-control" placeholder="Nome" name="nome-ref-add" required>
																			</div>
																		</div>
																		<div class="form-group">
																			<div class="input-group">
																				<input type="text" class="form-control" placeholder="Cognome" name="cognome-ref-add" required>
																			</div>
																		</div>
																		<div class="form-group">
																			<div class="input-group">
																				<input type="text" class="form-control" placeholder="Email" name="email-ref-add" required>
																			</div>
																		</div>
																		<div class="form-group">
																			<div class="input-group">
																				<input type="text" class="form-control" placeholder="Telefono" name="telefono-ref-add" required>
																			</div>
																		</div>
																		
																		<!-- CAMPO HIDDEN CON IL NOME DELLA AZIENDA -->
																		<input type="hidden" name="azienda-ref-add" value="${x.nomeAzienda}">
																		
																		<div class="form-group">
																			<button type="submit" id="insert-ref${c}" form="formA${c}" class="btn btn-success submit-btn btn-block">Inserisci</button>
																		</div>	
																	</form>
																	<!-- END FORM ADD REFERENTE -->
																	
																</div>
															</div>
														</div>
														<!-- CLOSE MODAL CONTENT ADD REFERENTE -->
													</c:if>

													<c:if test="${has_ref eq true}">
														<!-- START MODAL CONTENTE SHOW REFERENTE -->
														<div class="modal-content">
															<div class="modal-header">
																<h4 class="modal-title">Visualizza Referente</h4>
																<button type="button" class="close" data-dismiss="modal">&times;</button>
															</div>
															<div class="modal-body">
																<div class="auto-form-wrapper">
																	
																	<!-- START FORM SHOW/EDIT REFERENTE -->
																	<form action="GestioneReferenteEdit" method="POST" id="formE${c}">
																		<div class="form-group">
																			<div class="input-group">
																				<input type="text" class="form-control" placeholder="Nome" name="nome-ref-edit" required>
																			</div>
																		</div>
																		<div class="form-group">
																			<div class="input-group">
																				<input type="text" class="form-control" placeholder="Cognome" name="cognome-ref-edit" required>
																			</div>
																		</div>
																		<div class="form-group">
																			<div class="input-group">
																				<input type="text" class="form-control" placeholder="Email" name="email-ref-edit" required>
																			</div>
																		</div>
																		<div class="form-group">
																			<div class="input-group">
																				<input type="text" class="form-control" placeholder="Telefono" name="telefono-ref-edit" required>
																			</div>
																		</div>
																				<input type="hidden" name="azienda-ref-edit" value="${x.nomeAzienda}">
																				<input type="hidden" name="id-ref-edit" value="${x.referentes[0].persona.idPersona}">						
																									
																		
																		<div class="row mt-2">
																			<div class="col-6">
																				<button id="edit-ref${c}" onclick="edit(id=${c})" type="button" class="btn btn-warning submit-btn btn-block">Modifica</button>
																			</div>
																			
																			<div class="col-6">
																				<button id="save-ref${c}" form="formE${c}" class="btn btn-success submit-btn btn-block save-ref">Salva</button>
																			</div>
																		</div>
																	</form>
																	<!-- END FORM SHOW/EDIT REFERENTE -->	
																	
																	<!-- START FORM DELETE REFERENTE -->
																	<div class="row mt-2">
																		<div class="col-12">
																			<form action="GestioneReferenteDelete" method="POST" id="formD${c}">
																				<input type="hidden" name="id-ref-delete" value="${x.referentes[0].persona.idPersona}">	
																				<button id="delete-ref${c}" form="formD${c}" class="btn btn-danger submit-btn btn-block">Elimina</button>
																			</form>
																		</div>
																	</div>
																	<!-- END FORM DELETE REFERENTE -->
																</div>
															</div>
														</div>
														<!--CLOSE MODAL CONTENT SHOW/EDIT REFERENTE -->
													</c:if>
												</div>
											</div> 
											<!-- END MODAL REFERENTE -->
										</td>

										<!-- START DANGER OR SUCCESS ICON -->
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
										<!-- END DANGER OR SUCCESS ICON -->
										
										<!-- START EDIT AZIENDA -->
										<td>
											<button type="button" class="btn btn-secondary btn-fw edit-aziende"
												data-toggle="modal" data-target="#modal-edit-aziende"
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
										<!-- END EDIT AZIENDA -->
										
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
									<c:set var="c" scope="page" value="${c + 1}" />
								</c:forEach>
							</div>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>



<!--  START MODAL ADD AZIENDE -->
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
								<input type="text" class="form-control" placeholder="Nome Societ�" name="societa" required>
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



<!--  START MODAL EDIT AZIENDE-->
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
								<input type="text" class="form-control" placeholder="Nome Societ�" name="societa" required>
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
											<input type="radio" class="form-check-input" name="statusAzienda" id="status_azienda_true" value="1" required> Attivo 
											<i class="input-helper"></i>
										</label>
									</div>
									<div class="form-radio form-radio-flat danger">
										<label class="form-check-label"> 
											<input type="radio" class="form-check-input" name="statusAzienda" id="status_azienda_false" value="0" required> Disattivo 
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



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<script>
function launch_modal(id){
	var ref = $("#"+id).data("ref");
	
	if(ref){
		var idref = $("#"+id).data("idref");
		var nomeref = $("#"+id).data("nomeref");
		var cognomeref = $("#"+id).data("cognomeref");
		var emailref = $("#"+id).data("emailref");
		var telefonoref = $("#"+id).data("telefonoref");
		var aziendaref = $("#"+id).data("aziendaref");
		
		console.log(cognomeref);
		
		$("#modal"+id+" input[name=nome-ref-edit]").val(nomeref).prop("disabled",true);
		$("#modal"+id+" input[name=cognome-ref-edit]").val(cognomeref).prop("disabled",true);
		$("#modal"+id+" input[name=email-ref-edit]").val(emailref).prop("disabled",true);
		$("#modal"+id+" input[name=telefono-ref-edit]").val(telefonoref).prop("disabled",true);
		$("#modal"+id+" input[name=azienda-ref-edit]").val(aziendaref).prop("disabled",true);
		
		$("#save-ref"+id).prop("disabled",true);
	}
	
	$("#modal"+id).modal('show');
}				



function edit(id){
			
	$("#edit-ref"+id).prop("disabled",true);
	
	$("#modal"+id+" input[name=nome-ref-edit]").prop("disabled",false);
	$("#modal"+id+" input[name=cognome-ref-edit]").prop("disabled",false);
	$("#modal"+id+" input[name=email-ref-edit]").prop("disabled",false);
	$("#modal"+id+" input[name=telefono-ref-edit]").prop("disabled",false);
	$("#modal"+id+" input[name=azienda-ref-edit]").prop("disabled",false);
	
	$("#save-ref"+id).prop("disabled",false);
	
}

</script>