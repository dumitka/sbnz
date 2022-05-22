package upravljanje;

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

	public void pokreniSve() {
		System.out.println("..........................................................");
		KieSession kieSession = kieContainer.newKieSession();
		for (Zanr z : admin.getSviZanrovi()) kieSession.insert(z);
		for (Knjiga k : admin.getSveKnjige()) kieSession.insert(k);
		for (Korisnik k : admin.getSviKorisnici()) kieSession.insert(k);
//		kieSession.insert(admin.getSviZanrovi());
//		kieSession.insert(admin.getSveKnjige());
//		kieSession.insert(admin.getSviKorisnici());
		kieSession.fireAllRules();
		kieSession.dispose();
		System.out.println("..........................................................");
	}

}
