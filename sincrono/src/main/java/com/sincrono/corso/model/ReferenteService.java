package com.sincrono.corso.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReferenteService extends JpaRepository<Referente, Integer> {
	
	@Query("SELECT a.telefonoRef FROM Referente a where  a.azienda = :nomeAzienda")
	String findTelefonoByAziendaName(@Param("nomeAzienda") String nomeAzienda);

}
