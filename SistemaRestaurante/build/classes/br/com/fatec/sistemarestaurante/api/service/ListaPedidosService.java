package br.com.fatec.sistemarestaurante.api.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dto.ListaPedidosDTO;

public interface ListaPedidosService {
	
	ListaPedidosDTO salvar(ListaPedidosDTO listaPedidosProduto);
	
	void atualizar(ListaPedidosDTO listaPedidosProduto);
	
	void deletar(Long listaPedidosProdutoId);
	
	List<ListaPedidosDTO> listar();
	
	ListaPedidosDTO buscarPorId(Long listaPedidosProdutoId);

}
