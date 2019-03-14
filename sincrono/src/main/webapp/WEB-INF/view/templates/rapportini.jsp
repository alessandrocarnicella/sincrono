
<div class="row">
	<div class="col-lg-12 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-7">
						<h4 class="card-title">Rapportini</h4>
						<p class="card-description">In questa sezione puoi aggiungere e gestire i tuoi rapportini</p>
						</div>
						<div class="col-3 m-auto">
							<div class="form-group">
								<label for="searchRil">Cerca Ril</label> 
								<input
									type="text" class="form-control" id="searchRapportini"
									placeholder="Enter...">
							
						</div>
					</div>
					<div class="col-2 btn-center">
					<button type="button" class="btn btn-icons btn-rounded btn-outline-primary btn-center" data-toggle="modal" data-target="#modal-add-ril">
						<i class="fas fa-plus fa-2x"></i></button>
					</div>
				</div>
					<div class="table-responsive" >
						<table class="table table-striped" id="table-gestione-ril" >
							<thead>
								<tr>
									
									<th>Mese</th>
									<th>Anno</th>
									<th>Ore in Sede</th>
									<th>Ore dal Cliente</th>
									<th>Ore ferie</th>
									<th>Ore permessi</th>
									<th></th>

								</tr>
							</thead>
							<tbody>


								<!--  ROW ELENCO RAPPORTINI -->
								<div class="row" >
									<c:forEach items="${list_ril}" var="x">
										<tr>
											
											<td class="text-center">${x.id.meseRil}</td>
											<td class="text-center">${x.id.annoRil}</td>
											<td class="text-center">${x.oreSede}</td>
											<td class="text-center">${x.oreCliente}</td>
											<td class="text-center">${x.oreFerie}</td>
											<td class="text-center">${x.orePermessi}</td>
											
										
											<td>
												<form action="RapportiniElimina" method="POST">
						
													<input type="hidden" name="idRil" value="${x.id.idPersonaril}">
											
													<button type="submit" class="btn btn-secondary btn-fw">
														<i class="fas fa-trash-alt fa"></i>
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
	
	
	
	
	<div class="modal fade" id="modal-add-ril" role="dialog" >
    <div class="modal-dialog">
    
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Inserisci Rapportino</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
          
        </div>
        <div class="modal-body">
          <div class="auto-form-wrapper">
              <form action="GestioneRilAdd" method="POST">
              
                <div class="form-group">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="Mese" name="meseRil" required>
                  </div>
                </div>
                
                <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Anno" name="annoRil" required>
            		</div>
               	</div>
               		 
                <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Ore in sede" name="oreSede" required>
               		</div>
               	</div>
               	
               	 <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Ore dal cliente" name="oreCliente" required>
               		</div>
               	</div>
               	
               	
               	 <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Ore ferie" name="oreFerie" required>
               		</div>
               	</div>
               	
               	 <div class="form-group">
                  	<div class="input-group">
                    	<input type="text" class="form-control" placeholder="Ore permessi" name="orePermessi" required>
               		</div>
               	</div>
               	
     
               
               	<div class="form-group">
                  <button type="submit" onclick="prova_d()" class="btn btn-success submit-btn btn-block">Inserisci</button>
                </div>
                
              </form>
              	
            </div>
        </div>
       
      </div>
      
    </div>
  </div>
  
  
  
	
	
	
	
	