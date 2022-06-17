package upravljanje;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepozitorijumKorisnika extends JpaRepository<Korisnik, Integer> {
	
	public Korisnik findOneByKorisnickoIme(String korisnickoIme);
	
}
