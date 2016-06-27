package br.com.fatec.sistemarestaurante.core.converter;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dto.GarcomDTO;
import br.com.fatec.sistemarestaurante.api.entity.Garcom;
import br.com.spektro.minispring.dto.DTOConverter;

public class GarcomDTOConverter implements DTOConverter<Garcom, GarcomDTO> {

	@Override
	public GarcomDTO toDTO(Garcom entidade) {
		GarcomDTO dto = null;
		if (entidade != null){
			dto = new GarcomDTO();
			dto.setId(entidade.getId());
			dto.setNome(entidade.getNome());
			dto.setRegistro(entidade.getRegistro());
			dto.setSexo(entidade.getSexo());
			dto.setIdade(entidade.getIdade());
		}		
		return dto;
		
	}

	@Override
	public List<GarcomDTO> toDTO(List<Garcom> entidades) {
		List<GarcomDTO> dtos = Lists.newArrayList();
		for(Garcom entidade : entidades){
			dtos.add(this.toDTO(entidade));
		}
		return dtos;
	}

	@Override
	public Garcom toEntity(GarcomDTO dto) {
		Garcom garcom = new Garcom();
		garcom.setId(dto.getId());
		garcom.setIdade(dto.getIdade());
		garcom.setNome(dto.getNome());
		garcom.setRegistro(dto.getRegistro());
		garcom.setSexo(dto.getSexo());
		return garcom;
	}

	@Override
	public List<Garcom> toEntity(List<GarcomDTO> dtos) {
		List<Garcom> entidades = Lists.newArrayList();
		for(GarcomDTO dto: dtos){
			entidades.add(this.toEntity(dto));
		}
		return entidades;
	}
	
}
