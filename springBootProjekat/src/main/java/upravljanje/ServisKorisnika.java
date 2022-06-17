package upravljanje;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServisKorisnika {

	@Autowired
	private RepozitorijumKorisnika repozitorijumKorisnika;

	public List<Korisnik> nadjiSve() { return this.repozitorijumKorisnika.findAll(); }
	
	public Korisnik sacuvajKnjigu(Korisnik k) { return this.repozitorijumKorisnika.save(k); }
	
	public Korisnik nadjiPoKorisnickomImenu(String korIme) { return this.repozitorijumKorisnika.findOneByKorisnickoIme(korIme); }
	
}
