<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored="false"%>


<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>

<!DOCTYPE html>

<html lang="en">

<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

  <link href="vendors/iconfonts/mdi/css/materialdesignicons.min.css">
  <link href="vendors/css/vendor.bundle.base.css">
  <link href="vendors/css/vendor.bundle.addons.css">

  <link rel="stylesheet" type="text/css" href="css/style.css">

  <link rel="shortcut icon" href="images/favicon.png" />
  <meta charset="ISO-8859-1">
  
  <layout:block name="TITLE">
  	<title>LOGIN</title>
  </layout:block>

</head>

<body>
  <div class="container-scroller">

    <layout:block name="header">
    </layout:block>

    <div class="container-fluid page-body-wrapper  padd-0">

      <layout:block name="sidebar">
      </layout:block>

      <layout:block name="content">
        <div class="container-fluid page-body-wrapper full-page-wrapper auth-page">
          <div class="content-wrapper d-flex align-items-center auth auth-bg-1 theme-one">
            <div class="row w-100">
              <div class="col-lg-4 mx-auto">
                <div class="auto-form-wrapper">
                  
                  <!-- TODO: INSERIRE METODO E ACTION -->
                  <form action="#" method="post">
                    <div class="form-group">
                      <label class="label">Email</label>
                      <div class="input-group">
                        <input type="email" name="email" class="form-control" placeholder="gino@onlus.it">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="label">Password</label>
                      <div class="input-group">
                        <input type="password" name="password" class="form-control" placeholder="*********">
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <!-- QUESTO DEVE DIVENTARE UN BUTTON CON TYPE SUBMIT COSI DA MANDARE IN POST I DATI-->
                      <a class="btn btn-primary submit-btn btn-block" href="Dashboard">Login</a>
                    </div>
                    
                    <div class="row">
                    	<div class="col-12 text-center form-group">
                    		<a href="ForgotPassword" class="text-small forgot-password text-black">Forgot Password</a>
                    	</div>
                    </div>
	                   
                  </form>
                  
                </div>
                <ul class="auth-footer">
                  <li><a href="#">Conditions</a></li>
                  <li><a href="#">Help</a></li>
                  <li><a href="#">Terms</a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </layout:block>
      
      	
      
    </div>
	

    <layout:block name="footer">

   	</layout:block>
    </div>
    
</body>


<!-- SCRIPT FILE IMPORT -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script src="vendors/js/vendor.bundle.base.js"></script>
<script src="vendors/js/vendor.bundle.addons.js"></script>
<script src="js/off-canvas.js"></script>
<script src="js/misc.js"></script>
<script src="js/dashboard.js"></script>


</html>