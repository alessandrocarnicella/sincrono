
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
								<input
									type="text" class="form-control" id="searchGestioneAziende"
									placeholder="Enter...">
							
						</div>
					</div>
					<div class="col-2 btn-center">
					<button type="button" class="btn btn-icons btn-rounded btn-outline-primary btn-center" data-toggle="modal" data-target="#modal-add-aziende">
						<i class="fas fa-plus fa-2x"></i></button>
					</div>
				</div>
					<div class="table-responsive" >
						<table class="table table-striped" id="table-gestione-aziende" >
							<thead>
								<tr>
									
									<th>Nome Azienda</th>
									<th>Email</th>
									<th>Partita iva</th>
									<th class="text-center">Status</th>
									<th></th>

								</tr>
							</thead>
							<tbody>


								<!--  ROW ELENCO AZIENDE -->
								<div class="row" >
									<c:forEach items="${list_az}" var="x">
										<tr>
											
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
												<button type="button" class="btn btn-secondary btn-fw" id="edit-aziende" data-toggle="modal" data-target="#modal-edit-aziende"
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
						
												<input type="hidden" name="nomeAzienda" value="${x.nomeAzienda}">
										
												<button type="submit" class="btn btn-secondary btn-fw">
													<i class="fas fa-trash-alt fa"></i>
												</button>
											</form>
											</td>
											<td>
													<form action="GestioneAziendePrint" method="POST">
						
												<input type="hidden" name="nomeAzienda" value="${x.nomeAzienda}">
										
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
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>

$(document).ready(function(){
  $("#searchGestioneAziende").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#table-gestione-aziende tr").not('thead tr').filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
  
  $("#edit-aziende").on("click", function() {
		 var namen = $(this).data("namen");
		 var email = $(this).data("email");
		 var address = $(this).data("address");
		 var numdip = $(this).data("numdip");
		 var piva = $(this).data("piva");
		 var societa = $(this).data("societa");
		 var telefono = $(this).data("telefono");
		 var status = $(this).data("status");
		 
		$("#modal-edit-aziende input[name=nomeAzienda]").val(namen);
		$("#modal-edit-aziende input[name=emailAzienda]").val(email);
		$("#modal-edit-aziende input[name=indirizzoAzienda]").val(address);
		$("#modal-edit-aziende input[name=numdipAzienda]").val(numdip);
		$("#modal-edit-aziende input[name=pivaAzienda]").val(piva);
		$("#modal-edit-aziende input[name=societa]").val(societa);
		$("#modal-edit-aziende input[name=status]").val(status);
		$("#modal-edit-aziende input[name=telefonoAzienda]").val(telefono);


	  });
  
});
</script>





  <!--  QUESTO è IL MODAL PER L'ADD -->


  <div class="modal fade" id="modal-add-aziende" role="dialog" >
    <div class="modal-dialog">
    
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Inserisci Azienda</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
          
        </div>
        <div class="modal-body">
          <div class="auto-form-wrapper">
          <!-- TODO: INSERIRE ACTION PER INSERT DATABASE -->
              <form action="GestioneAziendeAdd" method="POST">
              
                <div class="form-group">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="Nome Azienda" name="nomeAzienda">
                  </div>
                </div>
                
                <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Email Azienda" name="emailAzienda">
            		</div>
               	</div>
               		 
                <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Indirizzo" name="indirizzoAzienda">
               		</div>
               	</div>
               	
               	 <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Numero dipendenti" name="numdipAzienda">
               		</div>
               	</div>
               	
               	
               	 <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Partita IVA" name="pivaAzienda">
               		</div>
               	</div>
               	
               	 <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Nome Società" name="societa">
               		</div>
               	</div>
               	
               	 <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Telefono" name="telefonoAzienda">
               		</div>
               	</div>
               	<input type="hidden" name="statusAzienda"  value="1">
               
               
                <div class="form-group">
                  <button type="submit" class="btn btn-success submit-btn btn-block">Inserisci</button>
                </div>
              </form>
            </div>
        </div>
       
      </div>
      
    </div>
  </div>
  
  
  
  
  
  
  <!--  QUESTO è IL MODAL PER L'EDIT -->
  
  <div class="modal fade" id="modal-edit-aziende" role="dialog" >
    <div class="modal-dialog">
    
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Edit Azienza</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
          
        </div>
        <div class="modal-body">
          <div class="auto-form-wrapper">
          <!-- TODO: INSERIRE ACTION PER INSERT DATABASE -->
              
                <div class="form-group">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="Nome Azienda" name="nomeAzienda">
                  </div>
                </div>
                
                <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Email Azienda" name="emailAzienda">
            		</div>
               	</div>
               		 
                <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Indirizzo" name="indirizzoAzienda">
               		</div>
               	</div>
               	
               	 <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Numero dipendenti" name="numdipAzienda">
               		</div>
               	</div>
               	
               	
               	 <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Partita IVA" name="pivaAzienda">
               		</div>
               	</div>
               	
               	 <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Nome Società" name="societa">
               		</div>
               	</div>
               	
               	 <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Telefono" name="telefonoAzienda">
               		</div>
               	</div>
               	<input type="hidden" name="statusAzienda" value="1">
               
                  <button type="submit" class="btn btn-success submit-btn btn-block">Modifica</button>
               
            </div>
        </div>
       
      </div>
      
    </div>
  </div>

	
	




