package de.unidue.inf.is.adapters;

public class Spende {
	
	private double spendenbetrag;
	private String sichtbarkeit;
	private Benutzer benutzer;
	public Spende( double spendenbetrag, String sichtbarkeit, Benutzer benutzer) {
		super();
		this.spendenbetrag = spendenbetrag;
		this.sichtbarkeit = sichtbarkeit;
		this.benutzer=benutzer;
	}
	
	public double getSpendenbetrag() {
		return spendenbetrag;
	}
	public void setSpendenbetrag(double spendenbetrag) {
		this.spendenbetrag = spendenbetrag;
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
