package com.sincrono.corso.model;

public class Andamento {

	private String nomeCommessa;
	private double tariffaCliente;
	private Azienda azienda;
	private Dipendente dipendente;
	private double guadagno;
	private double tariffaOraria;
	private double oreCliente;
	private RilPK rilPk;
	
	public Andamento(String nomeCommessa, double tariffaCliente, Azienda azienda,
			Dipendente dipendente, double guadagno, double tariffaOraria, 
			double oreCliente, RilPK rilPk) {
		super();
		this.nomeCommessa = nomeCommessa;
		this.tariffaCliente = tariffaCliente;
		this.azienda = azienda;
		this.dipendente = dipendente;
		this.guadagno = guadagno;
		this.tariffaOraria = tariffaOraria;
		this.oreCliente = oreCliente;
		this.rilPk = rilPk;
	}
	
	
	
	
	public Andamento() {
		super();
	}




	public RilPK getRilPk() {
		return rilPk;
	}

	public void setRilPk(RilPK rilPk) {
		this.rilPk = rilPk;
	}

	public double getOreCliente() {
		return oreCliente;
	}

	public void setOreCliente(double oreCliente) {
		this.oreCliente = oreCliente;
	}

	public double getTariffaOraria() {
		return tariffaOraria;
	}

	public void setTariffaOraria(double tariffaOraria) {
		this.tariffaOraria = tariffaOraria;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public String getNomeCommessa() {
		return nomeCommessa;
	}
	public void setNomeCommessa(String nomeCommessa) {
		this.nomeCommessa = nomeCommessa;
	}
	public double getTariffaCliente() {
		return tariffaCliente;
	}
	public void setTariffaCliente(double tariffaCliente) {
		this.tariffaCliente = tariffaCliente;
	}
	public Azienda getAzienda() {
		return azienda;
	}
	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
	public double getGuadagno() {
		return guadagno;
	}
	public void setGuadagno(double guadagno) {
		this.guadagno = guadagno;
	}
	
	
	
}
