<!--  ROW ELENCO AZIENDE -->
<div class="row">
	<div class="col-8">
			<h3>Elenco Aziende</h3>
			<p class="text-muted">Clicca sulle card per visualizzare il dettaglio dell'azienda!</p>
		</div>
		<div class="col-4">
			<div class="form-group">
	                          <label for="searchAziende">Ricerca Aziende</label>
	                          <input type="text" class="form-control" id="searchAziende" placeholder="Enter...">
	                        </div>
		</div> 
		
		<c:forEach items="${list_az}" var="x" varStatus="count">
			<div class="col-xl-3 col-lg-4 col-sm-6 grid-margin stretch-card" id="${count.count}" 
				onclick="launch_modal_aziende(id=${count.count})"
				data-namen="${x.nomeAzienda}" 
				data-email="${x.emailAzienda}"
				data-address="${x.indirizzoAzienda}"
				data-numdip="${x.numdipAzienda}"
				data-piva="${x.pivaAzienda}"
				data-societa="${x.societa}"
				data-telefono="${x.telefonoAzienda}"
				data-status="${x.statusAzienda}">
		        
				
				
				<c:set var="status_int" scope="page" value="${x.statusAzienda}"/> 
				<c:choose>
				    <c:when test="${status_int==1}">
						<c:set var="status_string" scope="page" value="success"/> 
				    </c:when>    
				    <c:otherwise>
						<c:set var="status_string" scope="page" value="danger"/>
				    </c:otherwise>
				</c:choose>
				
				
		        <div class="card card-statistics ${status_string}">
		          <div class="card-body">
		            <div class="clearfix">
		              <div class="float-left">              
		              	<h3 class="px18 font-weight-medium mb-0 fieldNameAzienda">${x.nomeAzienda}</h3>
		                <p class="mb-0">${x.pivaAzienda}</p>
		               	<p class="text-muted mt-3 mb-0">Telefono N:  ${x.telefonoAzienda}</p>
		              </div>
		              <div class="float-right">
              			<c:choose>
							<c:when test="${x.statusAzienda eq 1}">
								<i class="fas fa-circle text-success"></i>
							</c:when>
							<c:otherwise>
								<i class="fas fa-circle text-danger"></i>
							</c:otherwise>
						</c:choose>
		              </div>
		            </div>
		          </div>
		        </div>
		  		

		  		
				<div class="modal fade" id="modalA${count.count}" role="dialog" >
				    <div class="modal-dialog">
				    
				      <div class="modal-content" >
				      	
				        <div class="modal-header">
		          			<h4 class="modal-title">Dettagli Azienza</h4>
				        	<button type="button" class="close" data-dismiss="modal">&times;</button>	       
				        </div>
				        <div class="modal-body">
				        	<div class="row d-none" id="spinner">
				          			<div class="col-2"></div>
				          			<div class="col-8 text-center">
				          				<i  class="fas fa-sync-alt fa-5x"></i>
				          			</div>		
				          			<div class="col-2"></div>		          				
				          		</div>
				          <div class="auto-form-wrapper"> 
				          		
				          		
				                       
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
				               	<!-- TODO: controllare sto cazzzo di campo -->
				               	<input type="hidden" name="statusAzienda" value="${x.statusAzienda}">			               
				            </div>
				            <div class="row loading_chart">
				            	<div class="col-12">
		      				        <form action="Andamento" method="POST">
										<input type="hidden" name="nomeAziendaAndamento" value="${x.nomeAzienda}">
											<button type="submit"  class="w-100 btn btn-success btn-fw">
												<h4 class="d-inline">Visualizza grafico andamento</h4> 
												<i class="fas fa-chart-area fa-2x"></i>
											</button>
									</form>
								</div>
							</div>
				        </div>
				       
				      </div>
				      
				    </div>
			 	</div>
			 	
			 	
		  		
		    </div>
	</c:forEach>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style>
.glyphicon-refresh-animate {
   -webkit-animation-name: rotateThis;
   -webkit-animation-duration: 2s;
   -webkit-animation-iteration-count: infinite;
   -webkit-animation-timing-function: linear;
}

@-webkit-keyframes "rotateThis" {
 from { 
        -webkit-transform: rotate( 0deg );  
    }
 to  { 
        -webkit-transform: rotate( 360deg ); 
    }
}


</style>

<script>
function launch_modal_aziende(id){
	
	// RECUPERA I CAMPI DAI DATANAME E RIEMPIE LA FORM SHOW AZIENDA
	var namen = $("#"+id).data("namen");
	var email = $("#"+id).data("email");
	var address = $("#"+id).data("address");
	var numdip = $("#"+id).data("numdip");
	var piva = $("#"+id).data("piva");
	var societa = $("#"+id).data("societa");
	var telefono = $("#"+id).data("telefono");
	var status = $("#"+id).data("status");

	$("#modalA"+id+" input[name=nomeAzienda]").val(namen).prop("disabled",true);
	$("#modalA"+id+" input[name=emailAzienda]").val(email).prop("disabled",true);
	$("#modalA"+id+" input[name=indirizzoAzienda]").val(address).prop("disabled",true);
	$("#modalA"+id+" input[name=numdipAzienda]").val(numdip).prop("disabled",true);
	$("#modalA"+id+" input[name=pivaAzienda]").val(piva).prop("disabled",true);
	$("#modalA"+id+" input[name=societa]").val(societa).prop("disabled",true);
	$("#modalA"+id+" input[name=status]").val(status).prop("disabled",true);
	$("#modalA"+id+" input[name=telefonoAzienda]").val(telefono).prop("disabled",true);
	
	$("#modalA"+id).modal('show');
	
}

$(document).ready( function(){
	
	
    $(".loading_chart").on( "click", function( e ) {
    	
		$(".modal-dialog").addClass('modal-dialog-centered');
    	
    	$("#spinner").removeClass('d-none');
    	$(".auto-form-wrapper").hide();
		$(".modal-header").hide();
		$(".loading_chart").hide();
		
		
		//var $icon = $( this ).find( "#spinner" ),
        animateClass = "glyphicon-refresh-animate";
		
        $("#spinner").addClass( animateClass );
        

        
        
        // setTimeout is to indicate some async operation
        window.setTimeout( function() {
            $("#spinner").removeClass( animateClass );
        }, 7000 );

    });    
});

</script>
