package br.com.fatec.sistemarestaurante.core.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dao.GarcomDAO;
import br.com.fatec.sistemarestaurante.api.dto.GarcomDTO;
import br.com.fatec.sistemarestaurante.api.entity.Garcom;
import br.com.fatec.sistemarestaurante.api.service.GarcomService;
import br.com.fatec.sistemarestaurante.core.converter.GarcomDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class GarcomServiceImpl implements GarcomService {
	private GarcomDAO garcomDAO;
	private GarcomDTOConverter garcomDTOConverter;
	
	public GarcomServiceImpl(){
		garcomDAO = ImplFinder.getImpl(GarcomDAO.class);
		garcomDTOConverter = ImplFinder.getImpl(GarcomDTOConverter.class);
	}
	@Override
	public GarcomDTO salvar(GarcomDTO garcomDTO) {
		Garcom garcomEntidade = this.garcomDTOConverter.toEntity(garcomDTO);
		Long id = garcomDAO.save(garcomEntidade);
		garcomDTO.setId(id);
		return garcomDTO;
	}

	@Override
	public void atualizar(GarcomDTO garcom) {
		Garcom garcomEntidade = this.garcomDTOConverter.toEntity(garcom);
		this.garcomDAO.update(garcomEntidade);

	}

	@Override
	public List<GarcomDTO> listar() {
		return this.garcomDTOConverter.toDTO(garcomDAO.findAll());
	}

	@Override
	public GarcomDTO findById(Long id) {
		return this.garcomDTOConverter.toDTO(this.garcomDAO.findById(id));
	}
	@Override
	public void deletar(Long garcomId) {
		this.garcomDAO.delete(garcomId);
		
	}

}
