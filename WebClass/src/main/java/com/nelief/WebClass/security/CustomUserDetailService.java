package com.nelief.WebClass.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nelief.WebClass.entity.Utente;
import com.nelief.WebClass.repositories.UtentiRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	private UtentiRepository utentiRepository;

	public CustomUserDetailService(UtentiRepository utentiRepository) {
		super();
		this.utentiRepository = utentiRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		
		Utente utente = utentiRepository.findByUsername(username);
		
		
		
		if(utente == null) {
			throw new UsernameNotFoundException("The user doesn't exist!");
		}
		
		return new CustomUserDetails(utente);
	}

}
