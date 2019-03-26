<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="en">

<head>
	<!-- IMPORT FILE CSS  -->
	<%@ include file="import/css_import.html"%>
	<meta charset="ISO-8859-1">
	
	<layout:block name="TITLE">
		<title>Contact Us</title>
	</layout:block>
	
	</head>
	
	<body>
		<div class="container-scroller">
			<%@ include file="templates/header.jsp"%>
			<div class="container-fluid page-body-wrapper  padd-0">
				<%@ include file="templates/sidebar.jsp"%>
				<div class="main-panel  padd-55">
					<div class="content-wrapper">
						<%@ include file="templates/aziende.jsp"%>
					</div>
					<%@ include file="templates/footer.jsp"%>
				</div>
			</div>
		</div>
	</body>

	<!-- IMPORT FILE JS  -->
	<%@ include file="import/js_import.html"%>

</html>