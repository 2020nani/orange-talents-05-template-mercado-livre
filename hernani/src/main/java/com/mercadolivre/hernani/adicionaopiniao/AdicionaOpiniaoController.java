package com.mercadolivre.hernani.adicionaopiniao;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
public class AdicionaOpiniaoController {
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@Autowired
	private UsuarioRepository usuariorepository;
	
	@Autowired
	private AdicionaOpiniaoRepository opiniaorepository;
	
	@PostMapping(value="/produtos/{id}/opiniao")
	@Transactional
	public void criaOpiniao(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoForm opiniaoform, @AuthenticationPrincipal UserDetailsSecurity user) {
		
		Produto produto = produtorepository.findById(id).get();
		Usuario usuario = usuariorepository.findById(user.getId()).get();
		Opiniao opiniao = opiniaoform.converte(produto, usuario);
		opiniaorepository.save(opiniao);
		
	}
	

}
