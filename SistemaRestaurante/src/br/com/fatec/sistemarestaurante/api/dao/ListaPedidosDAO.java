package br.com.fatec.sistemarestaurante.api.dao;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.entity.ListaPedidos;

public interface ListaPedidosDAO {

	Long save(ListaPedidos listaPedidosSalvar);

	void delete(Long id);

	List<ListaPedidos> findAll();

	void update(ListaPedidos listaPedidosAtualizar);

	ListaPedidos findById(Long id);

}
