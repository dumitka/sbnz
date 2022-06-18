package upravljanje;

import java.util.ArrayList;
import java.util.Arrays;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import model.Admin;

@SpringBootApplication
public class Aplikacija {

	private static Logger log = LoggerFactory.getLogger(Aplikacija.class);

	private static ServisKorisnika servisKorisnika;
	private static ServisKnjiga servisKnjiga;
	private static ServisZanrova servisZanrova;
	
	@Autowired
	public Aplikacija(ServisKnjiga sknj, ServisZanrova sz, ServisKorisnika sk) {
		servisKnjiga = sknj;
		servisKorisnika =  sk;
		servisZanrova = sz;
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
	

//	@Bean
//	public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
//	    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//	    resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));
//	    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//	    dataSourceInitializer.setDataSource(dataSource);
//	    dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
//	    return dataSourceInitializer;
//	}
	
	public static void generisanjePodataka() {
		Admin admin = Admin.getInstance();
		
		admin.setSviKorisnici((ArrayList<Korisnik>) servisKorisnika.nadjiSve());
		admin.setSviZanrovi((ArrayList<Zanr>) servisZanrova.nadjiSve());
		admin.setSveKnjige((ArrayList<Knjiga>) servisKnjiga.nadjiSve());

	}
	
}
