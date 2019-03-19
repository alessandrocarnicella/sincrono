package com.sincrono.corso.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cespiti database table.
 * 
 */
@Entity
@NamedQuery(name="Cespiti.findAll", query="SELECT c FROM Cespiti c")
public class Cespiti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcespiti;

	@Column(name="anno_funzione")
	private int annoFunzione;

	private String categoria;

	private String descrizione;

	//bi-directional many-to-one association to Dipendente
	@ManyToOne
	@JoinColumn(name="id_dipendente_cespiti")
	private Dipendente dipendente;

	public Cespiti() {
	}

	public int getIdcespiti() {
		return this.idcespiti;
	}

	public void setIdcespiti(int idcespiti) {
		this.idcespiti = idcespiti;
	}

	public int getAnnoFunzione() {
		return this.annoFunzione;
	}

	public void setAnnoFunzione(int annoFunzione) {
		this.annoFunzione = annoFunzione;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Dipendente getDipendente() {
		return this.dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

}