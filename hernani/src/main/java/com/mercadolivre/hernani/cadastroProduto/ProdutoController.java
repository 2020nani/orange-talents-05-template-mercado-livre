package com.mercadolivre.hernani.cadastroProduto;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mercadolivre.hernani.adicionaopiniao.AdicionaOpiniaoRepository;
import com.mercadolivre.hernani.adicionaopiniao.Opiniao;
import com.mercadolivre.hernani.cadastrocategoria.CategoriaRepository;
import com.mercadolivre.hernani.cadastrousuario.Usuario;
import com.mercadolivre.hernani.cadastrousuario.UsuarioRepository;
import com.mercadolivre.hernani.config.security.UserDetailsSecurity;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@Autowired
	private ImagemRepository imagemrepository;
	
	@Autowired
	private CategoriaRepository categoriarepository;
	
	@Autowired
	private AdicionaOpiniaoRepository opiniaorepository;
	
	@Autowired
	private UsuarioRepository usuariorepository;

	@Autowired
	private Uploader uploaderFake;
	
	@InitBinder(value="produtoForm")
	public void init(WebDataBinder webDataBinder) {

		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}
	
	@GetMapping(value = "/produtos")
	public List<Produto> listaProduto() {
		
		List<Produto> lista = produtorepository.findAll();
		return lista;
	}
	
	@GetMapping(value = "/imagens")
	public List<ImagemProduto> listaImagens() {
		
		List<ImagemProduto> lista = imagemrepository.findAll();
		return lista;
	}
	
	@GetMapping(value = "/opiniao")
	public List<Opiniao> listaOpiniao() {
		
		List<Opiniao> lista = opiniaorepository.findAll();
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
	
	@PostMapping(value = "/produtos/{id}/imagens")
	@Transactional
	public String adicionaImagens(@PathVariable("id") Long id,@Valid NovasImagensForm novasimagens,@AuthenticationPrincipal UserDetailsSecurity user) {
			
		Produto produto = produtorepository.findById(id).get();
		
		if(!produto.pertenceAoUsuario(user)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links = uploaderFake.envia(novasimagens.getImagens());
		
		produto.associaImagens(links);
		
		produtorepository.save(produto);
		
		return produto.toString();
		
	}
}
