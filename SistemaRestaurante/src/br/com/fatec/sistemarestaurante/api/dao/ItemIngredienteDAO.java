package br.com.fatec.sistemarestaurante.api.dao;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.entity.ItemIngrediente;

public interface ItemIngredienteDAO {

	Long save(ItemIngrediente itemIngredienteSalvar);

	ItemIngrediente findById(Long id);

	void delete(Long id);

	List<ItemIngrediente> findAll();

	void update(ItemIngrediente itemIngredienteAtualizar);

}
