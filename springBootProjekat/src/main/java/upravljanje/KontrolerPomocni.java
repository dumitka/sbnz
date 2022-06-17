package upravljanje;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KontrolerPomocni {
	
	private final ServisKnjiga servisKnjiga;
	private final ServisKorisnika servisKorisnika;
	private final ServisAdmin servisAdmin;

	@Autowired
	public KontrolerPomocni(ServisKnjiga servisKnjiga, ServisKorisnika servisKorisnika, ServisAdmin servisAdmin) {
		this.servisKnjiga = servisKnjiga;
		this.servisKorisnika = servisKorisnika;
		this.servisAdmin = servisAdmin;
	}

//	@RequestMapping(value = "/dodajP1", method = RequestMethod.GET, produces = "application/json")
//	public ArrayList<Korisnik>  praviloP1() {		
////		dodavanje knjige u sistem
//		Knjiga knjiga = new Knjiga();
//		knjiga.setISBN("1111");
//		knjiga.setNaziv("Naziv");
//		servisKnjiga.dodajKnjigu(knjiga);
//		
////		dodavanjee korisnika1 
//		ArrayList<Knjiga> knjige1 = new ArrayList<>();
//		knjige1.add(knjiga);
//		Korisnik korisnik = new Korisnik();
//		korisnik.setKorisnickoIme("korisnik1");
//		korisnik.setIme("Imenko");
//		korisnik.setLajkovaneKnjige(knjige1);
//
////		dodavanje korisnika 2
//		ArrayList<Knjiga> knjige2 = new ArrayList<>();
//		Korisnik korisnik2 = new Korisnik();
//		korisnik2.setKorisnickoIme("korisnik2");
//		korisnik2.setIme("Imenko");
//		korisnik2.setLajkovaneKnjige(knjige2);
//
//		ArrayList<Korisnik> dodati = new ArrayList<>();
//		dodati.add(servisKorisnika.dodajKorisnika(korisnik));
//		dodati.add(servisKorisnika.dodajKorisnika(korisnik2));
//		return dodati;
//	}
	

	@RequestMapping(value = "/dodajP1_1", method = RequestMethod.GET, produces = "application/json")
	public ArrayList<Korisnik>  praviloP1DrugiPut() {		
//		dodavanje svega
		this.servisAdmin.preporukaKnjiga();
		
		ArrayList<Korisnik> dodati = new ArrayList<>();
		return dodati;
	}
	

	@RequestMapping(value = "/preporuka", method = RequestMethod.GET, produces = "application/json")
	public HashMap<Knjiga, Double> pravilo1() {
		HashMap<Knjiga, Double> k = servisAdmin.preporukaKnjiga();
		return k;
	}
	
	@RequestMapping(value = "/pretraga", method = RequestMethod.GET, produces = "application/json")
	public String pretraga(@RequestParam ("pretraga") String zaPretragu) {
		System.out.println("Usla saaam !!!");
		ArrayList<Knjiga> knjige = this.servisAdmin.pretragaKnjiga(zaPretragu);
		return knjige.toString();
	}

	@RequestMapping(value = "/templejt", method = RequestMethod.GET, produces = "application/json")
	public String templejt(@RequestParam ("pretraga") String zaPretragu, @RequestParam ("brKnjiga") int brKnjiga) {
		String putanja = "D:\\sbnz\\sbnz\\droolsProjekat\\src\\main\\resources\\pravila\\";
		try {
			//int redniBroj = (new File(putanja)).list().length - 1;
			
			InputStream ulazniFajl = new FileInputStream(new File(putanja + "pretraga.drt"));
			DataProvider podaci = (DataProvider) new ArrayDataProvider(new String[][] {new String[] {zaPretragu, "" + brKnjiga}});
			DataProviderCompiler kompajler = new DataProviderCompiler();
			String drl = kompajler.compile(podaci, ulazniFajl);
			System.out.println("------------------------------------------------\n");
			System.out.println(drl);
			System.out.println("------------------------------------------------\n");
			ulazniFajl.close();
			
			//OutputStream izlazniFajl = new FileOutputStream(new File(putanja + "templejt" + redniBroj + ".drl"));
			OutputStream izlazniFajl = new FileOutputStream(new File(putanja + "templejt.drl"));
			izlazniFajl.write(drl.getBytes());
			izlazniFajl.close();
			
			ArrayList<Knjiga> knjige = this.servisAdmin.pretragaTemplejt();
			return knjige.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
