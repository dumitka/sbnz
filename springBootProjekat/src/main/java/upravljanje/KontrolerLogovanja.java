package upravljanje;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/logovanje")
public class KontrolerLogovanja {
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;
		
	@PostMapping(value = "/podaci", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTokena> createAuthenticationToken(@RequestBody DtoZahtev authenticationRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername().toLowerCase(), authenticationRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			Korisnik user = (Korisnik) authentication.getPrincipal();
			ArrayList<Uloga> uloge = new ArrayList<>();
			uloge.add(user.getUloga());
			String jwt = tokenUtils.generateToken(user.getUsername(), uloge);
			int expiresIn = tokenUtils.getExpiredIn();
			
			return ResponseEntity.ok(new DtoTokena(jwt, expiresIn, user.getId(), user.getUsername(), user.getUloga().getIme()));
		} catch (DisabledException de) {
			return new ResponseEntity<DtoTokena>(HttpStatus.FORBIDDEN);
		} catch (BadCredentialsException bdc) {
			System.out.println(bdc.getMessage());
			return new ResponseEntity<DtoTokena>(HttpStatus.NOT_FOUND);
		}
	}
	
}
