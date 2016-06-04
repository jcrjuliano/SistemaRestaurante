package br.com.fatec.sistemarestaurante.api.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dto.ItemProdDTO;

public interface ItemProdService {
	
	ItemProdDTO salvar(ItemProdDTO itemProduto);
	
	void atualizar(ItemProdDTO itemProduto);
	
	void deletar(Long itemProdutoId);
	
	List<ItemProdDTO> listar();
	
	ItemProdDTO buscarPorId(Long itemProdutoId);

}

