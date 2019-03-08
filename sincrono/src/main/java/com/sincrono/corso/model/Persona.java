package com.sincrono.corso.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_persona")
	private int idPersona;

	@Column(name="cognome_persona")
	private String cognomePersona;

	@Column(name="email_persona")
	private String emailPersona;

	@Column(name="nome_persona")
	private String nomePersona;

	//bi-directional many-to-one association to Commessa
	@OneToMany(mappedBy="persona")
	private List<Commessa> commessas;

	//bi-directional one-to-one association to Dipendente
	@OneToOne(mappedBy="persona")
	private Dipendente dipendente;

	//bi-directional one-to-one association to Referente
	@OneToOne(mappedBy="persona")
	private Referente referente;

	//bi-directional one-to-one association to Ril
	@OneToOne(mappedBy="persona")
	private Ril ril;

	public Persona() {
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getCognomePersona() {
		return this.cognomePersona;
	}

	public void setCognomePersona(String cognomePersona) {
		this.cognomePersona = cognomePersona;
	}

	public String getEmailPersona() {
		return this.emailPersona;
	}

	public void setEmailPersona(String emailPersona) {
		this.emailPersona = emailPersona;
	}

	public String getNomePersona() {
		return this.nomePersona;
	}

	public void setNomePersona(String nomePersona) {
		this.nomePersona = nomePersona;
	}

	public List<Commessa> getCommessas() {
		return this.commessas;
	}

	public void setCommessas(List<Commessa> commessas) {
		this.commessas = commessas;
	}

	public Commessa addCommessa(Commessa commessa) {
		getCommessas().add(commessa);
		commessa.setPersona(this);

		return commessa;
	}

	public Commessa removeCommessa(Commessa commessa) {
		getCommessas().remove(commessa);
		commessa.setPersona(null);

		return commessa;
	}

	public Dipendente getDipendente() {
		return this.dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public Referente getReferente() {
		return this.referente;
	}

	public void setReferente(Referente referente) {
		this.referente = referente;
	}

	public Ril getRil() {
		return this.ril;
	}

	public void setRil(Ril ril) {
		this.ril = ril;
	}

}