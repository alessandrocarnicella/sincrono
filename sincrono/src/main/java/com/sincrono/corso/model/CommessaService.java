package com.sincrono.corso.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommessaService extends JpaRepository<Commessa, Integer> {
	
	@Query("SELECT com.idCommessa FROM Commessa com WHERE com.azienda = :nomeAzienda")
	List<Integer> findIdCommessaByAziendaName(@Param("nomeAzienda") Azienda nomeAzienda);
	
	@Query("SELECT com.idCommessa FROM Commessa com WHERE com.persona = :persona")
	int findIdRefByPersona(@Param("persona") Persona persona);
	
	

//	@Query("SELECT com.azienda FROM Commessa com WHERE com.azienda = :nomeazienda")
//	List<String> findByNomeAziendaCommessa(String nomeAziendaCommessa);
//	
//	@Query("SELECT com.nomeCommessa FROM Commessa com WHERE com.nomeCommessa = :nomeCommessa")
//	List<String> findByNomeCommessa(String nomeCommessa);
//
//	@Query("SELECT com.persona FROM Commessa com WHERE com.persona = :persona")
//	List<String> findByDipendente(String persona);

}
