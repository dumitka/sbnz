package upravljanje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServisUlogovaniKorisnik  implements UserDetailsService {

	@Autowired
	private ServisKorisnika userRepository;

	//funkcija koja na osnovu username-a iz baze vraca objekat User-a
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Korisnik user = userRepository.nadjiPoKorisnickomImenu(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("Nema korisnika sa korisnickim imenom '%s' :(", username));
		} else {
			return user;
		}
	}
}
