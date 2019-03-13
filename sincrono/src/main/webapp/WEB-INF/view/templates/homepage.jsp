	
	
	<div class="row">
		<div class="col-12">			
			
			<c:set var="categoriaFlag" scope="session"  value="${sessionScope.categoria}" />
			
			<c:if test="${categoriaFlag eq 'Amministratore' }">
				ciao ${sessionScope.categoria} <br>
			</c:if>
			
			<c:if test="${categoriaFlag eq 'Amministrativo' }">
				ciao ${sessionScope.categoria} <br>
			</c:if>
			
			<c:if test="${categoriaFlag eq 'Commerciale' }">
				ciao ${sessionScope.categoria} <br>
			</c:if>
			
			<c:if test="${categoriaFlag eq 'Nessuno' }">
				ciao ${sessionScope.categoria} <br>
			</c:if>
			
		</div>
	</div>