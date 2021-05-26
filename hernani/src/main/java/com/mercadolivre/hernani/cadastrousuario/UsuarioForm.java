package com.mercadolivre.hernani.cadastrousuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mercadolivre.hernani.compartilhado.UniqueValue;

public class UsuarioForm {

	@NotBlank
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	@Email(message = "Por favor digite um email valido")
	private String email;
	
	@NotBlank
	@Length(min = 6,message = "A senha deve ter no minimo 6 caracteres")
	private String senha;

	public UsuarioForm(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public Usuario converte(BCryptPasswordEncoder pe) {
		
		return new Usuario(email, pe.encode(senha));
	}
	
	
}
