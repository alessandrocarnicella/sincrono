<nav class="navbar default-layout col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
  <div class="text-center navbar-brand-wrapper d-flex align-items-top justify-content-center">
    <a class="navbar-brand brand-logo" href="Dashboard"> <img src="images/logo.svg" alt="logo" /> </a>
  </div>
  <div class="navbar-menu-wrapper d-flex align-items-center">
  		<a href="" type="button" class="btn btn-success btn-fw">
           <i class="fas fa-check-circle"></i>${sessionScope.categoria}
        </a>
		
		<ul class="navbar-nav navbar-nav-right">
          
          
	        <li class="nav-item dropdown d-none d-xl-inline-block">
	              <span class="profile-text">	
	              	Ciao, ${sessionScope.username}!
	              </span>   
	              <span class="ml-3">
	              	 <a href="Logout" type="button" class="btn btn-danger btn-fw">
	              		<i class="fas fa-sign-out-alt"></i>Logout
	          		 </a>
	              </span>           
			</li>
        </ul>
    
  </div>
</nav>