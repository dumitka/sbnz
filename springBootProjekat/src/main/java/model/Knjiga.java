package model;

import java.util.ArrayList;

public class Knjiga {
	
	private String ISBN;
	private String naziv;
	private ArrayList<String> pisci;
	private String izdavackaKuca;
	private ArrayList<Zanr> zanrovi;
	private ArrayList<Korisnik> zainteresovaniKorisnici;
	
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public ArrayList<String> getPisci() {
		return pisci;
	}
	public void setPisci(ArrayList<String> pisci) {
		this.pisci = pisci;
	}
	
	public String getIzdavackaKuca() {
		return izdavackaKuca;
	}
	public void setIzdavackaKuca(String izdavackaKuca) {
		this.izdavackaKuca = izdavackaKuca;
	}
	
	public ArrayList<Zanr> getZanrovi() {
		return zanrovi;
	}
	public void setZanrovi(ArrayList<Zanr> zanrovi) {
		this.zanrovi = zanrovi;
	}
	
	public ArrayList<Korisnik> getZainteresovaniKorisnici() {
		return zainteresovaniKorisnici;
	}
	public void setZainteresovaniKorisnici(ArrayList<Korisnik> zainteresovaniKorisnici) {
		this.zainteresovaniKorisnici = zainteresovaniKorisnici;
	}
	
	public Knjiga() {
		super();
	}
	
	public Knjiga(String isbn, String naziv, ArrayList<String> pisci, String izdavackaKuca, ArrayList<Zanr> zanrovi,
			ArrayList<Korisnik> zainteresovaniKorisnici) {
		super();
		this.ISBN = isbn;
		this.naziv = naziv;
		this.pisci = pisci;
		this.izdavackaKuca = izdavackaKuca;
		this.zanrovi = zanrovi;
		this.zainteresovaniKorisnici = zainteresovaniKorisnici;
	}
	@Override
	public String toString() {
		return "Knjiga [ISBN=" + ISBN + ", naziv=" + naziv + ", pisci=" + pisci + ", izdavackaKuca=" + izdavackaKuca
				+ ", zainteresovaniKorisnici=" + zainteresovaniKorisnici + "]";
	}

	
}
