package br.com.fatec.sistemarestaurante.api.dao;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.entity.Comanda;

public interface ComandaDAO {

	Long save(Comanda comandaSalvar);

	Comanda findById(Long id);

	List<Comanda> findAll();

	void update(Comanda comandaAtualizar);

	void delete(Long id);

}
