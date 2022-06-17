package upravljanje;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServisUloga {
	
	@Autowired
	 private RepozitorijumUloga repozitorijumUloga;
	  
	 public Uloga findById(Integer id) { return this.repozitorijumUloga.getOne(id); }
	
	 public List<Uloga> findByName(String name) { return this.repozitorijumUloga.findByIme(name); }
}
