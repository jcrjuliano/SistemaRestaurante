package br.com.fatec.sistemarestaurante.core.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dao.ItemIngredienteDAO;
import br.com.fatec.sistemarestaurante.api.dto.ItemIngredienteDTO;
import br.com.fatec.sistemarestaurante.api.entity.ItemIngrediente;
import br.com.fatec.sistemarestaurante.api.service.ItemIngredienteService;
import br.com.fatec.sistemarestaurante.core.converter.ItemIngredienteDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class ItemIngredienteServiceImpl implements ItemIngredienteService {
	private ItemIngredienteDAO itemIngredienteDAO;
	private ItemIngredienteDTOConverter itemIngredienteDTOConverter;
	
	public ItemIngredienteServiceImpl(){
		itemIngredienteDAO = ImplFinder.getImpl(ItemIngredienteDAO.class);
		itemIngredienteDTOConverter = ImplFinder.getFinalImpl(ItemIngredienteDTOConverter.class);
	}
	@Override
	public ItemIngredienteDTO salvar(ItemIngredienteDTO itemIngredienteDTO) {
		ItemIngrediente ItemIngredienteEntidade = this.itemIngredienteDTOConverter.toEntity(itemIngredienteDTO);
		//Long id = itemIngredienteDAO.save(itemIngredienteEntidade);
		//itemIngredienteDTO.setId(id);
		return itemIngredienteDTO;
	}

	@Override
	public void atualizar(ItemIngredienteDTO itemIngrediente) {
		ItemIngrediente itemIngredienteEntidade = this.itemIngredienteDTOConverter.toEntity(itemIngrediente);
		this.itemIngredienteDAO.update(itemIngredienteEntidade);

	}

	@Override
	public List<ItemIngredienteDTO> listar() {
		return this.itemIngredienteDTOConverter.toDTO(itemIngredienteDAO.findAll());
	}

	@Override
	public void deletar(Long itemIngredienteId) {
		//this.itemIngredienteDAO.delete(itemIngredienteId);
	}
	@Override
	public ItemIngredienteDTO buscarPorId(Long itemIngredienteId) {
		return null;
		//return itemIngredienteDTOConverter.toDTO(this.itemIngredienteDAO.findById(itemIngredienteId));
	}

}
