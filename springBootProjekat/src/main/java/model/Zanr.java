package model;

import java.util.ArrayList;

public class Zanr {
	
	private int id;
	private String naziv;
	private ArrayList<Knjiga> knjigeZanra;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public ArrayList<Knjiga> getKnjigeZanra() {
		return knjigeZanra;
	}
	public void setKnjigeZanra(ArrayList<Knjiga> knjigeZanra) {
		this.knjigeZanra = knjigeZanra;
	}
	
	public Zanr() {
		super();
	}
	
	public Zanr(int id, String naziv, ArrayList<Knjiga> knjigeZanra) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.knjigeZanra = knjigeZanra;
	}

}
