package upravljanje;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("knjige")
public class KontrolerKnjiga {

	@Autowired
	private ServisKnjiga servisKnjiga;

	@Autowired
	private ServisZanrova servisZanrova;

    @PostMapping(value = "/dodaj")
	@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DtoKnjiga> dodajKnjigua(@RequestBody DtoKnjiga zaDodavanje) {
    	Knjiga knjiga = this.servisKnjiga.nadjiPoISBN(zaDodavanje.getIsbn());
    	if (knjiga != null)
    		return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    	List<Zanr> zanrovi = new ArrayList<>();
    	for (String z : zaDodavanje.getZanrovi()) {
    		Zanr zanr = this.servisZanrova.nadjiPoNazivu(z);
    		if (zanr == null) zanr = this.servisZanrova.sacuvajZanr(Zanr.builder().naziv(z).build());
    		zanrovi.add(zanr);
    	}
    	StringBuilder pisci = new StringBuilder();
    	for (int i = 0; i < zaDodavanje.getPisci().size(); i++) {
    		pisci.append(zaDodavanje.getPisci().get(i));
    		if (i != zaDodavanje.getPisci().size() - 1) pisci.append(",");
    	}
    	knjiga = Knjiga.builder().naziv(zaDodavanje.getNaziv()).isbn(zaDodavanje.getIsbn()).izdavackaKuca(zaDodavanje.getIzdavackaKuca()).
    			zanrovi(zanrovi).pisci(pisci.toString().getBytes()).build();
    	this.servisKnjiga.sacuvajKnjigu(knjiga);
        return new ResponseEntity<>(zaDodavanje, HttpStatus.OK);
    }
    
    @GetMapping(value = "/pretraga/{zaPretragu}")
	@PreAuthorize("hasAuthority('KORISNIK')")
    public ResponseEntity<List<DtoKnjiga>> pretrazi(@PathVariable String zaPretragu) {
    	ArrayList<DtoKnjiga> povratna = new ArrayList<>();
    	ArrayList<Knjiga> knjige = this.servisKnjiga.pretragaKnjiga(zaPretragu);
    	for (Knjiga k : knjige) {
    		ArrayList<String> pisci = new ArrayList<>();
    		String pom = new String(k.getPisci());
    		pom = pom.replace("[", "").replace("]", "").replace("\"", "");
    		for (String s : (Arrays.asList((pom).split(",")))) pisci.add(s);
    		ArrayList<String> zanrovi = new ArrayList<>();
    		for (Zanr z : k.getZanrovi()) zanrovi.add(z.getNaziv());
    		DtoKnjiga pomocna = DtoKnjiga.builder().isbn(k.getIsbn()).izdavackaKuca(k.getIzdavackaKuca()).naziv(k.getNaziv()).pisci(pisci).zanrovi(zanrovi).build();
    		povratna.add(pomocna);
    	}
        return new ResponseEntity<>(povratna, HttpStatus.OK);
    }
    
    @GetMapping(value = "/stariTemplejt")
	@PreAuthorize("hasAuthority('KORISNIK')")
    public ResponseEntity<List<DtoKnjiga>> stariTemplejt() {
    	ArrayList<DtoKnjiga> povratna = new ArrayList<>();
    	ArrayList<Knjiga> knjige = this.servisKnjiga.pretragaTemplejt();
    	for (Knjiga k : knjige) {
    		ArrayList<String> pisci = new ArrayList<>();
    		String pom = new String(k.getPisci());
    		pom = pom.replace("[", "").replace("]", "").replace("\"", "");
    		for (String s : (Arrays.asList((pom).split(",")))) pisci.add(s);
    		ArrayList<String> zanrovi = new ArrayList<>();
    		for (Zanr z : k.getZanrovi()) zanrovi.add(z.getNaziv());
    		DtoKnjiga pomocna = DtoKnjiga.builder().isbn(k.getIsbn()).izdavackaKuca(k.getIzdavackaKuca()).naziv(k.getNaziv()).pisci(pisci).zanrovi(zanrovi).build();
    		povratna.add(pomocna);
    	}
        return new ResponseEntity<>(povratna, HttpStatus.OK);
    }
    
    @GetMapping(value = "/generisiTemplejt/{zaPretragu}/{zaPrikaz}")
	@PreAuthorize("hasAuthority('KORISNIK')")
    public ResponseEntity<List<DtoKnjiga>> generisiTemplejt(@PathVariable String zaPretragu, @PathVariable int zaPrikaz) {
    	String putanja = "D:\\sbnz\\sbnz\\droolsProjekat\\src\\main\\resources\\pravila\\";
		try {
			InputStream ulazniFajl = new FileInputStream(new File(putanja + "pretraga.drt"));
			DataProvider podaci = (DataProvider) new ArrayDataProvider(new String[][] {new String[] {zaPretragu, "" + zaPrikaz}});
			DataProviderCompiler kompajler = new DataProviderCompiler();
			String drl = kompajler.compile(podaci, ulazniFajl);
			System.out.println("------------------------------------------------\n");
			System.out.println(drl);
			System.out.println("------------------------------------------------\n");
			ulazniFajl.close();
			
			OutputStream izlazniFajl = new FileOutputStream(new File(putanja + "templejt.drl"));
			izlazniFajl.write(drl.getBytes());
			izlazniFajl.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		ArrayList<DtoKnjiga> povratna = new ArrayList<>();
    	ArrayList<Knjiga> knjige = this.servisKnjiga.pretragaKnjiga(zaPretragu);
    	int indexi = zaPrikaz <= knjige.size() ? zaPrikaz : knjige.size();
    	for (int i = 0; i < indexi; i++) {
    		Knjiga k = knjige.get(i);
    		ArrayList<String> pisci = new ArrayList<>();
    		String pom = new String(k.getPisci());
    		pom = pom.replace("[", "").replace("]", "").replace("\"", "");
    		for (String s : (Arrays.asList((pom).split(",")))) pisci.add(s);
    		ArrayList<String> zanrovi = new ArrayList<>();
    		for (Zanr z : k.getZanrovi()) zanrovi.add(z.getNaziv());
    		DtoKnjiga pomocna = DtoKnjiga.builder().isbn(k.getIsbn()).izdavackaKuca(k.getIzdavackaKuca()).naziv(k.getNaziv()).pisci(pisci).zanrovi(zanrovi).build();
    		povratna.add(pomocna);
    	}
        return new ResponseEntity<>(povratna, HttpStatus.OK);
    }
}
