<!--  ROW ELENCO AZIENDE -->
<div class="row">
	<div class="col-12">
		<h3>Elenco Aziende</h3>
	</div>
	<c:forEach items="${list}" var="x">
	<div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 grid-margin stretch-card">
        <div class="card card-statistics">
          <div class="card-body">
            <div class="clearfix">
              <div class="float-left">
                <i class="mdi mdi-cube text-danger icon-lg"></i>
              </div>
              <div class="float-right">
                <p class="mb-0 text-right">${x.getPivaAzienda()}</p>
                <div class="fluid-container">
                  <h3 class="font-weight-medium text-right mb-0">${x.getNomeAzienda()}</h3>
                </div>
              </div>
            </div>
            <p class="text-muted mt-3 mb-0">
              <i class="mdi mdi-alert-octagon mr-1" aria-hidden="true"></i>Telefono N:  ${x.getTelefonoAzienda()}
            </p>
          </div>
        </div>
      </div>

	</c:forEach>
</div>
<hr>
	
	
	
