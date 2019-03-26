<nav class="sidebar sidebar-offcanvas" id="sidebar">
  <ul class="nav" style="margin-top:6rem;">
    <li class="nav-item">
      <a class="nav-link" href="Aziende" id="azienda_nav"> 
        <i class="fas fa-building"></i> &nbsp
        <span class="menu-title">Aziende</span>
      </a>
    </li>

    <li class="nav-item" >
      <a class="nav-link" href="Utenti" id="utenti_nav"> 
        <i class="fas fa-users"></i> &nbsp
        <span class="menu-title">Utenti</span>
      </a>
    </li>

    <li class="nav-item" >
      <a class="nav-link" href="Rapportini" id="rapportini_nav"> 
        <i class="fas fa-file-signature"></i> &nbsp
        <span class="menu-title">Rapportini</span>
      </a>
    </li>
	

	<c:if test="${categoria == 'Amministratore' }">
	    <li class="nav-item" >
	      <a class="nav-link" href="GestioneCommesseDipendenti" id="commesse_nav"> 
	        <i class="fas fa-calendar-alt"></i> &nbsp
	        <span class="menu-title">Gestione Commesse Dipendenti </span>
	      </a>
	    </li>
	
	    <li class="nav-item" >
	      <a class="nav-link" href="GestioneAziende" id="gestione_aziende_nav"> 
	       	<i class="fas fa-rocket"></i> &nbsp
	        <span class="menu-title">Gestione Aziende</span>
	      </a>
	    </li>
	
	    <li class="nav-item" >
	      <a class="nav-link" href="GestioneUtenti" id="gestione_utenti_nav"> 
	        <i class="fas fa-cogs"></i> &nbsp
	        <span class="menu-title">Gestione Utenti</span>
	      </a>
	    </li>
			
	 <li class="nav-item" >
	      <a class="nav-link" href="Cespiti" id="cespiti_nav"> 
	        <i class="fas fa-chair"></i> &nbsp
	        <span class="menu-title">Cespiti </span>
	      </a>
	    </li>
	    
	</c:if>
	
	<c:if test="${categoria == 'Amministrativo' }">
	    <li class="nav-item" >
	      <a class="nav-link" href="GestioneCommesseDipendenti" id="commesse_nav"> 
	       	<i class="fas fa-calendar-alt"></i> &nbsp
	        <span class="menu-title">Gestione Commesse Dipendenti </span>
	      </a>
	    </li>
		<li class="nav-item" >
			<a class="nav-link" href="Cespiti" id="cespiti_nav"> 
				<i class="fas fa-chair"></i>  &nbsp
				<span class="menu-title">Cespiti </span>
			</a>
		</li>
	</c:if>


	<c:if test="${categoria == 'Commerciale' }">
	  
	    <li class="nav-item" >
	      <a class="nav-link" href="GestioneAziende" id="gestione_aziende_nav"> 
	        <i class="fas fa-rocket"></i> &nbsp
	        <span class="menu-title">Gestione Aziende</span>
	      </a>
	    </li>

	</c:if>
	
	<li class="nav-item" >
      <a class="nav-link" href="ContactUs" id="contact_us"> 
        <i class="fas fa-envelope-open"></i> &nbsp
        <span class="menu-title">ContactUs</span>
      </a>
    </li>
	
  </ul>
</nav>





			