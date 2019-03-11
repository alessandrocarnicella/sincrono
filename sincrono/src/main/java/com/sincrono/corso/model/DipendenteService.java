package com.sincrono.corso.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DipendenteService extends JpaRepository<Dipendente, Integer> {


	/*SELECT p.nomePersona, p.cognomePersona FROM Dipendente d, Persona p where (p.idPersona = d.idPersonadip and d.passwordDip = :password and p.emailPersona = :email)*/
	@Query("SELECT p.idPersona FROM Dipendente d, Persona p where (p.idPersona = d.idPersonadip and d.passwordDip = :password and p.emailPersona = :email and d.statusDip = 1)")
	int existUser(@Param("password") String password, @Param("email") String email);

	
}
