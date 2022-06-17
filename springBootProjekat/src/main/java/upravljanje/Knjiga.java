package upravljanje;

import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "knjige")
public class Knjiga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "isbn", nullable = false, unique = true)
	private String isbn;
	
	@Column(name = "naziv",  nullable = false)
	private String naziv;

	@Column(name = "pisci")
	private byte[] pisci;

	@Column(name = "izdavacka_kuca",  nullable = false)
	private String izdavackaKuca;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "zanrovi_knjiga", joinColumns = @JoinColumn(name = "knjiga_id"), inverseJoinColumns = @JoinColumn(name = "zanr_id"))
	private List<Zanr> zanrovi;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "korisnici_knjige", joinColumns = @JoinColumn(name = "knjiga_id"), inverseJoinColumns = @JoinColumn(name = "korisnik_id"))
	private List<Korisnik> zainteresovaniKorisnici;

	@Override
	public String toString() {
		StringBuilder sb1 =  new StringBuilder();
		for (Zanr k : getZanrovi()) sb1.append(k.getNaziv() + ", ");
		StringBuilder sb2 =  new StringBuilder();
		for (Korisnik k : getZainteresovaniKorisnici()) sb2.append(k.getKorisnickoIme() + ", ");
		String pis = new String(pisci);
		return "Knjiga [isbn=" + isbn + ", naziv=" + naziv + ", pisci=" + pis
				+ ", izdavackaKuca=" + izdavackaKuca + ", zanrovi=[" + sb1.toString() + "], zainteresovaniKorisnici=["
				+ sb2.toString() + "]]";
	}

}
