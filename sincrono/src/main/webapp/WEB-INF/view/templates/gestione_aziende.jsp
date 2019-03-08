<div class="col-lg-12 grid-margin stretch-card">
	<div class="card">
		<div class="card-body">
			<h4 class="card-title">Gestione Aziende</h4>
			<p class="card-description">Puoi modificare le aziende e farti un
				pacco di cazzi di tutti ( però ricordati della Privacy e fai i corsi
				online !)</p>
			<div class="table-responsive">
				<table class="table table-striped">
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