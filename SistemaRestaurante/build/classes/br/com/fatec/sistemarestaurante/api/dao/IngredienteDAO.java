package br.com.fatec.sistemarestaurante.api.dao;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.entity.Ingrediente;

public interface IngredienteDAO {

	Long save(Ingrediente ingredienteSalvar);

	Ingrediente findById(Long id);

	void delete(Long id);

	List<Ingrediente> findAll();

	void update(Ingrediente ingredienteAtualizar);

}
