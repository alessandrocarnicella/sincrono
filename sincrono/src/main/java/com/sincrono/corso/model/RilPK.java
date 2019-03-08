package com.sincrono.corso.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ril database table.
 * 
 */
@Embeddable
public class RilPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_personaril")
	private int idPersonaril;

	@Column(name="mese_ril")
	private String meseRil;

	@Column(name="anno_ril")
	private int annoRil;

	public RilPK() {
	}
	public int getIdPersonaril() {
		return this.idPersonaril;
	}
	public void setIdPersonaril(int idPersonaril) {
		this.idPersonaril = idPersonaril;
	}
	public String getMeseRil() {
		return this.meseRil;
	}
	public void setMeseRil(String meseRil) {
		this.meseRil = meseRil;
	}
	public int getAnnoRil() {
		return this.annoRil;
	}
	public void setAnnoRil(int annoRil) {
		this.annoRil = annoRil;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RilPK)) {
			return false;
		}
		RilPK castOther = (RilPK)other;
		return 
			(this.idPersonaril == castOther.idPersonaril)
			&& this.meseRil.equals(castOther.meseRil)
			&& (this.annoRil == castOther.annoRil);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPersonaril;
		hash = hash * prime + this.meseRil.hashCode();
		hash = hash * prime + this.annoRil;
		
		return hash;
	}
}