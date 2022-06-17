package upravljanje;

import java.util.ArrayList;
import java.util.HashMap;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Admin;

@Service
public class ServisAdmin {

	private final KieContainer kieContainer;
	private static Admin admin;

	@Autowired
	public ServisAdmin(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
		admin = Admin.getInstance();
	}
	
	public KieSession pokreniSve(Knjiga pomstranaKnjiga, Korisnik ulogovaniKorisnik) {
		System.out.println("..........................................................");
		KieSession kieSession = kieContainer.newKieSession();
		//kieSession.getAgenda().getActivationGroup("Racunanje ucesca").clear();
		// globalne
		kieSession.setGlobal("posmatranaKnjiga", pomstranaKnjiga);
//		//kieSession.setGlobal("korisnici", new ArrayList<>());
//		//kieSession.setGlobal("ucesca", new HashMap<>());
		kieSession.setGlobal("ocenjeneKnjige", new HashMap<>());
		kieSession.setGlobal("ulogovaniKorisnik", ulogovaniKorisnik);
		// inserti
		for (Zanr z : admin.getSviZanrovi()) kieSession.insert(z);
		for (Knjiga k : admin.getSveKnjige()) kieSession.insert(k);
		for (Korisnik k : admin.getSviKorisnici()) kieSession.insert(k);
		System.out.println("..........................................................");
		return kieSession;
	}
	
	public KieSession pokreniPretragu(String staTrazis) {
		System.out.println("..........................................................");
		KieSession kieSession = kieContainer.newKieSession();
		// globalne
		kieSession.setGlobal("pretragaKnjiga", new ArrayList<>());
		kieSession.setGlobal("zaPretragu", staTrazis);
		// inserti
		for (Knjiga k : admin.getSveKnjige()) kieSession.insert(k);
		System.out.println("..........................................................");
		return kieSession;
	}
	
	public HashMap<Knjiga, Double> preporukaKnjiga() {
		KieSession kieSession = pokreniSve(admin.getSveKnjige().get(3), admin.getSviKorisnici().get(1));
		kieSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		kieSession.fireAllRules();
		HashMap<Knjiga, Double> mapa = (HashMap<Knjiga, Double>) kieSession.getGlobal("ocenjeneKnjige");
		kieSession.dispose();
		return mapa;
	}
	
	public ArrayList<Knjiga> pretragaKnjiga(String zaPretragu) {
		KieSession kieSession = pokreniPretragu(zaPretragu);
		kieSession.getAgenda().getAgendaGroup("pretraga").setFocus();
		kieSession.fireAllRules();
		ArrayList<Knjiga> knjige = (ArrayList<Knjiga>) kieSession.getGlobal("pretragaKnjiga");
		kieSession.dispose();
		return knjige;
	}

	public ArrayList<Knjiga> pretragaTemplejt() {
		KieSession kieSession = kieContainer.newKieSession();
		// globalne
		kieSession.setGlobal("pretragaKnjiga", new ArrayList<Knjiga>());
		// inserti
		for (Knjiga k : admin.getSveKnjige()) kieSession.insert(k);
		kieSession.getAgenda().getAgendaGroup("pretragaTemplejt").setFocus();
		kieSession.fireAllRules();
		ArrayList<Knjiga> knjige = (ArrayList<Knjiga>) kieSession.getGlobal("pretragaKnjiga");
		kieSession.dispose();
		return knjige;
	}
}
