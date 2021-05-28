package com.mercadolivre.hernani.cadastroProduto;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploaderFake implements Uploader {
	
	/*
	 * @param imagens
	 * retorna links para imagens que veio enviada pelo upload do usuario
	 * 
	 */

	public Set<String> envia(List<MultipartFile> imagens) {

		return imagens.stream()
				.map(imagem -> "http://s3.amazon/" + imagem.getOriginalFilename())
				.collect(Collectors.toSet());

	}

}
