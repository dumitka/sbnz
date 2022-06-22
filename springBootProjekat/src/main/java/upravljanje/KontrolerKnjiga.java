package upravljanje;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("knjige")
public class KontrolerKnjiga {

	@Autowired
	private ServisKnjiga servisKnjiga;

	@Autowired
	private ServisZanrova servisZanrova;

    @PostMapping(value = "/dodaj")
	@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DtoKnjiga> dodajKnjigua(@RequestBody DtoKnjiga zaDodavanje) {
    	Knjiga knjiga = this.servisKnjiga.nadjiPoISBN(zaDodavanje.getIsbn());
    	if (knjiga != null)
    		return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    	List<Zanr> zanrovi = new ArrayList<>();
    	for (String z : zaDodavanje.getZanrovi()) {
    		Zanr zanr = this.servisZanrova.nadjiPoNazivu(z);
    		if (zanr == null) zanr = this.servisZanrova.sacuvajZanr(Zanr.builder().naziv(z).build());
    		zanrovi.add(zanr);
    	}
    	StringBuilder pisci = new StringBuilder();
    	for (int i = 0; i < zaDodavanje.getPisci().size(); i++) {
    		pisci.append(zaDodavanje.getPisci().get(i));
    		if (i != zaDodavanje.getPisci().size() - 1) pisci.append(",");
    	}
    	knjiga = Knjiga.builder().naziv(zaDodavanje.getNaziv()).isbn(zaDodavanje.getIsbn()).izdavackaKuca(zaDodavanje.getIzdavackaKuca()).
    			zanrovi(zanrovi).pisci(pisci.toString().getBytes()).build();
    	this.servisKnjiga.sacuvajKnjigu(knjiga);
        return new ResponseEntity<>(zaDodavanje, HttpStatus.OK);
    }
}
