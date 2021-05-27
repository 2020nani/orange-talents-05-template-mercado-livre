package com.mercadolivre.hernani.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import com.mercadolivre.hernani.cadastrousuario.Usuario;
import com.mercadolivre.hernani.cadastrousuario.UsuarioRepository;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuariorepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuariorepository.findByEmail(email);
		if (usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserDetailsSecurity(usuario.getId(), usuario.getEmail(), usuario.getSenha(), List.of());
	}
}
