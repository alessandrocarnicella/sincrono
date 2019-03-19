package com.sincrono.corso.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReferenteService extends JpaRepository<Referente, Integer> {
	
	@Query("SELECT r.idRef FROM Referente r WHERE r.azienda = :nomeAzienda")
	int findIdRefByAziendaName(@Param("nomeAzienda") Azienda nomeAzienda);
	
	@Transactional
	@Modifying
	@Query("UPDATE Referente SET telefonoRef=:telefonoRef where idRef=:idRef")
	void updateReferente(@Param("idRef") Integer idRef,
			@Param("telefonoRef") String telefonoRef); 
}
