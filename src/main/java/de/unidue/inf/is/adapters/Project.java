package de.unidue.inf.is.adapters;

import java.util.ArrayList;

public class Project {
	
	private int kennung;
	private String titel;
	private String beschreibung;
	private String status;
	private double fl;
	private Benutzer ersteller;
	private int vorganger;
	private Kategorie kategorie;
	private ArrayList<Spende> spende = new ArrayList<Spende>();
	private ArrayList<Kommentar> kommentar =new ArrayList<Kommentar>();
	public Project(int kennung, String titel, String beschreibung,  String status, double fl, Benutzer ersteller,
			int vorganger, Kategorie kategorie) {
		super();
		this.kennung = kennung;
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.status = status;
		this.fl = fl;
		this.ersteller = ersteller;
		this.vorganger = vorganger;
		this.kategorie = kategorie;
	}
	public int getKennung() {
		return kennung;
	}
	public void setKennung(int kennung) {
		this.kennung = kennung;
	}
	
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getFl() {
		return fl;
	}
	public void setFl(double fl) {
		this.fl = fl;
	}
	
	public int getVorganger() {
		return vorganger;
	}
	public void setVorganger(int vorganger) {
		this.vorganger = vorganger;
	}
	
	
	
	
	public Benutzer getErsteller() {
		return ersteller;
	}
	public void setErsteller(Benutzer ersteller) {
		this.ersteller = ersteller;
	}
	public Kategorie getKategorie() {
		return kategorie;
	}
	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}
	public ArrayList<Spende> getSpende() {
		return spende;
	}
	public void setSpende(ArrayList<Spende> spende) {
		this.spende = spende;
	}
	public void addSpende(Spende s) {
		spende.add(s);
	}
	
	public double totalDonations() {
		double sum=0;
		for(Spende s:spende) {
			sum+=s.getSpendenbetrag();
		}
		
		return sum;
	}
	public ArrayList<Kommentar> getKommentar() {
		return kommentar;
	}
	public void setKommentar(ArrayList<Kommentar> kommentar) {
		this.kommentar = kommentar;
	}
	public void addKommentar(Kommentar k) {
		kommentar.add(k);
	}
	
	public double getSpendenbetrag(String email) {
		double spendenbetrag=0;
		for(Spende s:spende) {
			if(s.getBenutzer().getEmail().equals(email)) {
				spendenbetrag+=s.getSpendenbetrag();
			}
		}
		return spendenbetrag;
		
	}
	
	public String getSichtbarkeit(String email) {
		for(Spende s:spende) {
			if(s.getBenutzer().getEmail().equals(email)) {
				return s.getSichtbarkeit();
			}
		}
		return null;
		
		
	}
	
	

}
