package upravljanje;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoUlogovaniKorisnik {

	private String korisnickoIme;
	private String ime;
	private String prezime;
	private String lozinka;

}
