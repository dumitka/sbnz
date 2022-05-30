package upravljanje;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Knjiga;
import model.Korisnik;

@RestController
public class KontrolerPomocni {
	
	private final ServisKnjiga servisKnjiga;
	private final ServisKorisnika servisKorisnika;
	private final ServisAdmin servisAdmin;

	@Autowired
	public KontrolerPomocni(ServisKnjiga servisKnjiga, ServisKorisnika servisKorisnika, ServisAdmin servisAdmin) {
		this.servisKnjiga = servisKnjiga;
		this.servisKorisnika = servisKorisnika;
		this.servisAdmin = servisAdmin;
	}

	@RequestMapping(value = "/dodajP1", method = RequestMethod.GET, produces = "application/json")
	public ArrayList<Korisnik>  praviloP1() {		
//		dodavanje knjige u sistem
		Knjiga knjiga = new Knjiga();
		knjiga.setISBN("1111");
		knjiga.setNaziv("Naziv");
		servisKnjiga.dodajKnjigu(knjiga);
		
//		dodavanjee korisnika1 
		ArrayList<Knjiga> knjige1 = new ArrayList<>();
		knjige1.add(knjiga);
		Korisnik korisnik = new Korisnik();
		korisnik.setKorisnickoIme("korisnik1");
		korisnik.setIme("Imenko");
		korisnik.setLajkovaneKnjige(knjige1);

//		dodavanje korisnika 2
		ArrayList<Knjiga> knjige2 = new ArrayList<>();
		Korisnik korisnik2 = new Korisnik();
		korisnik2.setKorisnickoIme("korisnik2");
		korisnik2.setIme("Imenko");
		korisnik2.setLajkovaneKnjige(knjige2);

		ArrayList<Korisnik> dodati = new ArrayList<>();
		dodati.add(servisKorisnika.dodajKorisnika(korisnik));
		dodati.add(servisKorisnika.dodajKorisnika(korisnik2));
		return dodati;
	}
	

	@RequestMapping(value = "/dodajP1_1", method = RequestMethod.GET, produces = "application/json")
	public ArrayList<Korisnik>  praviloP1DrugiPut() {		
//		dodavanje svega
		this.servisAdmin.preporukaKnjiga();
		
		ArrayList<Korisnik> dodati = new ArrayList<>();
		return dodati;
	}
	

	@RequestMapping(value = "/p1", method = RequestMethod.GET, produces = "application/json")
	public int pravilo1() {
		int k = servisAdmin.preporukaKnjiga();
		return k;
	}
	
	@RequestMapping(value = "/pretraga", method = RequestMethod.GET, produces = "application/json")
	public String pretraga(@RequestParam ("pretraga") String zaPretragu) {
		System.out.println("Usla saaam !!!");
		ArrayList<Knjiga> knjige = this.servisAdmin.pretragaKnjiga(zaPretragu);
		return knjige.toString();
	}
}
