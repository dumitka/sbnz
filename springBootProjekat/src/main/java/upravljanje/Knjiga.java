package upravljanje;

import java.util.List;
import javax.persistence.*;
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
	private Byte[] pisci;

	@Column(name = "izdavacka_kuca",  nullable = false)
	private String izdavackaKuca;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "zanrovi_knjiga", joinColumns = @JoinColumn(name = "knjiga_id"), inverseJoinColumns = @JoinColumn(name = "zanr_id"))
	private List<Zanr> zanrovi;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "korisnici_knjige", joinColumns = @JoinColumn(name = "knjiga_id"), inverseJoinColumns = @JoinColumn(name = "korisnik_id"))
	private List<Korisnik> zainteresovaniKorisnici;

	@Override
	public String toString() {
		return "Knjiga [ISBN=" + isbn + ", naziv=" + naziv + ", pisci=" + pisci + ", izdavackaKuca=" + izdavackaKuca
				+ ", zainteresovaniKorisnici=" + zainteresovaniKorisnici + "]";
	}
	
}
