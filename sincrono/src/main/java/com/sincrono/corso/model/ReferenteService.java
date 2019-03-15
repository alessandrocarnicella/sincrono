package com.sincrono.corso.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReferenteService extends JpaRepository<Referente, Integer> {
	
	@Query("SELECT r.idRef FROM Referente r WHERE r.azienda = :nomeAzienda")
	int findIdRefByAziendaName(@Param("nomeAzienda") Azienda nomeAzienda);

}
