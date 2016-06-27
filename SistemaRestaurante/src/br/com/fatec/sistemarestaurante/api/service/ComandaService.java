package br.com.fatec.sistemarestaurante.api.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dto.ComandaDTO;

public interface ComandaService {
	
	ComandaDTO salvar(ComandaDTO comanda);
	
	ComandaDTO fecharComanda(ComandaDTO comanda);
	
	void deletar(Long comandaId);
	
	List<ComandaDTO> listar();
	
	ComandaDTO buscarPorId(Long comandaId);

}
