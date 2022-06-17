package upravljanje;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepozitorijumZanrova extends JpaRepository<Zanr, Integer> {
	
	public Zanr findOneByNaziv(String naziv);
	
}
