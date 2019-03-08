<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>


<layout:extends name="Login">
	
	<layout:put block="TITLE" type="REPLACE">
		<title>GESTIONE AZIENDE</title>
	</layout:put>
	
	<layout:put block="header" type="REPLACE">
		<%@ include file="templates/header.jsp"%>
	</layout:put>

	<layout:put block="sidebar" type="REPLACE">
		<%@ include file="templates/sidebar.jsp" %>
	</layout:put>

	<layout:put block="content" type="REPLACE">
		<div class="main-panel  padd-55">
			<div class="content-wrapper">
				<%@ include file="templates/gestione_aziende.jsp" %>
			</div>
			
			<layout:put block="footer" type="REPLACE">
				<%@ include file="templates/footer.jsp" %>
			</layout:put>
			
		</div>
	</layout:put>


</layout:extends>