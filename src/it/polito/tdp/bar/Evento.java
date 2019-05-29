package it.polito.tdp.bar;

import java.time.LocalTime;

public class Evento implements Comparable<Evento>{
	
	public enum TipoEvento{
		ARRIVO_GRUPPO_CLIENTI, // il/i cliente/i arrivano
		LEAVING,
	}
	
	private  LocalTime oraArrivo; // ora di arrivo del/i cliente/i
	private TipoEvento tipo;
	private int numeroClienti;
	private float tolleranza;
	private int durat;
	
	public Evento(LocalTime oraArrivo, TipoEvento tipo, int numeroClienti, float tolleranza, int durat) {
		super();
		this.oraArrivo = oraArrivo;
		this.tipo = tipo;
		this.numeroClienti = numeroClienti;
		this.tolleranza = tolleranza;
		this.durat = durat;
	}

	public LocalTime getOraArrivo() {
		return oraArrivo;
	}

	public void setOraArrivo(LocalTime oraArrivo) {
		this.oraArrivo = oraArrivo;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}

	public int getNumeroClienti() {
		return numeroClienti;
	}

	public void setNumeroClienti(int numeroClienti) {
		this.numeroClienti = numeroClienti;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}

	public int getDurat() {
		return durat;
	}

	public void setDurat(int durat) {
		this.durat = durat;
	}


	@Override
	public String toString() {
		return "Evento [oraArrivo=" + oraArrivo + ", tipo=" + tipo + ", tolleranza=" + tolleranza + ", durat=" + durat
				+ "]";
	}

	@Override
	public int compareTo(Evento arg0) {
		return this.oraArrivo.compareTo(arg0.getOraArrivo());
	}


}
