package com.mercadolivre.hernani.cadastrousuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	@Column(unique = true)
	private String email;

	@NotBlank
	private String senha;

	@Future
	@CreationTimestamp
	private LocalDateTime momentoCriacao;

	public Usuario(@NotBlank @Email String email, @NotBlank String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

}
