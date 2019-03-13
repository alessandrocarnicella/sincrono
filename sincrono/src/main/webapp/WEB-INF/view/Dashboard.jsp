<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.* "%>
<%@ page import="com.sincrono.corso.model.Dipendente"%>
	
	
<%		
		
		Optional<Dipendente> optDip = (Optional)session.getAttribute("dipendente");
		Dipendente dip = optDip.get();
		
		String cognomeDip = dip.getPersona().getCognomePersona();
		String nomeDip = dip.getPersona().getNomePersona();
		
		String username = cognomeDip.substring(0, 1).toUpperCase() + cognomeDip.substring(1) 
						+ " " 
						+ nomeDip.substring(0, 1).toUpperCase() + nomeDip.substring(1);
		
		String categoriaDip = dip.getCategoria().getId().getRuoloCat();
		categoriaDip = categoriaDip.substring(0, 1).toUpperCase() + categoriaDip.substring(1);

		
		session.setAttribute("username", username);
		session.setAttribute("categoria", categoriaDip);
	%>

<!DOCTYPE html>

<html lang="en">

<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link href="vendors/iconfonts/mdi/css/materialdesignicons.min.css">
<link href="vendors/css/vendor.bundle.base.css">
<link href="vendors/css/vendor.bundle.addons.css">

<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">

<link rel="shortcut icon" href="images/favicon.png" />
<meta charset="ISO-8859-1">

<layout:block name="TITLE">
	<title>Dashboard</title>
</layout:block>

</head>

<body>
	
	<div class="container-scroller">
		<%@ include file="templates/header.jsp"%>
		<div class="container-fluid page-body-wrapper  padd-0">
			<%@ include file="templates/sidebar.jsp"%>
			<div class="main-panel  padd-55">
				<div class="content-wrapper">
					
					<%@ include file="templates/homepage.jsp"%>
				</div>
				<%@ include file="templates/footer.jsp"%>
			</div>
		</div>
	</div>
</body>


<!-- SCRIPT FILE IMPORT -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<script src="vendors/js/vendor.bundle.base.js"></script>
<script src="vendors/js/vendor.bundle.addons.js"></script>
<script src="js/off-canvas.js"></script>
<script src="js/misc.js"></script>
<script src="js/dashboard.js"></script>

<style>
.nav .nav-item.dropdown .dropdown-toggle:after, .navbar-nav .nav-item.dropdown .dropdown-toggle:after{
  content: none;
}
</style>

</html>