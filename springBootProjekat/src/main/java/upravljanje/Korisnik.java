package upravljanje;

import java.util.List;
import javax.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "korisnici")
public class Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "korisnicko_ime", nullable = false, unique = true)
	private String korisnickoIme;
	
	@Column(name = "lozinka", nullable = false)
	private String lozinka;
	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;
	
	@ManyToOne
	@JoinColumn(name = "uloga_id")
	private Uloga uloga;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "korisnici_knjige", joinColumns = @JoinColumn(name = "korisnik_id"), inverseJoinColumns = @JoinColumn(name = "knjiga_id"))
	private List<Knjiga> lajkovaneKnjige;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "lajkovani_zanrovi", joinColumns = @JoinColumn(name = "korisnik_id"), inverseJoinColumns = @JoinColumn(name = "zanr_id"))
	private List<Zanr> lajkovaniZanrovi;

	@Override
	public String toString() {
		return "Korisnik [korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", ime=" + ime + ", prezime="
				+ prezime + "]";
	}

}
