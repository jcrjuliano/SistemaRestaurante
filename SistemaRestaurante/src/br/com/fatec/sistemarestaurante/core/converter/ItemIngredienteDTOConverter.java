package br.com.fatec.sistemarestaurante.core.converter;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dto.ItemIngredienteDTO;
import br.com.fatec.sistemarestaurante.api.entity.ItemIngrediente;
import br.com.spektro.minispring.dto.DTOConverter;

public class ItemIngredienteDTOConverter implements DTOConverter<ItemIngrediente, ItemIngredienteDTO> {

	@Override
	public ItemIngredienteDTO toDTO(ItemIngrediente entidade) {
		ItemIngredienteDTO dto = null;
		if (entidade != null){
			dto = new ItemIngredienteDTO();
			dto.setProduto(entidade.getProduto());
			dto.setIngrediente(entidade.getIngrediente());
			dto.setQuantidade(entidade.getQuantidade());
		}		
		return dto;
		
	}

	@Override
	public List<ItemIngredienteDTO> toDTO(List<ItemIngrediente> entidades) {
		List<ItemIngredienteDTO> dtos = Lists.newArrayList();
		for(ItemIngrediente entidade : entidades){
			dtos.add(this.toDTO(entidade));
		}
		return dtos;
	}

	@Override
	public ItemIngrediente toEntity(ItemIngredienteDTO dto) {
		ItemIngrediente itemIngrediente = new ItemIngrediente();
		itemIngrediente.setProduto(dto.getProduto());
		itemIngrediente.setIngrediente(dto.getIngrediente());
		itemIngrediente.setQuantidade(dto.getQuantidade());
		return itemIngrediente;
	}

	@Override
	public List<ItemIngrediente> toEntity(List<ItemIngredienteDTO> dtos) {
		List<ItemIngrediente> entidades = Lists.newArrayList();
		for(ItemIngredienteDTO dto: dtos){
			entidades.add(this.toEntity(dto));
		}
		return entidades;
	}
	
}
