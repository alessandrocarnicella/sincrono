<c:choose>
	<c:when test="${categoria == 'Amministratore' or categoria == 'Commerciale' or categoria == 'Amministrativo'}">
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
		
	<button onclick="topFunction()" id="myBtn" title="Go to top">
		<i class="fas fa-arrow-up fa-2x text-primary"></i>
	</button>
	
	</c:when>
	<c:otherwise>
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
               	  	<img src="images/sirena.gif" class="w-50px" >
               		<h4 class="card-title d-inline">Benvenuto in Sincronia!</h4>
                  	<div class="media">
                    	<div class="media-body">
                      		<p class="card-text">Testo provvisorio</p>
                    	</div>
                  	</div>                  
                </div>
              </div>
           	</div>
		</div>
	</c:otherwise>
</c:choose>


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


