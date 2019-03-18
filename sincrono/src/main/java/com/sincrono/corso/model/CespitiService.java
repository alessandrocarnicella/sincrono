package com.sincrono.corso.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CespitiService extends JpaRepository<Cespiti, Integer> {
	
	@Transactional
	@Modifying
	@Query("UPDATE Cespiti SET annoFunzione=:annoFunzione, categoria=:categoria, descrizione=:descrizione , dipendente=:dipendente where idcespiti=:idcespiti")
	void updateCespite(@Param("idcespiti") Integer  idcespiti,
			@Param("annoFunzione") Integer annoFunzione, 
			@Param("categoria") String categoria,
			@Param("descrizione") String descrizione, 
			@Param("dipendente") Dipendente dipendente);
}
