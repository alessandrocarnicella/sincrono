package com.sincrono.corso.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AziendaService extends JpaRepository<Azienda, String> {
	
	@Query("SELECT a.nomeAzienda FROM Azienda a where  a.nomeAzienda = :nomeAzienda")
	List<String> findByNomeAzienda(@Param("nomeAzienda") String nomeAzienda);
	
	@Query("SELECT a.emailAzienda FROM Azienda a where  a.emailAzienda = :emailAzienda")
	List<String>  findByEmailAzienda(@Param("emailAzienda") String emailAzienda);
	
	@Query("SELECT a.pivaAzienda FROM Azienda a where  a.pivaAzienda = :pivaAzienda")
	List<String>  findByPivaAzienda(@Param("pivaAzienda") String pivaAzienda);

	@Transactional
	@Modifying
	@Query("UPDATE Azienda SET emailAzienda=:emailAzienda, indirizzoAzienda=:indirizzoAzienda, numdipAzienda=:numdipAzienda , pivaAzienda=:pivaAzienda, societa=:societa, statusAzienda=:statusAzienda, telefonoAzienda=:telefonoAzienda where nomeAzienda=:nomeAzienda")
	void updateAzienda(@Param("nomeAzienda") String nomeAzienda,
			@Param("emailAzienda") String emailAzienda,
			@Param("indirizzoAzienda") String indirizzoAzienda,
			@Param("numdipAzienda") Integer numdipAzienda, 
			@Param("pivaAzienda") String pivaAzienda, 
			@Param("societa") String societa,
			@Param("statusAzienda") byte statusAzienda,
			@Param("telefonoAzienda") String telefonoAzienda);
	
	
}
