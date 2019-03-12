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
	<div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 grid-margin stretch-card" >
        <div class="card card-statistics">
          <div class="card-body">
            <div class="clearfix">
              <div class="float-left">
                <i class="mdi mdi-cube text-danger icon-lg"></i>
              </div>
              <div class="float-right">
                <p class="mb-0 text-right">${x.pivaAzienda}</p>
                <div class="fluid-container">
                  <h3 class="font-weight-medium text-right mb-0 fieldNameAzienda">${x.nomeAzienda}</h3>
                </div>
              </div>
            </div>
            <p class="text-muted mt-3 mb-0">
              <i class="mdi mdi-alert-octagon mr-1" aria-hidden="true"></i>Telefono N:  ${x.telefonoAzienda}
            </p>
          </div>
        </div>
      </div>
	
	</c:forEach>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
$(document).ready(function(){
  $("#searchAziende").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $(".fieldNameAzienda").filter(function() {
      $(this).closest(".stretch-card").toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>

	
	
	
