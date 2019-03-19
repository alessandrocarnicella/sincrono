<nav class="navbar default-layout col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
  <div class="text-center navbar-brand-wrapper d-flex align-items-top justify-content-center">
    <a class="navbar-brand brand-logo" href="DashboardHome"> <img src="images/logo.svg" alt="logo" /> </a>
  </div>
  <div class="navbar-menu-wrapper d-flex align-items-center">
  	 
  		<button class="btn btn-primary">
           <i class="fas fa-check-circle"></i>${sessionScope.categoria}
        </button>
		
		<ul class="navbar-nav navbar-nav-right">        
	        <li class="nav-item dropdown d-none d-xl-inline-block">
              <span class="profile-text">	
              		Ciao ${sessionScope.username} !
              </span>   
              <span class="ml-3">
              	<form class="d-inline" action="Logout" method="post">
					<button type="submit" class="btn btn-danger">
						<i class="fas fa-sign-out-alt"></i>Logout
					</button>
				</form>
              </span>         
			</li>
        </ul>
		
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
          		<i class="fas fa-bars"></i>
    	</button>
  </div>
</nav>