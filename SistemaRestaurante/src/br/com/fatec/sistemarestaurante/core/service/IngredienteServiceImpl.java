package br.com.fatec.sistemarestaurante.core.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dao.IngredienteDAO;
import br.com.fatec.sistemarestaurante.api.dto.IngredienteDTO;
import br.com.fatec.sistemarestaurante.api.entity.Ingrediente;
import br.com.fatec.sistemarestaurante.api.service.IngredienteService;
import br.com.fatec.sistemarestaurante.core.converter.IngredienteDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class IngredienteServiceImpl implements IngredienteService {
	private IngredienteDAO ingredienteDAO;
	private IngredienteDTOConverter ingredienteDTOConverter;
	
	public IngredienteServiceImpl(){
		ingredienteDAO = ImplFinder.getImpl(IngredienteDAO.class);
		ingredienteDTOConverter = ImplFinder.getFinalImpl(IngredienteDTOConverter.class);
	}
	@Override
	public IngredienteDTO salvar(IngredienteDTO ingredienteDTO) {
		Ingrediente ingredienteEntidade = this.ingredienteDTOConverter.toEntity(ingredienteDTO);
		Long id = ingredienteDAO.save(ingredienteEntidade);
		ingredienteDTO.setId(id);
		return ingredienteDTO;
	}

	@Override
	public void atualizar(IngredienteDTO ingrediente) {
		Ingrediente ingredienteEntidade = this.ingredienteDTOConverter.toEntity(ingrediente);
		this.ingredienteDAO.update(ingredienteEntidade);

	}

	@Override
	public List<IngredienteDTO> listar() {
		return this.ingredienteDTOConverter.toDTO(ingredienteDAO.findAll());
	}

	@Override
	public IngredienteDTO findById(Long id) {
		return this.ingredienteDTOConverter.toDTO(this.ingredienteDAO.findById(id));
	}
	@Override
	public void deletar(Long ingredienteId) {
		this.ingredienteDAO.delete(ingredienteId);
		
	}

}
