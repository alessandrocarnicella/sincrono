package com.sincrono.corso.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the azienda database table.
 * 
 */
@Entity
@NamedQuery(name="Azienda.findAll", query="SELECT a FROM Azienda a")
public class Azienda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nome_azienda")
	private String nomeAzienda;

	@Column(name="email_azienda")
	private String emailAzienda;

	@Column(name="indirizzo_azienda")
	private String indirizzoAzienda;

	@Column(name="numdip_azienda")
	private int numdipAzienda;

	@Column(name="piva_azienda")
	private String pivaAzienda;

	private String societa;

	@Column(name="status_azienda")
	private byte statusAzienda;

	@Column(name="telefono_azienda")
	private String telefonoAzienda;

	//bi-directional many-to-one association to Commessa
	@OneToMany(mappedBy="azienda")
	private List<Commessa> commessas;

	//bi-directional many-to-one association to Referente
	@OneToMany(mappedBy="azienda")
	private List<Referente> referentes;

	public Azienda() {
	}

	public String getNomeAzienda() {
		return this.nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}

	public String getEmailAzienda() {
		return this.emailAzienda;
	}

	public void setEmailAzienda(String emailAzienda) {
		this.emailAzienda = emailAzienda;
	}

	public String getIndirizzoAzienda() {
		return this.indirizzoAzienda;
	}

	public void setIndirizzoAzienda(String indirizzoAzienda) {
		this.indirizzoAzienda = indirizzoAzienda;
	}

	public int getNumdipAzienda() {
		return this.numdipAzienda;
	}

	public void setNumdipAzienda(int numdipAzienda) {
		this.numdipAzienda = numdipAzienda;
	}

	public String getPivaAzienda() {
		return this.pivaAzienda;
	}

	public void setPivaAzienda(String pivaAzienda) {
		this.pivaAzienda = pivaAzienda;
	}

	public String getSocieta() {
		return this.societa;
	}

	public void setSocieta(String societa) {
		this.societa = societa;
	}

	public byte getStatusAzienda() {
		return this.statusAzienda;
	}

	public void setStatusAzienda(byte statusAzienda) {
		this.statusAzienda = statusAzienda;
	}

	public String getTelefonoAzienda() {
		return this.telefonoAzienda;
	}

	public void setTelefonoAzienda(String telefonoAzienda) {
		this.telefonoAzienda = telefonoAzienda;
	}

	public List<Commessa> getCommessas() {
		return this.commessas;
	}

	public void setCommessas(List<Commessa> commessas) {
		this.commessas = commessas;
	}

	public Commessa addCommessa(Commessa commessa) {
		getCommessas().add(commessa);
		commessa.setAzienda(this);

		return commessa;
	}

	public Commessa removeCommessa(Commessa commessa) {
		getCommessas().remove(commessa);
		commessa.setAzienda(null);

		return commessa;
	}

	public List<Referente> getReferentes() {
		return this.referentes;
	}

	public void setReferentes(List<Referente> referentes) {
		this.referentes = referentes;
	}

	public Referente addReferente(Referente referente) {
		getReferentes().add(referente);
		referente.setAzienda(this);

		return referente;
	}

	public Referente removeReferente(Referente referente) {
		getReferentes().remove(referente);
		referente.setAzienda(null);

		return referente;
	}

}