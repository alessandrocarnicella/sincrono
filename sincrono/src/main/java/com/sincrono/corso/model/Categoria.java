package com.sincrono.corso.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CategoriaPK id;

	//bi-directional many-to-one association to Dipendente
	@OneToMany(mappedBy="categoria")
	private List<Dipendente> dipendentes;

	public Categoria() {
	}

	public CategoriaPK getId() {
		return this.id;
	}

	public void setId(CategoriaPK id) {
		this.id = id;
	}

	public List<Dipendente> getDipendentes() {
		return this.dipendentes;
	}

	public void setDipendentes(List<Dipendente> dipendentes) {
		this.dipendentes = dipendentes;
	}

	public Dipendente addDipendente(Dipendente dipendente) {
		getDipendentes().add(dipendente);
		dipendente.setCategoria(this);

		return dipendente;
	}

	public Dipendente removeDipendente(Dipendente dipendente) {
		getDipendentes().remove(dipendente);
		dipendente.setCategoria(null);

		return dipendente;
	}

}