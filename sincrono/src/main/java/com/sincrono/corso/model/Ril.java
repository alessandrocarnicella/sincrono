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

	@EmbeddedId
	private RilPK id;

	@Column(name="ore_cliente")
	private double oreCliente;

	@Column(name="ore_ferie")
	private double oreFerie;

	@Column(name="ore_permessi")
	private double orePermessi;

	@Column(name="ore_sede")
	private double oreSede;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_personaril", insertable=false, updatable=false)
	private Persona persona;

	public Ril() {
	}

	public RilPK getId() {
		return this.id;
	}

	public void setId(RilPK id) {
		this.id = id;
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