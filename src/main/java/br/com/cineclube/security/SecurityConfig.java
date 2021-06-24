package br.com.cineclube.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Um exemplo de uma simples autenticação de usuário
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// para autenticar um usuario precisamos de uma service
	// que vai responder se as credenciais do usuario sao validas
	@Autowired
	private UsuarioService userServ;
	
	// precisamos tambem dizer como sera realizado a autenticacao sobrescrevendo o metodo configure
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// nesse ponto a UsuarioService é chamada para consultar o database
		auth.userDetailsService(userServ);
	}
	
	/* setamos o bcrypt como metodo de criptografia para codificar o password */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	/* 
	 * - para "desligar" o filtro de autenticacao do spring security descomentar o metodo configure abaixo.
	 * este metodo libera o acesso para todos os recurso de acordo com o padrao passado na 
	 * url antMatchers("/**") (util na fase de desenvolvimento)
	 *  
	 * - para "ligar" a autenticacao basta comentar esse metodo.
	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//        .authorizeRequests()
//            .antMatchers("/**").permitAll();
//	}
	

}

