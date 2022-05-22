package upravljanje;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Korisnik;

@Service
public class ServisKorisnika {

	private final KieContainer kieContainer;

	@Autowired
	public ServisKorisnika(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public Korisnik dodajKorisnika(Korisnik k) {
		System.out.println("*********************************\n" + k + "\n*********************************");
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(k);
		kieSession.fireAllRules();
		kieSession.dispose();
		System.out.println("*********************************\n" + k + "\n*********************************");
		return k;
	}


}
