package de.unidue.inf.is.adapters;

import java.sql.Timestamp;

public class Kommentar {
	
	private int id;
	private String text;
	private Timestamp datum;
	private String sichtbarkeit;
	private Benutzer benutzer;
	public Kommentar(int id, String text, Timestamp datum, String sichtbarkeit,Benutzer benutzer) {
		super();
		this.id = id;
		this.text = text;
		this.datum = datum;
		this.sichtbarkeit = sichtbarkeit;
		this.benutzer=benutzer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getDatum() {
		return datum;
	}
	public void setDatum(Timestamp datum) {
		this.datum = datum;
	}
	public String getSichtbarkeit() {
		return sichtbarkeit;
	}
	public void setSichtbarkeit(String sichtbarkeit) {
		this.sichtbarkeit = sichtbarkeit;
	}
	public Benutzer getBenutzer() {
		return benutzer;
	}
	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}
	
	

}
