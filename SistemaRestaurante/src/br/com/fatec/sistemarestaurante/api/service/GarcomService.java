package br.com.fatec.sistemarestaurante.api.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dto.GarcomDTO;

public interface GarcomService {
	
	GarcomDTO salvar(GarcomDTO garcom);
	
	void atualizar(GarcomDTO garcom);
	
	void deletar(GarcomDTO garcom);
	
	List<GarcomDTO> listar();
	
	GarcomDTO findById(Long id);

}
