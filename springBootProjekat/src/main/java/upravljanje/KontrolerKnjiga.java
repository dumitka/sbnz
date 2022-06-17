package upravljanje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KontrolerKnjiga {

	private final ServisKnjiga servisKnjiga;

	@Autowired
	public KontrolerKnjiga(ServisKnjiga servisKnjiga) {
		this.servisKnjiga = servisKnjiga;
	}

//	@RequestMapping(value = "/dodajKnjigu", method = RequestMethod.GET, produces = "application/json")
//	public Knjiga dodavanjeKnjige() {
//		Knjiga knjiga = new Knjiga();
//		knjiga.setISBN("1111");
//		knjiga.setNaziv("Naziv");
//
//		Knjiga k = servisKnjiga.dodajKnjigu(knjiga);
//		return k;
//	}
//
//	@RequestMapping(value = "/dodajKnjigu2", method = RequestMethod.GET, produces = "application/json")
//	public Knjiga dodavanjeKnjige2() {
//		Knjiga knjiga = new Knjiga();
//		knjiga.setISBN("22");
//		knjiga.setNaziv("Naziv");
//
//		Knjiga k = servisKnjiga.dodajKnjigu(knjiga);
//		return k;
//	}
}
