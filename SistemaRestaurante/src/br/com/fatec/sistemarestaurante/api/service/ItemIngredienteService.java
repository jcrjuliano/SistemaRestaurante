package br.com.fatec.sistemarestaurante.api.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dto.ItemIngredienteDTO;

public interface ItemIngredienteService {
	
	ItemIngredienteDTO salvar(ItemIngredienteDTO itemIngrediente);
	
	void atualizar(ItemIngredienteDTO itemIngrediente);
	
	void deletar(Long itemIngredienteId);
	
	List<ItemIngredienteDTO> listar();
	
	ItemIngredienteDTO buscarPorId(Long itemIngredienteId);

}
