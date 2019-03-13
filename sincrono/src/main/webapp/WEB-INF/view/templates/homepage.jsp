	
	
	<div class="row">
		<div class="col-12">			
			
			
<c:set var="categoriaFlag" scope="session"  value="${sessionScope.categoria}" />
			
			<c:if test="${categoriaFlag eq 'Amministratore' }">
				 ${sessionScope.categoria} <br>
			</c:if>
			
			<c:if test="${categoriaFlag eq 'Amministrativo' }">
				 ${sessionScope.categoria} <br>
			</c:if>
			
			<c:if test="${categoriaFlag eq 'Commerciale' }">
				 ${sessionScope.categoria} <br>
			</c:if>
			
			<c:if test="${categoriaFlag eq 'Nessuno' }">
				schiavo <br>
			</c:if>
			
		</div>
	</div>