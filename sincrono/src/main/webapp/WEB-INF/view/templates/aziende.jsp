<!--  ROW ELENCO AZIENDE -->


<div class="row">
<div class="col-8">
		<h3>Elenco Aziende</h3>
	</div>
	<div class="col-4">
		<div class="form-group">
                          <label for="searchAziende">Cerca Aziende</label>
                          <input type="text" class="form-control" id="searchAziende" placeholder="Enter...">
                        </div>
	</div> 
	
	<c:forEach items="${list_az}" var="x">
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 grid-margin stretch-card" data-toggle="modal" data-target="#modal-detail-aziende"
			data-namen="${x.nomeAzienda}" 
			data-email="${x.emailAzienda}"
			data-address="${x.indirizzoAzienda}"
			data-numdip="${x.numdipAzienda}"
			data-piva="${x.pivaAzienda}"
			data-societa="${x.societa}"
			data-telefono="${x.telefonoAzienda}"
			data-status="${x.statusAzienda}">
	        
	        <div class="card card-statistics">
	          <div class="card-body">
	            <div class="clearfix">
	              <div class="float-left">
	              	
	              	<h3 class="px18 font-weight-medium mb-0 fieldNameAzienda">${x.nomeAzienda}</h3>
	            
	                <p class="mb-0">${x.pivaAzienda}</p>
	               	 <p class="text-muted mt-3 mb-0">Telefono N:  ${x.telefonoAzienda}</p>
	              </div>
	            </div>
	          </div>
	        </div>
	  
	    </div>
	</c:forEach>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>





<div class="modal fade" id="modal-detail-aziende" role="dialog" >
    <div class="modal-dialog">
    
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Dettagli Azienza</h4>
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
               
               
            </div>
        </div>
       
      </div>
      
    </div>
  </div>

	
	
	
