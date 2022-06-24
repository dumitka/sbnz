package upravljanje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private ServisUloga servisUloga;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(10);
	}

    @GetMapping(value = "/info")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DtoUlogovaniKorisnik> ulogovaniKorisnik() {
        Korisnik loggedUser = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DtoUlogovaniKorisnik dto = new DtoUlogovaniKorisnik(loggedUser.getKorisnickoIme(), loggedUser.getIme(), loggedUser.getPrezime(), "");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/izmena")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DtoUlogovaniKorisnik> izmeniKorisnika(@RequestBody DtoUlogovaniKorisnik izmenjenKorisnik) {
    	Korisnik korisnik = this.servisKorisnika.nadjiPoKorisnickomImenu(izmenjenKorisnik.getKorisnickoIme());
    	korisnik.setIme(izmenjenKorisnik.getIme());
    	korisnik.setPrezime(izmenjenKorisnik.getPrezime());
    	this.servisKorisnika.sacuvajKorisnika(korisnik);
        DtoUlogovaniKorisnik dto = new DtoUlogovaniKorisnik(korisnik.getKorisnickoIme(), korisnik.getIme(), korisnik.getPrezime(), "");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/dodaj")
	@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DtoUlogovaniKorisnik> dodajKorisnika(@RequestBody DtoUlogovaniKorisnik zaDodavanje) {
    	Korisnik korisnik = this.servisKorisnika.nadjiPoKorisnickomImenu(zaDodavanje.getKorisnickoIme());
    	if (korisnik != null)
    		return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    	Uloga uloga = this.servisUloga.nadjiJednuPoImenu("KORISNIK");
    	String sifra = encoder().encode(zaDodavanje.getLozinka());
    	korisnik = Korisnik.builder().ime(zaDodavanje.getIme()).prezime(zaDodavanje.getPrezime()).uloga(uloga).
    			korisnickoIme(zaDodavanje.getKorisnickoIme()).lozinka(sifra).build();
    	this.servisKorisnika.sacuvajKorisnika(korisnik);
        DtoUlogovaniKorisnik dto = new DtoUlogovaniKorisnik(korisnik.getKorisnickoIme(), korisnik.getIme(), korisnik.getPrezime(), "");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
	
}
