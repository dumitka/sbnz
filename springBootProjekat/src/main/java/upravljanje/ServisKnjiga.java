package upravljanje;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServisKnjiga {
	
	private RepozitorijumKnjiga repozitorijumKnjiga;
	private final KieContainer kieContainer;

	@Autowired
	public ServisKnjiga(KieContainer kieContainer, RepozitorijumKnjiga repozitorijumKnjiga) {
		this.kieContainer = kieContainer;
		this.repozitorijumKnjiga = repozitorijumKnjiga;
	}

	public List<Knjiga> nadjiSve() { return  this.repozitorijumKnjiga.findAll(); }
	
	public Knjiga sacuvajKnjigu(Knjiga k) { return this.repozitorijumKnjiga.save(k); }
	
	public Knjiga nadjiPoISBN(String isbn) { return this.repozitorijumKnjiga.findOneByIsbn(isbn); }
	

	public ArrayList<Knjiga> pretragaKnjiga(String zaPretragu) {
		KieSession kieSession = kieContainer.newKieSession();
		// globalne
		kieSession.setGlobal("pretragaKnjiga", new ArrayList<>());
		kieSession.setGlobal("zaPretragu", zaPretragu);
		// inserti
		for (Knjiga k : repozitorijumKnjiga.findAll()) kieSession.insert(k);
		kieSession.getAgenda().getAgendaGroup("pretraga").setFocus();
		kieSession.fireAllRules();
		ArrayList<Knjiga> knjige = (ArrayList<Knjiga>) kieSession.getGlobal("pretragaKnjiga");
		kieSession.dispose();
		return knjige;
	}
	
	public ArrayList<Knjiga> pretragaTemplejt() {
		KieSession kieSession = kieContainer.newKieSession();
		// globalne
		kieSession.setGlobal("pretragaKnjiga", new ArrayList<>());
		// inserti
		for (Knjiga k : repozitorijumKnjiga.findAll()) kieSession.insert(k);

		kieSession.getAgenda().getAgendaGroup("templejt").setFocus();
		kieSession.fireAllRules();
		ArrayList<Knjiga> knjige = (ArrayList<Knjiga>) kieSession.getGlobal("pretragaKnjiga");
		kieSession.dispose();
		return knjige;
	}
}
