package upravljanje;

import java.util.ArrayList;
import java.util.HashMap;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Admin;
import model.Knjiga;
import model.Korisnik;
import model.Zanr;

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
		kieSession.setGlobal("korisnici", new ArrayList<>());
		kieSession.setGlobal("ucesca", new HashMap<>());
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
	
	public int preporukaKnjiga() {
		KieSession kieSession = pokreniSve(admin.getSveKnjige().get(3), admin.getSviKorisnici().get(0));
		kieSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		kieSession.fireAllRules();
		int broj = ((ArrayList<Korisnik>) kieSession.getGlobal("korisnici")).size();
		kieSession.dispose();
		return broj;
	}
	
	public ArrayList<Knjiga> pretragaKnjiga(String zaPretragu) {
		KieSession kieSession = pokreniPretragu(zaPretragu);
		kieSession.getAgenda().getAgendaGroup("pretraga").setFocus();
		kieSession.fireAllRules();
		ArrayList<Knjiga> knjige = (ArrayList<Knjiga>) kieSession.getGlobal("pretragaKnjiga");
		kieSession.dispose();
		return knjige;
	}

}
