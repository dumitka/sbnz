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
@Table(name = "zanrovi")
public class Zanr {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "naziv", nullable = false, unique = true)
	private String naziv;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "zanrovi_knjiga", joinColumns = @JoinColumn(name = "zanr_id"), inverseJoinColumns = @JoinColumn(name = "knjiga_id"))
	private List<Knjiga> knjigeZanra;
	
}
