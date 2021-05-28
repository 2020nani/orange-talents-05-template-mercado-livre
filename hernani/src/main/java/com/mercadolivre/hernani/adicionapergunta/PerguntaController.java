package com.mercadolivre.hernani.adicionapergunta;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.mercadolivre.hernani.cadastroProduto.Produto;
import com.mercadolivre.hernani.cadastroProduto.ProdutoRepository;
import com.mercadolivre.hernani.cadastrousuario.Usuario;
import com.mercadolivre.hernani.cadastrousuario.UsuarioRepository;
import com.mercadolivre.hernani.config.security.UserDetailsSecurity;

@RestController
public class PerguntaController {
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@Autowired
	private UsuarioRepository usuariorepository;

	@Autowired
	private PerguntaRepository perguntarepository;
	
	@Autowired
	private EnviaEmail enviaemail;

	@PostMapping(value="/produtos/{id}/pergunta")
	@Transactional
	public String criaOpiniao(@PathVariable("id") Long id, @RequestBody @Valid PerguntaForm perguntaform, @AuthenticationPrincipal UserDetailsSecurity user) {
		
		Produto produto = produtorepository.findById(id).get();
		Usuario usuario = usuariorepository.findById(user.getId()).get();
		Pergunta pergunta = perguntaform.converte(produto, usuario);
		perguntarepository.save(pergunta);
		enviaemail.PerguntaUsuario(pergunta);
		return pergunta.toString();
	}

}
