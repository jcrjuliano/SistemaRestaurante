package br.com.fatec.sistemarestaurante.api.dao;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.entity.Garcom;

public interface GarcomDAO {

	Long save(Garcom garcomSalvar);

	Garcom findById(Long id);

	void update(Garcom garcomAtualizar);

	void delete(Long id);

	List<Garcom> findAll();

}
