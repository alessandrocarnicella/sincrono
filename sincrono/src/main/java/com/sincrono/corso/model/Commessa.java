package com.sincrono.corso.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the commessa database table.
 * 
 */
@Entity
@NamedQuery(name="Commessa.findAll", query="SELECT c FROM Commessa c")
public class Commessa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_commessa")
	private int idCommessa;

	@Column(name="nome_commessa")
	private String nomeCommessa;

	@Column(name="tariffa_cliente")
	private double tariffaCliente;

	//bi-directional many-to-one association to Azienda
	@ManyToOne
	@JoinColumn(name="nome_aziendacommessa")
	private Azienda azienda;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_personacommessa")
	private Persona persona;

	public Commessa() {
	}

	public int getIdCommessa() {
		return this.idCommessa;
	}

	public void setIdCommessa(int idCommessa) {
		this.idCommessa = idCommessa;
	}

	public String getNomeCommessa() {
		return this.nomeCommessa;
	}

	public void setNomeCommessa(String nomeCommessa) {
		this.nomeCommessa = nomeCommessa;
	}

	public double getTariffaCliente() {
		return this.tariffaCliente;
	}

	public void setTariffaCliente(double tariffaCliente) {
		this.tariffaCliente = tariffaCliente;
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