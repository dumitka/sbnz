package upravljanje;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepozitorijumUloga extends JpaRepository<Uloga, Integer> {

	List<Uloga> findByIme(String ime);

	Uloga findOneByIme(String ime);
}
