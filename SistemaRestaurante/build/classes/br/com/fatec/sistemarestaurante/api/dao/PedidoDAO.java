package br.com.fatec.sistemarestaurante.api.dao;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.entity.Pedido;

public interface PedidoDAO {

	Long save(Pedido pedidoSalvar);

	Pedido findById(Long id);

	void update(Pedido pedidoAtualizar);

	void delete(Long id);

	List<Pedido> findAll();

}
