package model;

import java.util.ArrayList;

public class Admin {
	
	private static Admin instanca;
	private String korisnickoIme;
	private String lozinka;
	private ArrayList<Knjiga> sveKnjige;
	private ArrayList<Zanr> sviZanrovi;
	private ArrayList<Korisnik> sviKorisnici;
	
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
	
	public ArrayList<Knjiga> getSveKnjige() {
		return sveKnjige;
	}
	public void setSveKnjige(ArrayList<Knjiga> sveKnjige) {
		this.sveKnjige = sveKnjige;
	}
	
	public ArrayList<Zanr> getSviZanrovi() {
		return sviZanrovi;
	}
	public void setSviZanrovi(ArrayList<Zanr> sviZanrovi) {
		this.sviZanrovi = sviZanrovi;
	}
	
	public ArrayList<Korisnik> getSviKorisnici() {
		return sviKorisnici;
	}
	public void setSviKorisnici(ArrayList<Korisnik> sviKorisnici) {
		this.sviKorisnici = sviKorisnici;
	}
	
	public static Admin getInstance() {
		if (instanca == null) instanca = new Admin("dumit", "dumit", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		return instanca;
	}
	
	private Admin(String korisnickoIme, String lozinka, ArrayList<Knjiga> sveKnjige, ArrayList<Zanr> sviZanrovi,
			ArrayList<Korisnik> sviKorisnici) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.sveKnjige = sveKnjige;
		this.sviZanrovi = sviZanrovi;
		this.sviKorisnici = sviKorisnici;
	}
	
}
