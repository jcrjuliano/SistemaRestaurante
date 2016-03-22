package br.com.fatec.sistemarestaurante.api.dao;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.entity.ItemProd;

public interface ItemProdDAO {

	Long save(ItemProd itemProdSalvar);

	ItemProd findById(Long id);

	void update(ItemProd itemProdAtualizar);

	void delete(Long id);

	List<ItemProd> findAll();

}
