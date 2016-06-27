package br.com.fatec.sistemarestaurante.api.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dto.IngredienteDTO;

public interface IngredienteService {
	
	IngredienteDTO salvar(IngredienteDTO ingrediente);
	
	void atualizar(IngredienteDTO ingrediente);
	
	void deletar(Long ingredienteId);
	
	List<IngredienteDTO> listar();
	
	IngredienteDTO findById(Long id);

}
