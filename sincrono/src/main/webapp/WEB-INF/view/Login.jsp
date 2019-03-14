<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>

<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="en">

<head>
	<!-- IMPORT FILE CSS  -->	
  	<%@ include file="import/css_import.html"%>
  	
  	<meta charset="ISO-8859-1">
  
	<layout:block name="TITLE">
		<title>LOGIN</title>
	</layout:block>

</head>

<body>
  <div class="container-scroller">

    <layout:block name="header">
    </layout:block>

    <div class="container-fluid page-body-wrapper padd-0">

      <layout:block name="sidebar">
      </layout:block>

      <layout:block name="content">
        <div class="container-fluid page-body-wrapper full-page-wrapper auth-page">
          <div class="content-wrapper d-flex align-items-center auth auth-bg-1 theme-one">
            <div class="row w-100">
              <div class="col-lg-4 mx-auto">
                <div class="auto-form-wrapper">
                  
                  <form action="Dashboard" method="post">
                  	<div class="form-group">
                  	
                  	<c:set var="loginFlag" scope="session" value="${error_login}" />
					
					<c:if test="${loginFlag eq true}">
		  				<p class="text-danger">Login Errato</p>
					</c:if>
						               		
                   	</div>
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
                      <button type="submit" class="btn btn-primary submit-btn btn-block">Login</button>
                    </div>
                    
                    <div class="row">
                    	<div class="col-12 text-center form-group">
                    		<a href="ForgotPassword" class="text-small forgot-password text-black">Forgot Password</a>
                    		<br/>
                    		${new_psw}
                    	</div>
                    </div>
	                   
                  </form>
                  
                </div>
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

	<!-- IMPORT FILE JS -->	
  	<%@ include file="import/js_import.html"%>
  	
</html>