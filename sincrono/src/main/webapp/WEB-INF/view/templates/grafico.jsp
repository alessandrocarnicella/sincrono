<div class="row">
	<c:forEach items="${list_guadagno_totale_azienda}" var="y" varStatus="count">
		<div class="col-2"></div>
		<div class="col-lg-8 grid-margin stretch-card" onclick="prova1(id=${count.count},dati=${y})">
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
	</c:forEach>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	setTimeout(function(){
		$( ".stretch-card" ).click();
		}, 500);
});
</script>