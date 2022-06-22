package upravljanje;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "korisnici")
public class Korisnik implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	@JoinColumn(name = "uloga_id", nullable = false)
	private Uloga uloga;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "korisnici_knjige", joinColumns = @JoinColumn(name = "korisnik_id"), inverseJoinColumns = @JoinColumn(name = "knjiga_id"))
	private List<Knjiga> lajkovaneKnjige;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "lajkovani_zanrovi", joinColumns = @JoinColumn(name = "korisnik_id"), inverseJoinColumns = @JoinColumn(name = "zanr_id"))
	private List<Zanr> lajkovaniZanrovi;


	@Override
	public String toString() {
		StringBuilder sb1 =  new StringBuilder();
		for (Knjiga k : lajkovaneKnjige) sb1.append(k.getNaziv() + ", ");
		StringBuilder sb2 =  new StringBuilder();
		for (Zanr z : lajkovaniZanrovi) sb2.append(z.getNaziv() + ", ");
		return "Korisnik [korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", ime=" + ime
				+ ", prezime=" + prezime + ", uloga=" + uloga.getIme() + ", lajkovaneKnjige=[" + sb1.toString()
				+ "], lajkovaniZanrovi=[" + sb2.toString() + "]]";
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	ArrayList<Uloga> uloge = new ArrayList<>();
    	uloge.add(this.uloga);
        return uloge;
    }

    @Override
    public String getUsername() {
        return this.korisnickoIme;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

	@Override
	public String getPassword() {
		return this.lozinka;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
