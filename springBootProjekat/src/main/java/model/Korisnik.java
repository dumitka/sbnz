package model;

import java.util.ArrayList;

public class Korisnik {
	
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private ArrayList<Knjiga> lajkovaneKnjige;
	private ArrayList<Zanr> lajkovaniZanrovi;
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public ArrayList<Knjiga> getLajkovaneKnjige() {
		return lajkovaneKnjige;
	}
	public void setLajkovaneKnjige(ArrayList<Knjiga> lajkovaneKnjige) {
		this.lajkovaneKnjige = lajkovaneKnjige;
	}
	
	public ArrayList<Zanr> getLajkovaniZanrovi() {
		return lajkovaniZanrovi;
	}
	public void setLajkovaniZanrovi(ArrayList<Zanr> lajkovaniZanrovi) {
		this.lajkovaniZanrovi = lajkovaniZanrovi;
	}
	
	public Korisnik() {
		super();
	}
	
	public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, ArrayList<Knjiga> lajkovaneKnjige,
			ArrayList<Zanr> lajkovaniZanrovi) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.lajkovaneKnjige = lajkovaneKnjige;
		this.lajkovaniZanrovi = lajkovaniZanrovi;
	}
	
	@Override
	public String toString() {
		return "Korisnik [korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", ime=" + ime + ", prezime="
				+ prezime + "]";
	}

}
