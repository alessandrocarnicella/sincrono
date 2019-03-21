<nav class="sidebar sidebar-offcanvas" id="sidebar">
  <ul class="nav" style="margin-top:6rem;">
    <li class="nav-item">
      <a class="nav-link" href="Aziende"> 
        <i class=""></i> 
        <span class="menu-title">Aziende</span>
      </a>
    </li>

    <li class="nav-item">
      <a class="nav-link" href="Utenti"> 
        <i class=""></i> 
        <span class="menu-title">Utenti</span>
      </a>
    </li>

    <li class="nav-item">
      <a class="nav-link" href="Rapportini"> 
        <i class=""></i> 
        <span class="menu-title">Rapportini</span>
      </a>
    </li>
	
	<c:set var="categoriaFlag" scope="session"  value="${sessionScope.categoria}" />
		<c:if test="${categoriaFlag eq 'Amministratore' }">
		    <li class="nav-item">
		      <a class="nav-link" href="GestioneCommesseDipendenti"> 
		        <i class=""></i> 
		        <span class="menu-title">Gestione Commesse Dipendenti </span>
		      </a>
		    </li>
		
		    <li class="nav-item">
		      <a class="nav-link" href="GestioneAziende"> 
		        <i class=""></i> 
		        <span class="menu-title">Gestione Aziende</span>
		      </a>
		    </li>
		
		    <li class="nav-item">
		      <a class="nav-link" href="GestioneUtenti"> 
		        <i class=""></i> 
		        <span class="menu-title">Gestione Utenti</span>
		      </a>
		    </li>
				
		 <li class="nav-item">
		      <a class="nav-link" href="Cespiti"> 
		        <i class=""></i> 
		        <span class="menu-title">Cespiti </span>
		      </a>
		    </li>
		    
		</c:if>
		
		<c:set var="categoriaFlag" scope="session"  value="${sessionScope.categoria}" />
		<c:if test="${categoriaFlag eq 'Amministrativo' }">
		    <li class="nav-item">
		      <a class="nav-link" href="GestioneCommesseDipendenti"> 
		        <i class=""></i> 
		        <span class="menu-title">Gestione Commesse Dipendenti </span>
		      </a>
		    </li>
		     <li class="nav-item">
		      <a class="nav-link" href="Cespiti"> 
		        <i class=""></i> 
		        <span class="menu-title">Cespiti </span>
		      </a>
		    </li>
		</c:if>
		
		
		
		<c:set var="categoriaFlag" scope="session"  value="${sessionScope.categoria}" />
		<c:if test="${categoriaFlag eq 'Commerciale' }">
		  
		
		    <li class="nav-item">
		      <a class="nav-link" href="GestioneAziende"> 
		        <i class=""></i> 
		        <span class="menu-title">Gestione Aziende</span>
		      </a>
		    </li>
	
		</c:if>
		
		
		
		


  </ul>
</nav>


			