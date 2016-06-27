package br.com.fatec.sistemarestaurante.core.converter;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dto.IngredienteDTO;
import br.com.fatec.sistemarestaurante.api.entity.Ingrediente;
import br.com.spektro.minispring.dto.DTOConverter;

public class IngredienteDTOConverter implements DTOConverter<Ingrediente, IngredienteDTO> {

	@Override
	public IngredienteDTO toDTO(Ingrediente entidade) {
		IngredienteDTO dto = null;
		if (entidade != null){
			dto = new IngredienteDTO();
			dto.setId(entidade.getId());
			dto.setDescricao(entidade.getDescricao());
		}		
		return dto;
		
	}

	@Override
	public List<IngredienteDTO> toDTO(List<Ingrediente> entidades) {
		List<IngredienteDTO> dtos = Lists.newArrayList();
		for(Ingrediente entidade : entidades){
			dtos.add(this.toDTO(entidade));
		}
		return dtos;
	}

	@Override
	public Ingrediente toEntity(IngredienteDTO dto) {
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setId(dto.getId());
		ingrediente.setDescricao(dto.getDescricao());
		return ingrediente;
	}

	@Override
	public List<Ingrediente> toEntity(List<IngredienteDTO> dtos) {
		List<Ingrediente> entidades = Lists.newArrayList();
		for(IngredienteDTO dto: dtos){
			entidades.add(this.toEntity(dto));
		}
		return entidades;
	}
	
}
