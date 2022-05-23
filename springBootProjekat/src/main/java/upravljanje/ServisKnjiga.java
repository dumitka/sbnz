package upravljanje;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Knjiga;

@Service
public class ServisKnjiga {

	private final KieContainer kieContainer;

	@Autowired
	public ServisKnjiga(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public Knjiga dodajKnjigu(Knjiga k) {
		System.out.println("*********************************\n" + k + "\n*********************************");
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(k);
		kieSession.fireAllRules();
		kieSession.dispose();
		System.out.println("*********************************\n" + k + "\n*********************************");
		return k;
	}
	
	public Knjiga postaviKaoGlobalnu(Knjiga k) {
		KieSession kSession = kieContainer.newKieSession();
		kSession.setGlobal("posmatranaKnjiga", k);
		System.out.println("---------------\n" + k + "\n---------------");
		return k;
	}

}
