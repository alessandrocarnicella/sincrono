
<div class="row">
	<div class="col-lg-12 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-7">
						<h4 class="card-title">Gestione Aziende</h4>
						<p class="card-description">Puoi modificare le aziende e farti
							un pacco di cazzi di tutti ( però ricordati della Privacy e fai i
							corsi online !)</p>
						</div>
						<div class="col-3">
							<div class="form-group">
								<label for="searchAziende">Cerca Aziende</label> <input
									type="text" class="form-control" id="searchGestioneAziende"
									placeholder="Enter...">
							
						</div>
					</div>
					<div class="col-2">
					<button type="button" class="btn btn-icons btn-rounded btn-outline-success">
						<i class="fas fa-plus fa-2x"></i></button>
					</div>
				</div>
					<div class="table-responsive">
						<table class="table table-striped" id="table-gestione-aziende">
							<thead>
								<tr>
									<th>User</th>
									<th>Nome Azienda</th>
									<th>Email</th>
									<th>Partita iva</th>
									<th class="text-center">Status</th>
									<th></th>
								</tr>
							</thead>
							<tbody>


								<!--  ROW ELENCO AZIENDE -->
								<div class="row">
									<c:forEach items="${list_az}" var="x">
										<tr>
											<td class="py-1"><img
												src="../../images/faces-clipart/pic-1.png" alt="image"></td>
											<td>${x.nomeAzienda}</td>
											<td>${x.emailAzienda}</td>
											<td>${x.pivaAzienda}</td>
											<td class="text-center"><c:choose>
													<c:when test="${x.statusAzienda eq 1}">
														<button type="button"
															class="btn btn-icons btn-rounded btn-success"></button>
													</c:when>
													<c:otherwise>
														<button type="button"
															class="btn btn-icons btn-rounded btn-danger"></button>
													</c:otherwise>
												</c:choose></td>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
$(document).ready(function(){
  $("#searchGestioneAziende").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#table-gestione-aziende tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>