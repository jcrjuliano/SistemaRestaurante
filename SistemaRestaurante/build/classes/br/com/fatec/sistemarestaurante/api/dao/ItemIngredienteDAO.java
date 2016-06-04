package br.com.fatec.sistemarestaurante.api.dao;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.entity.ItemIngrediente;

public interface ItemIngredienteDAO {

	void save(ItemIngrediente itemIngredienteSalvar);


	List<ItemIngrediente> findAll();

	void update(ItemIngrediente itemIngredienteAtualizar);

	ItemIngrediente findByIds(Long id, Long id2);

	void delete(Long prodId, Long ingredId);


	List<ItemIngrediente> findByProduto(Long id);

}
