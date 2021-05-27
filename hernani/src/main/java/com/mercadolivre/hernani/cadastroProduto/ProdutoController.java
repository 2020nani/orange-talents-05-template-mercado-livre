package com.mercadolivre.hernani.cadastroProduto;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.hernani.cadastrocategoria.CategoriaRepository;
import com.mercadolivre.hernani.cadastrousuario.Usuario;
import com.mercadolivre.hernani.cadastrousuario.UsuarioRepository;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@Autowired
	private CategoriaRepository categoriarepository;
	
	@Autowired
	private UsuarioRepository usuariorepository;
	
	@InitBinder
	public void init(WebDataBinder webDataBinder) {

		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}
	
	@GetMapping(value = "/produtos")
	public List<Produto> listaProduto() {
		List<Produto> lista = produtorepository.findAll();
		return lista;
	}
	
	@PostMapping(value="/produtos")
	@Transactional
	public void criaProduto(@RequestBody @Valid ProdutoForm produtoform) {
		//Simula usuario logado
		Usuario dono = usuariorepository.findByEmail("j2@hotmail.com");
		Produto produto = produtoform.converte(categoriarepository, dono);
		produtorepository.save(produto);
	
	}

}
