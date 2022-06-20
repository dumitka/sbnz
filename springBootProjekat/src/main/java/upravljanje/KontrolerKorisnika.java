package upravljanje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("korisnici")
public class KontrolerKorisnika {

	@Autowired
	private ServisKorisnika servisKorisnika;

    @GetMapping(value = "/info")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DtoUlogovaniKorisnik> ulogovaniKorisnik() {
        Korisnik loggedUser = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DtoUlogovaniKorisnik dto = new DtoUlogovaniKorisnik(loggedUser.getKorisnickoIme(), loggedUser.getIme(), loggedUser.getPrezime());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/izmena")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DtoUlogovaniKorisnik> izmeniKorisnika(@RequestBody DtoUlogovaniKorisnik izmenjenKorisnik) {
    	Korisnik korisnik = this.servisKorisnika.nadjiPoKorisnickomImenu(izmenjenKorisnik.getKorisnickoIme());
    	korisnik.setIme(izmenjenKorisnik.getIme());
    	korisnik.setPrezime(izmenjenKorisnik.getPrezime());
    	this.servisKorisnika.sacuvajKorisnika(korisnik);
        DtoUlogovaniKorisnik dto = new DtoUlogovaniKorisnik(korisnik.getKorisnickoIme(), korisnik.getIme(), korisnik.getPrezime());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

//	@RequestMapping(value = "/dodajKorisnika", method = RequestMethod.GET, produces = "application/json")
//	public Korisnik dodajKorisnika() {
//		Korisnik korisnik = new Korisnik();
//		korisnik.setKorisnickoIme("korisnik1");
//		korisnik.setIme("Imenko");
//
//		Korisnik k = servisKorisnika.dodajKorisnika(korisnik);
//		return k;
//	}
	
}
