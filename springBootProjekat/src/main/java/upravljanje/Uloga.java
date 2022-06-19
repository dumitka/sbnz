package upravljanje;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "uloge")
public class Uloga implements GrantedAuthority { 

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "ime", unique = true, nullable = false)
    String ime;

    @JsonIgnore
	@Override
	public String getAuthority() {
		return this.ime;
	}
}