package com.sincrono.corso.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the referente database table.
 * 
 */
@Entity
@NamedQuery(name="Referente.findAll", query="SELECT r FROM Referente r")
public class Referente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ref")
	private int idRef;

	@Column(name="telefono_ref")
	private String telefonoRef;

	//bi-directional many-to-one association to Azienda
	@ManyToOne
	@JoinColumn(name="nome_azienda")
	private Azienda azienda;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="id_ref")
	private Persona persona;

	public Referente() {
	}

	public int getIdRef() {
		return this.idRef;
	}

	public void setIdRef(int idRef) {
		this.idRef = idRef;
	}

	public String getTelefonoRef() {
		return this.telefonoRef;
	}

	public void setTelefonoRef(String telefonoRef) {
		this.telefonoRef = telefonoRef;
	}

	public Azienda getAzienda() {
		return this.azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}