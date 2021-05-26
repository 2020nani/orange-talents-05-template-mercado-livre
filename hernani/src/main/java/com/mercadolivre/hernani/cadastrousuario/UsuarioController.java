package com.mercadolivre.hernani.cadastrousuario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuariorepository;
	
	@PostMapping(value = "/usuarios")
	public void criaUsuario(@RequestBody @Valid UsuarioForm usuarioform) {
		Usuario usuario = usuarioform.converte(pe);
		usuariorepository.save(usuario);
		
	}

}
