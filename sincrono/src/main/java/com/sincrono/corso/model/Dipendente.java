package com.sincrono.corso.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dipendente database table.
 * 
 */
@Entity
@NamedQuery(name="Dipendente.findAll", query="SELECT d FROM Dipendente d")
public class Dipendente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_personadip")
	private int idPersonadip;

	@Column(name="password_dip")
	private String passwordDip;

	@Column(name="status_dip")
	private byte statusDip;

	@Column(name="tariffa_oraria")
	private double tariffaOraria;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="nome_cat", referencedColumnName="nome_cat"),
		@JoinColumn(name="ruolo_cat", referencedColumnName="ruolo_cat")
		})
	private Categoria categoria;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="id_personadip")
	private Persona persona;

	public Dipendente() {
	}

	public int getIdPersonadip() {
		return this.idPersonadip;
	}

	public void setIdPersonadip(int idPersonadip) {
		this.idPersonadip = idPersonadip;
	}

	public String getPasswordDip() {
		return this.passwordDip;
	}

	public void setPasswordDip(String passwordDip) {
		this.passwordDip = passwordDip;
	}

	public byte getStatusDip() {
		return this.statusDip;
	}

	public void setStatusDip(byte statusDip) {
		this.statusDip = statusDip;
	}

	public double getTariffaOraria() {
		return this.tariffaOraria;
	}

	public void setTariffaOraria(double tariffaOraria) {
		this.tariffaOraria = tariffaOraria;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}