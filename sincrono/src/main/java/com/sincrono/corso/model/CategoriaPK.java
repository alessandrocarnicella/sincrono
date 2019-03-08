package com.sincrono.corso.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the categoria database table.
 * 
 */
@Embeddable
public class CategoriaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="nome_cat")
	private String nomeCat;

	@Column(name="ruolo_cat")
	private String ruoloCat;

	public CategoriaPK() {
	}
	public String getNomeCat() {
		return this.nomeCat;
	}
	public void setNomeCat(String nomeCat) {
		this.nomeCat = nomeCat;
	}
	public String getRuoloCat() {
		return this.ruoloCat;
	}
	public void setRuoloCat(String ruoloCat) {
		this.ruoloCat = ruoloCat;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CategoriaPK)) {
			return false;
		}
		CategoriaPK castOther = (CategoriaPK)other;
		return 
			this.nomeCat.equals(castOther.nomeCat)
			&& this.ruoloCat.equals(castOther.ruoloCat);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nomeCat.hashCode();
		hash = hash * prime + this.ruoloCat.hashCode();
		
		return hash;
	}
}