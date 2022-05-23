package upravljanje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Korisnik;

@RestController
public class KontrolerKorisnika {

	private final ServisKorisnika servisKorisnika;

	@Autowired
	public KontrolerKorisnika(ServisKorisnika servisKorisnika) {
		this.servisKorisnika = servisKorisnika;
	}

	@RequestMapping(value = "/dodajKorisnika", method = RequestMethod.GET, produces = "application/json")
	public Korisnik dodajKorisnika() {
		Korisnik korisnik = new Korisnik();
		korisnik.setKorisnickoIme("korisnik1");
		korisnik.setIme("Imenko");

		Korisnik k = servisKorisnika.dodajKorisnika(korisnik);
		return k;
	}
	
}
