package upravljanje;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepozitorijumKnjiga extends JpaRepository<Knjiga, Integer> {
	
	public Knjiga findOneByIsbn(String isbn);
	
}
