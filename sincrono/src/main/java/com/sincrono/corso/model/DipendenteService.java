package com.sincrono.corso.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

public interface DipendenteService extends JpaRepository<Dipendente, Integer> {

	@Query("SELECT p.idPersona FROM Dipendente d, Persona p where (p.idPersona = d.idPersonadip and d.passwordDip = :password and p.emailPersona = :email)")
	int existUser(@Param("password") String password, @Param("email") String email);

	@Query("SELECT p.idPersona FROM Dipendente d, Persona p where (p.idPersona = d.idPersonadip and p.emailPersona = :email)")
	int existUserByEmail(@Param("email") String email);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE Dipendente SET passwordDip=:passwordDip where idPersonadip=:idPersonadip")
	void updatePswDip(@Param("idPersonadip") Integer idPersonadip,
			@RequestParam("new_password") String passwordDip);
			
	
	@Transactional
	@Modifying
	@Query("UPDATE Dipendente SET statusDip=:statusDip, passwordDip=:passwordDip, tariffaOraria=:tariffaOraria, nome_cat=:nome_cat, ruolo_cat=:ruolo_cat where idPersonadip=:idPersonadip")
	void updateDipendente(@Param("idPersonadip") Integer idPersonadip,
			@RequestParam("passwordDip") String passwordDip, 
			@RequestParam("nome_cat") String nome_cat,
			@RequestParam("ruolo_cat") String ruolo_cat ,
			@RequestParam("tariffaOraria") double tariffaOraria,
			@RequestParam("statusDip") byte statusDip);
}
