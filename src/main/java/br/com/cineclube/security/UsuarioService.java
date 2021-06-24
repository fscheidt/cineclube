package br.com.cineclube.security;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {
	// Nossa service precisa implementar a interface UserDetailsService

	// carrega do database os dados do usuario de acordo com o username fornecido
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// por contrato precisamos implementar o metodo loadUserByUsername
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();		
		return new User("maria", passwordEncoder.encode("1q2w3e"), new ArrayList<>());
		
	}
}

