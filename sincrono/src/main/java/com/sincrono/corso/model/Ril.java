package com.sincrono.corso.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ril database table.
 * 
 */
@Entity
@NamedQuery(name="Ril.findAll", query="SELECT r FROM Ril r")
public class Ril implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_personaril")
	private int idPersonaril;

	@Column(name="anno_ril")
	private int annoRil;

	@Column(name="mese_ril")
	private String meseRil;

	@Column(name="ore_cliente")
	private double oreCliente;

	@Column(name="ore_ferie")
	private double oreFerie;

	@Column(name="ore_permessi")
	private double orePermessi;

	@Column(name="ore_sede")
	private double oreSede;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="id_personaril")
	private Persona persona;

	public Ril() {
	}

	public int getIdPersonaril() {
		return this.idPersonaril;
	}

	public void setIdPersonaril(int idPersonaril) {
		this.idPersonaril = idPersonaril;
	}

	public int getAnnoRil() {
		return this.annoRil;
	}

	public void setAnnoRil(int annoRil) {
		this.annoRil = annoRil;
	}

	public String getMeseRil() {
		return this.meseRil;
	}

	public void setMeseRil(String meseRil) {
		this.meseRil = meseRil;
	}

	public double getOreCliente() {
		return this.oreCliente;
	}

	public void setOreCliente(double oreCliente) {
		this.oreCliente = oreCliente;
	}

	public double getOreFerie() {
		return this.oreFerie;
	}

	public void setOreFerie(double oreFerie) {
		this.oreFerie = oreFerie;
	}

	public double getOrePermessi() {
		return this.orePermessi;
	}

	public void setOrePermessi(double orePermessi) {
		this.orePermessi = orePermessi;
	}

	public double getOreSede() {
		return this.oreSede;
	}

	public void setOreSede(double oreSede) {
		this.oreSede = oreSede;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}