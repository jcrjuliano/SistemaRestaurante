package br.com.fatec.sistemarestaurante.api.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dto.PedidoDTO;

public interface PedidoService {
	
	PedidoDTO salvar(PedidoDTO pedido);
	
	void atualizar(PedidoDTO pedido);
	
	void deletar(Long pedidoId);
	
	List<PedidoDTO> listar();
	
	PedidoDTO buscarPorId(Long pedidoId);

}
