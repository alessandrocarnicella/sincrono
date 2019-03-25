<div class="row">
	<div class="col-lg-12 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-7">
						<h4 class="card-title">Dashboard</h4>
					</div>
					<div class="col-3 m-auto">
						<div class="form-group">
							<label for="searchRil">Cerca </label> <input type="text"
								class="form-control" id="searchRapportini"
								placeholder="Enter...">

						</div>
					</div>
				</div>


				<div class="table-responsive">
					<table class="table table-striped" id="table-gestione-ril">
						<thead>
							<tr>
								<th>Azienda</th>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Commessa</th>
								<th>Mese</th>
								<th>Anno</th>
								<th>Guadagno</th>
							</tr>
						</thead>
						<tbody>


							<!--  ROW ELENCO RAPPORTINI -->
							<div class="row">
								<c:forEach items="${list_andamenti}" var="x">
									<tr>

										<td class="text-center">${x.azienda.nomeAzienda}</td>
										<td class="text-center">${x.dipendente.persona.cognomePersona}</td>
										<td class="text-center">${x.dipendente.persona.nomePersona}</td>
										<td class="text-center">${x.nomeCommessa}</td>
										<td class="text-center">${x.rilPk.meseRil}</td>
										<td class="text-center">${x.rilPk.annoRil}</td>
										<td class="text-center">${x.guadagno}</td>

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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<<script type="text/javascript">
	
</script>






