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
@EqualsAndHashCode
@Entity
@Table(name = "zanrovi")
public class Zanr {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "naziv", nullable = false, unique = true)
	private String naziv;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "zanrovi_knjiga", joinColumns = @JoinColumn(name = "zanr_id"), inverseJoinColumns = @JoinColumn(name = "knjiga_id"))
	private List<Knjiga> knjigeZanra;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Knjiga k : getKnjigeZanra()) sb.append(k.getNaziv() + ", ");
		return "Zanr [naziv=" + naziv + ", knjigeZanra=[" + sb.toString() + "]]";
	}
	
}
