package upravljanje;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServisZanrova {

	@Autowired
	private RepozitorijumZanrova repozitorijumZanrova;

	public List<Zanr> nadjiSve() { return this.repozitorijumZanrova.findAll(); }
	
	public Zanr nadjiPoNazivu(String naziv) { return this.repozitorijumZanrova.findOneByNaziv(naziv); }
	
}
