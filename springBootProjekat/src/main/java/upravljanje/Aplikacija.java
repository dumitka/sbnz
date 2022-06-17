package upravljanje;


import java.util.ArrayList;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import model.Admin;

@SpringBootApplication
public class Aplikacija {

	private static Logger log = LoggerFactory.getLogger(Aplikacija.class);

	private static ServisKorisnika servisKorisnika;
	private static ServisKnjiga servisKnjiga;
	private static ServisZanrova servisZanrova;
	
	@Autowired
	public Aplikacija(ServisKnjiga servisKnjiga, ServisZanrova sz, ServisKorisnika sk) {
		this.servisKnjiga = servisKnjiga;
		this.servisKorisnika =  sk;
		this.servisZanrova = sz;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = SpringApplication.run(Aplikacija.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);

		StringBuilder sb = new StringBuilder("Binovi u aplikaciji:\n");
		for (String beanName : beanNames) {
			sb.append(beanName + "\n");
		}
		log.info(sb.toString());
		
		generisanjePodataka();
	}
	

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("projekat", "droolsProjekat", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}
	

	@Bean
	public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
	    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
	    resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));
	    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
	    dataSourceInitializer.setDataSource(dataSource);
	    dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
	    return dataSourceInitializer;
	}
	
	public static void generisanjePodataka() {
		Admin admin = Admin.getInstance();
		
		admin.setSviKorisnici((ArrayList<Korisnik>) servisKorisnika.nadjiSve());
		for (Korisnik k : admin.getSviKorisnici()) System.out.println(k);
		System.out.println("---------------------------------------------");
		admin.setSviZanrovi((ArrayList<Zanr>) servisZanrova.nadjiSve());
		for (Zanr k : admin.getSviZanrovi()) System.out.println(k);
		System.out.println("---------------------------------------------");

		admin.setSveKnjige((ArrayList<Knjiga>) servisKnjiga.nadjiSve());
		for (Knjiga k : admin.getSveKnjige()) System.out.println(k);
		System.out.println("---------------------------------------------");

//		dodajZanrove(admin.getSviZanrovi());
//		dodajKnjige(admin);
//		dodajKorisnike(admin);
	}
//	
//	private static void dodajZanrove(ArrayList<Zanr> sviZanrovi) {
//		sviZanrovi.add(new Zanr(0, "tinejdz", new ArrayList<>()));
//		sviZanrovi.add(new Zanr(1, "decije", new ArrayList<>()));
//		sviZanrovi.add(new Zanr(2, "ljubavni", new ArrayList<>()));
//		sviZanrovi.add(new Zanr(3, "horor", new ArrayList<>()));
//		sviZanrovi.add(new Zanr(4, "istorija", new ArrayList<>()));
//		sviZanrovi.add(new Zanr(5, "klasici", new ArrayList<>()));
//		sviZanrovi.add(new Zanr(6, "misterija", new ArrayList<>()));
//	}
//	
//	private static void dodajKnjige(Admin admin) {
//		ArrayList<Knjiga> sveKnjige = admin.getSveKnjige();
//		ArrayList<Zanr> sviZanrovi = admin.getSviZanrovi();
//		
//		// knjiga 1
//		ArrayList<String> pisci1 = new ArrayList<>();
//		pisci1.add("Viktorija Ejvjard");
//		ArrayList<Zanr> zanrovi1 = new ArrayList<>();
//		zanrovi1.add(sviZanrovi.get(0));
//		Knjiga knjiga1 = new Knjiga("978-86-89565-22-5", "Crvena kraljica", pisci1, "Urban reads", zanrovi1, new ArrayList<>());
//		sveKnjige.add(knjiga1);
//		sviZanrovi.get(0).getKnjigeZanra().add(knjiga1);
//		
//		// knjiga 2
//		ArrayList<String> pisci2 = new ArrayList<>();
//		pisci2.add("Hauard F. Lavkraft");
//		ArrayList<Zanr> zanrovi2 = new ArrayList<>();
//		zanrovi2.add(sviZanrovi.get(3));
//		Knjiga knjiga2 = new Knjiga("978-86-6039-050-1", "Bezimeni grad", pisci2, "Orfelin", zanrovi2, new ArrayList<>());
//		sveKnjige.add(knjiga2);
//		sviZanrovi.get(3).getKnjigeZanra().add(knjiga2);
//		
//		// knjiga 3
//		ArrayList<String> pisci3 = new ArrayList<>(pisci1);
//		ArrayList<Zanr> zanrovi3 = new ArrayList<>(zanrovi1);
//		Knjiga knjiga3 = new Knjiga("978-86-89565-29-4", "Stakleni mac", pisci3, "Urban reads", zanrovi3, new ArrayList<>());
//		sveKnjige.add(knjiga3);
//		sviZanrovi.get(0).getKnjigeZanra().add(knjiga3);
//		
//		// knjiga 4
//		ArrayList<String> pisci4 = new ArrayList<>();
//		pisci4.add("Gijom Muso");
//		ArrayList<Zanr> zanrovi4 = new ArrayList<>();
//		zanrovi4.add(sviZanrovi.get(2));
//		zanrovi4.add(sviZanrovi.get(6));
//		Knjiga knjiga4 = new Knjiga("978-86-10-02026-7", "Stan u Parizu", pisci4, "Vulkan", zanrovi4, new ArrayList<>());
//		sveKnjige.add(knjiga4);
//		sviZanrovi.get(2).getKnjigeZanra().add(knjiga4);
//		sviZanrovi.get(6).getKnjigeZanra().add(knjiga4);
//
//		// knjiga 5
//		ArrayList<String> pisci5 = new ArrayList<>();
//		pisci5.add("Sofija Lundberj");
//		ArrayList<Zanr> zanrovi5 = new ArrayList<>();
//		zanrovi5.add(sviZanrovi.get(2));
//		zanrovi5.add(sviZanrovi.get(4));
//		Knjiga knjiga5 = new Knjiga("978-86-521-3244-7", "Crveni adresar", pisci5, "Laguna", zanrovi5, new ArrayList<>());
//		sveKnjige.add(knjiga5);
//		sviZanrovi.get(2).getKnjigeZanra().add(knjiga5);
//		sviZanrovi.get(4).getKnjigeZanra().add(knjiga5);
//		
//		// knjiga 6
//		ArrayList<String> pisci6 = new ArrayList<>();
//		pisci6.add("Loren Kejt");
//		ArrayList<Zanr> zanrovi6 = new ArrayList<>();
//		zanrovi6.add(sviZanrovi.get(0));
//		Knjiga knjiga6 = new Knjiga("978-86-505-1624-9", "Pad", pisci6, "Evro giunti", zanrovi6, new ArrayList<>());
//		sveKnjige.add(knjiga6);
//		sviZanrovi.get(0).getKnjigeZanra().add(knjiga6);
//	}
//	
//	private static void dodajKorisnike(Admin admin) {
//		ArrayList<Korisnik> sviKorisnici = admin.getSviKorisnici();
//		ArrayList<Zanr> sviZanrovi = admin.getSviZanrovi();
//		ArrayList<Knjiga> sveKnjige = admin.getSveKnjige();
//		
//		// korisnik 1
//		ArrayList<Knjiga> knjige1 = new ArrayList<>();
//		knjige1.add(sveKnjige.get(3));
//		knjige1.add(sveKnjige.get(4));
//		ArrayList<Zanr> zanrovi1 = new ArrayList<>();
//		zanrovi1.add(sviZanrovi.get(2));
//		zanrovi1.add(sviZanrovi.get(5));
//		Korisnik korisnik1 = new Korisnik("ana123", "svecaneBeleKosulje", "Anica", "Dobra", knjige1, zanrovi1);
//		sviKorisnici.add(korisnik1);
//		sveKnjige.get(3).getZainteresovaniKorisnici().add(korisnik1);
//
//		// korisnik 2
//		sviKorisnici.add(new Korisnik("gd'-'", "misery", "Green", "Day", new ArrayList<>(), new ArrayList<>()));
//		
//
//		// korisnik 3
//		ArrayList<Knjiga> knjige3 = new ArrayList<>();
//		knjige3.add(sveKnjige.get(0));
//		knjige3.add(sveKnjige.get(2));
//		knjige3.add(sveKnjige.get(3));
//		ArrayList<Zanr> zanrovi3 = new ArrayList<>(zanrovi1);
//		Korisnik korisnik3 = new Korisnik("nana123", "NanA", "Natasa", "Nedic", knjige3, zanrovi3);
//		sviKorisnici.add(korisnik3);
//		sveKnjige.get(3).getZainteresovaniKorisnici().add(korisnik3);
//	}

}
