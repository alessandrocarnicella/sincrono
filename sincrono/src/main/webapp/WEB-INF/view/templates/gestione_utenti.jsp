<div class="row">
	<div class="col-lg-12 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-7">
						<h4 class="card-title">Gestione utenti</h4>
						<p class="card-description">Puoi modificare le aziende e farti
							un pacco di cazzi di tutti ( però ricordati della Privacy e fai i
							corsi online !)</p>
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
							<tr>
								<th>User</th>
								<th>Identificativo</th>
								<th>Tariffa oraria</th>
								<th>Persona associata</th>
								<th class="text-center">Status</th>
								<th></th>
							</tr>
						</thead>
						<tbody>


							<!--  ROW ELENCO AZIENDE -->
							<div class="row">
								<c:forEach items="${list_dip}" var="x">
									<tr>
										<td class="py-1"><img src="../../images/faces-clipart/pic-1.png" alt="image"></td>
										<td>${x.idPersonadip}</td>
										<td>${x.tariffaOraria}</td>
										<td>${x.persona.nomePersona}</td>
										<td class="text-center">
											<c:choose>
												<c:when test="${x.statusDip eq 1}">
													<button type="button" class="btn btn-icons btn-rounded btn-success"></button>
												</c:when>
												<c:otherwise>
													<button type="button" class="btn btn-icons btn-rounded btn-danger"></button>
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<button type="button" class="btn btn-secondary btn-fw">
												<i class="mdi mdi-cloud-download"></i>Modifica
											</button>
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
					<!-- TODO: INSERIRE ACTION E METODO POST -->
					<form action="#">
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Username">

							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="password" class="form-control" placeholder="Password">

							</div>
						</div>

						<div class="form-group">
							<button class="btn btn-success submit-btn btn-block">Inserisci</button>
						</div>

					</form>
				</div>
			</div>
		</div>

	</div>
</div>





<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(
		function() {
			$("#searchGestioneUtenti").on("keyup", function() {
					var value = $(this).val().toLowerCase();
					$("#table-gestione-utenti tr").filter(function() {
							$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
						});
				});
		});
</script>