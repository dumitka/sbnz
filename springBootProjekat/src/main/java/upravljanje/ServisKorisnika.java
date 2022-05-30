package upravljanje;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Korisnik;

@Service
public class ServisKorisnika {

	private final KieContainer kieContainer;
	private final KieSession kieSession;

	@Autowired
	public ServisKorisnika(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
		this.kieSession = kieContainer.newKieSession();
	}

	public Korisnik dodajKorisnika(Korisnik k) {
		System.out.println("*********************************\n" + k + "\n*********************************");
		
		this.kieSession.insert(k);
		this.kieSession.fireAllRules();
		//kieSession.dispose();
		System.out.println("*********************************\n" + k + "\n*********************************");
		return k;
	}


}
