package upravljanje;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servis {
	@Autowired
	private Repo deskRepository;

	public java.util.List<Pomocna> findAll() {
		return deskRepository.findAll();
	}
}
