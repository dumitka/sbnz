package upravljanje;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DtoKnjiga {

	private String isbn;
	private String naziv;
	private String izdavackaKuca;
	private ArrayList<String> pisci;
	private ArrayList<String> zanrovi;

}
