package br.com.fatec.sistemarestaurante.core.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dao.ItemProdDAO;
import br.com.fatec.sistemarestaurante.api.dto.ItemProdDTO;
import br.com.fatec.sistemarestaurante.api.entity.ItemProd;
import br.com.fatec.sistemarestaurante.api.service.ItemProdService;
import br.com.fatec.sistemarestaurante.core.converter.ItemProdDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class ItemProdServiceImpl implements ItemProdService {
	private ItemProdDAO itemProdDAO;
	private ItemProdDTOConverter itemProdDTOConverter;
	
	public ItemProdServiceImpl(){
		itemProdDAO = ImplFinder.getImpl(ItemProdDAO.class);
		itemProdDTOConverter = ImplFinder.getFinalImpl(ItemProdDTOConverter.class);
	}
	@Override
	public ItemProdDTO salvar(ItemProdDTO itemProdDTO) {
		ItemProd itemProdEntidade = this.itemProdDTOConverter.toEntity(itemProdDTO);
		Long id = itemProdDAO.save(itemProdEntidade);
		itemProdDTO.setId(id);
		return itemProdDTO;
	}

	@Override
	public void atualizar(ItemProdDTO itemProd) {
		ItemProd itemProdEntidade = this.itemProdDTOConverter.toEntity(itemProd);
		this.itemProdDAO.update(itemProdEntidade);

	}

	@Override
	public List<ItemProdDTO> listar() {
		return this.itemProdDTOConverter.toDTO(itemProdDAO.findAll());
	}

	@Override
	public void deletar(Long itemProdId) {
		this.itemProdDAO.delete(itemProdId);
	}
	
	@Override
	public ItemProdDTO buscarPorId(Long itemProdId) {
		return itemProdDTOConverter.toDTO(this.itemProdDAO.findById(itemProdId));
	}	

}
