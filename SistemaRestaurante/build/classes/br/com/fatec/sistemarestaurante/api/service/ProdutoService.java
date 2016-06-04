package br.com.fatec.sistemarestaurante.api.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dto.ProdutoDTO;

public interface ProdutoService {
	
	ProdutoDTO salvar(ProdutoDTO produto);
	
	void atualizar(ProdutoDTO produto);
	
	void deletar(Long produtoId);
	
	List<ProdutoDTO> listar();
	
	ProdutoDTO buscarPorId(Long produtoId);

}
