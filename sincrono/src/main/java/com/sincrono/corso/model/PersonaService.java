package com.sincrono.corso.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PersonaService extends JpaRepository<Persona, Integer> {
	
	@Query("SELECT a.emailPersona FROM Persona a where  a.emailPersona = :emailPersona")
	List<String> findByEmailPersona(@Param("emailPersona") String emailPersona);
	
	@Transactional
	@Modifying
	@Query("UPDATE Persona SET nomePersona=:nomePersona, cognomePersona=:cognomePersona, emailPersona=:emailPersona where idPersona=:idPersonadip")
	void updatePersona(@Param("idPersonadip") Integer idPersonadip,
			@Param("cognomePersona") String cognomePersona,
			@Param("nomePersona") String nomePersona, 
			@Param("emailPersona") String emailPersona);
}
