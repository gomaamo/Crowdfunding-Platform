package de.unidue.inf.is.adapters;

public class Konto {

	private String inhaber;
	private double guthaben;
	private String geheimzahl;
	
	
	public Konto(String inhaber, double guthaben, String geheimzahl) {
		super();
		this.inhaber = inhaber;
		this.guthaben = guthaben;
		this.geheimzahl = geheimzahl;
	}
	public String getGeheimzahl() {
		return geheimzahl;
	}
	public void setGeheimzahl(String geheimzahl) {
		this.geheimzahl = geheimzahl;
	}
	public double getGuthaben() {
		return guthaben;
	}
	public void setGuthaben(double guthaben) {
		this.guthaben = guthaben;
	}
	public String getInhaber() {
		return inhaber;
	}
	public void setInhaber(String inhaber) {
		this.inhaber = inhaber;
	}
	
	
	
}
