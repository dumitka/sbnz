package upravljanje;

import lombok.*;

//dto za logovanje
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DtoZahtev {
	
	private String username;
    private String password;

}
