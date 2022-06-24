package upravljanje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServisKnjiga {
	
	private RepozitorijumKnjiga repozitorijumKnjiga;
	private final KieContainer kieContainer;
	private ServisZanrova servisZanrova;
	private ServisKorisnika servisKorisnika;

	@Autowired
	public ServisKnjiga(KieContainer kieContainer, RepozitorijumKnjiga repozitorijumKnjiga, 
			ServisZanrova servisZanrova, ServisKorisnika servisKorisnika) {
		this.kieContainer = kieContainer;
		this.repozitorijumKnjiga = repozitorijumKnjiga;
		this.servisZanrova = servisZanrova;
		this.servisKorisnika = servisKorisnika;
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
	
	public ArrayList<Knjiga> preporukaKnjiga(String korisnickoIme, String isbn) {
		Korisnik ulogovaniKorisnik = this.servisKorisnika.nadjiPoKorisnickomImenu(korisnickoIme);
		Knjiga pomstranaKnjiga = this.nadjiPoISBN(isbn);
		
		KieSession kieSession = kieContainer.newKieSession();
		// globalne
		kieSession.setGlobal("posmatranaKnjiga", pomstranaKnjiga);
		kieSession.setGlobal("pretragaKnjiga", new ArrayList<>());
		kieSession.setGlobal("ocenjeneKnjige", new HashMap<>());
		kieSession.setGlobal("ulogovaniKorisnik", ulogovaniKorisnik);
		// inserti
		for (Zanr z : this.servisZanrova.nadjiSve()) kieSession.insert(z);
		for (Knjiga k : this.nadjiSve()) kieSession.insert(k);
		for (Korisnik k : this.servisKorisnika.nadjiSve()) if (k.getId() != 1) kieSession.insert(k);
		kieSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		kieSession.fireAllRules();
		HashMap<Knjiga, Double> mapa = (HashMap<Knjiga, Double>) kieSession.getGlobal("ocenjeneKnjige");
		kieSession.dispose();
		
		ArrayList<Knjiga> povratna = new ArrayList<>();
		List<Double> vrednosti = mapa.values().stream().sorted().collect(Collectors.toList());
		for (int i = vrednosti.size() - 1; i >= 0; i--) for (Knjiga k : mapa.keySet()) 
			if (vrednosti.get(i) == mapa.get(k) && !povratna.contains(k)) povratna.add(k);
		return povratna;
	}
}
