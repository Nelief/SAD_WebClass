package com.nelief.WebClass.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;

	public WebSecurityConfiguration(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf()
		.and()
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/home").authenticated()
		.antMatchers("/listaDocenti","/formAddDocente","/saveDocente","/formUpdateDocente","/updateDocente"
					,"/listaClassi","/formAddClasse","/saveClasse","/formUpdateClasse"
					,"/listaCattedre","/formAddCattedra","/saveCattedra"
					,"/listaStudenti","/formAddStudente","/saveStudente","/formUpdateStudente"
					,"/infoGenitore","/saveGenitore")
		.hasAuthority("ADMIN")
		.antMatchers("/listaClassiDocente","/listaStudentiDocente"
					,"/listaCompitiClasse","/formAddCompito","/saveCompito")
		.hasAuthority("DOCENTE")
		.antMatchers("/anagraficaFiglio","/getListaCompiti")
		.hasAuthority("GENITORE")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logout_success").permitAll();
	}	
}
