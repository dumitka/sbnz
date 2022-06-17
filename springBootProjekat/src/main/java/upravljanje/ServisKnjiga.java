package upravljanje;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServisKnjiga {
	
	@Autowired
	private RepozitorijumKnjiga repozitorijumKnjiga;

	public List<Knjiga> nadjiSve() { return  this.repozitorijumKnjiga.findAll(); }
	
	public Knjiga sacuvajKnjigu(Knjiga k) { return this.repozitorijumKnjiga.save(k); }
	
	public Knjiga nadjiPoISBN(String isbn) { return this.repozitorijumKnjiga.findOneByIsbn(isbn); }
	
}
