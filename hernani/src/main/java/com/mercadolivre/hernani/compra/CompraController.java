package com.mercadolivre.hernani.compra;


import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.validation.BindException;

import com.mercadolivre.hernani.adicionapergunta.EnviaEmail;
import com.mercadolivre.hernani.cadastroProduto.Produto;
import com.mercadolivre.hernani.cadastroProduto.ProdutoRepository;
import com.mercadolivre.hernani.cadastrousuario.Usuario;
import com.mercadolivre.hernani.cadastrousuario.UsuarioRepository;
import com.mercadolivre.hernani.config.security.UserDetailsSecurity;

@RestController
public class CompraController {
	
	@Autowired
	private EnviaEmail email;
	
	@Autowired
	private CompraRepository comprarepository;

	@Autowired
	private ProdutoRepository produtorepository;
	
	@Autowired
	private UsuarioRepository usuariorepository;

	@PostMapping(value = "produto/{id}/compra")
	@Transactional
	public String iniciacompra(@PathVariable("id") Long id, @RequestBody @Valid CompraForm compraform,
			UriComponentsBuilder uriComponentsBuilder, @AuthenticationPrincipal UserDetailsSecurity user) throws BindException {

		int quantidadecompra = compraform.getQuantidadecompra();
		
		Produto produtoComprado = produtorepository.findById(id).get();
		
		Usuario usuarioRealizaCompra = usuariorepository.findById(user.getId()).get();

		boolean atualizaestoque = produtoComprado.atualizaEstoque(quantidadecompra);
		
		if(atualizaestoque) {
		Compra compra =	new Compra(produtoComprado, usuarioRealizaCompra, compraform.getGateway(), quantidadecompra);
		comprarepository.save(compra);
		email.CompraRealizada(compra);
		return compraform.getGateway().criaUrlRetorno(compra, uriComponentsBuilder);
		}
		
		BindException problemaComEstoque = new BindException(compraform, "compraForm");
		problemaComEstoque.reject(null,"Não foi possível realizar a compra por conta do estoque");

		throw problemaComEstoque;
	}

}
