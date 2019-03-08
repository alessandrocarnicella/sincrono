<h1>pROVA </h1>
    <hr>
	<c:forEach items="${list}" var="x">
		<tr>
			<td>${x.getNomeAzienda()}</td>
			<br>
		</tr>
	</c:forEach>