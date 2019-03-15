<div class="row">
	<div class="col-lg-6 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<div class="chartjs-size-monitor"
					style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
					<div class="chartjs-size-monitor-expand"
						style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
						<div
							style="position: absolute; width: 1000000px; height: 1000000px; left: 0; top: 0"></div>
					</div>
					<div class="chartjs-size-monitor-shrink"
						style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
						<div
							style="position: absolute; width: 200%; height: 200%; left: 0; top: 0"></div>
					</div>
				</div>
				<h4 class="card-title">Area chart</h4>
				<canvas id="areaChart"
					style="height: 204px; display: block; width: 409px;" width="613"
					height="306" class="chartjs-render-monitor"></canvas>
			</div>
		</div>
	</div>
	<div class="col-lg-6 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<div class="chartjs-size-monitor"
					style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
					<div class="chartjs-size-monitor-expand"
						style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
						<div
							style="position: absolute; width: 1000000px; height: 1000000px; left: 0; top: 0"></div>
					</div>
					<div class="chartjs-size-monitor-shrink"
						style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
						<div
							style="position: absolute; width: 200%; height: 200%; left: 0; top: 0"></div>
					</div>
				</div>
				<h4 class="card-title">Doughnut chart</h4>
				<canvas id="doughnutChart"
					style="height: 204px; display: block; width: 409px;" width="613"
					height="306" class="chartjs-render-monitor"></canvas>
			</div>
		</div>
	</div>
</div>



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
								<th></th>

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








