package com.sincrono.corso.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

public interface CommessaService extends JpaRepository<Commessa, Integer> {
	
	@Query("SELECT com.idCommessa FROM Commessa com WHERE com.azienda = :nomeAzienda")
	List<Integer> findIdCommessaByAziendaName(@Param("nomeAzienda") Azienda nomeAzienda);
	
	@Query("SELECT com.idCommessa FROM Commessa com WHERE com.persona = :persona")
	int findIdRefByPersona(@Param("persona") Persona persona);
	

	@Query("SELECT com.persona FROM Commessa com WHERE com.persona = :persona")
	List<String> findByDipendente(String persona);

	@Query("SELECT com.nomeCommessa FROM Commessa com WHERE com.nomeCommessa = :nomeCommessa")
	List<String> findByNomeCommessa(@Param("nomeCommessa") String nomeCommessa);

	@Query("SELECT com.persona FROM Commessa com WHERE com.persona = :idDipendente")
	List<Integer> findByIdDipendente(@Param("idDipendente") Persona pers);
	
	@Query("SELECT com.nomeCommessa FROM Commessa com WHERE com.nomeCommessa = :nomeCommessa AND com.azienda=:nomeAziendaCom")
	List<String> findByNomeCommessaAndNomeAzienda(@Param("nomeCommessa") String nomeCommessa, @Param("nomeAziendaCom") Azienda nomeAziendaCommessa);


	@Transactional
	@Modifying
	@Query("UPDATE Commessa SET idCommessa=:idCommessa, nomeCommessa=:nomeCommessa, azienda=:nomeAziendaCommessa , tariffaCliente=:tariffaCliente, persona=:idDipendente WHERE idCommessa=:idCommessa")
	int updateCommessa(@Param("idCommessa") int idCommessa,
			@RequestParam("tariffaCliente") double tariffaCliente,
			@RequestParam("nomeCommessa") String nomeCommessa,
			@RequestParam("idDipendente") Persona idDipendente,
			@RequestParam("nomeAziendaCommessa") Azienda nomeAziendaCommessa);

}
