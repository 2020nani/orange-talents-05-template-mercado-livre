package com.mercadolivre.hernani.cadastroProduto;


import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {

	/**
	 * 
	 * @param imagens
	 * @return links para imagens que foram enviadas no upload do usuario
	 */
	Set<String> envia(List<MultipartFile> imagens);

}
