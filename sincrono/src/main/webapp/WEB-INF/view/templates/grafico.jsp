
<div class="row">
	<c:forEach items="${list_guadagno_totale_azienda}" var="y" varStatus="count">
		<div class="col-2"></div>
		<div class="col-lg-8 grid-margin stretch-card"
			onclick="prova1(id=${count.count},dati=${y})">
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
					<h4 class="card-title">${nomeAzienda}</h4>
					<canvas id="areaChart${count.count}"
						style="height: 204px; display: block; width: 409px;" width="613"
						height="306" class="chartjs-render-monitor"></canvas>
				</div>
			</div>
		</div>
		
	<button onclick="topFunction()" id="myBtn" title="Go to top">
		<i class="fas fa-arrow-up fa-2x text-primary"></i>
	</button>
	
			<div class="col-lg-12 grid-margin stretch-card">
				<div class="card">
	
					<div class="card-body">
						<div class="row">
							<div class="col-8"></div>
							<div class="col-4 m-auto">
								<div class="form-group">
									<label for="searchRil">Cerca </label> <input type="text"
										class="form-control" id="searchRapportini"
										placeholder="Enter...">
	
								</div>
							</div>
						</div>
						<div class="row">
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
											<c:forEach items="${list_andamenti_azienda}" var="x">
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
		</c:forEach>

</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	setTimeout(function(){
		$( ".stretch-card" ).click();
		}, 500);
});


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
