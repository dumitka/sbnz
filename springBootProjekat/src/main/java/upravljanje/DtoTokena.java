package upravljanje;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class DtoTokena {
	
	private String accessToken;
    private Long expiresIn;

    private Integer id;
    private String username;
    private String role;
    private Boolean firstLogin;
    
    public DtoTokena() {
        this.accessToken = null;
        this.expiresIn = null;
    }
    
    public DtoTokena(String accessToken, long expiresIn, Integer id, String username, String role) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
