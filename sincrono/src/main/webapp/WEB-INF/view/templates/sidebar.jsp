<nav class="sidebar sidebar-offcanvas" id="sidebar">
  <ul class="nav mt-5">
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
		</c:if>
		
		<c:if test="${categoriaFlag eq 'Amministrativo' }">
			ciao ${sessionScope.categoria} <br>
		</c:if>
		
		<c:if test="${categoriaFlag eq 'Commerciale' }">
			ciao ${sessionScope.categoria} <br>
		</c:if>
		
		<c:if test="${categoriaFlag eq 'Nessuno' }">
			ciao ${sessionScope.categoria} <br>
		</c:if>


  </ul>
</nav>


			