package br.com.fatec.sistemarestaurante.core.converter;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dto.ComandaDTO;
import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.spektro.minispring.dto.DTOConverter;

import com.google.common.collect.Lists;

public class ComandaDTOConverter implements DTOConverter<Comanda, ComandaDTO>{

	@Override
	public ComandaDTO toDTO(Comanda entity) {
		ComandaDTO dto = null;
		if (entity != null){
			dto = new ComandaDTO();
			dto.setDataAbertura(entity.getDataAbertura());
			dto.setDataFechamento(entity.getDataFechamento());
			dto.setId(entity.getId());
			dto.setValorTotal(entity.getValorTotal());
		}
		return dto;
	}

	@Override
	public List<ComandaDTO> toDTO(List<Comanda> entidades) {
		List<ComandaDTO> dtos = Lists.newArrayList();
		
		for(Comanda entidade: entidades){
			dtos.add(this.toDTO(entidade));
		}
		
		return dtos;
	}

	@Override
	public Comanda toEntity(ComandaDTO dto) {
		Comanda entity = new Comanda();
		entity.setDataAbertura(dto.getDataAbertura());
		entity.setDataFechamento(dto.getDataFechamento());
		entity.setId(dto.getId());
		entity.setValorTotal(dto.getValorTotal());
		return entity;
	}

	@Override
	public List<Comanda> toEntity(List<ComandaDTO> dtos) {
		List<Comanda> entidades = Lists.newArrayList();
		for(ComandaDTO dto: dtos){
			entidades.add(this.toEntity(dto));
		}
		return entidades;
	}

}
