package br.com.fatec.sistemarestaurante.core.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dao.ListaPedidosDAO;
import br.com.fatec.sistemarestaurante.api.dto.ListaPedidosDTO;
import br.com.fatec.sistemarestaurante.api.entity.ListaPedidos;
import br.com.fatec.sistemarestaurante.api.service.ListaPedidosService;
import br.com.fatec.sistemarestaurante.core.converter.ListaPedidosDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class ListaPedidosServiceImpl implements ListaPedidosService {
	private ListaPedidosDAO listaPedidosDAO;
	private ListaPedidosDTOConverter listaPedidosDTOConverter;
	
	public ListaPedidosServiceImpl(){
		listaPedidosDAO = ImplFinder.getImpl(ListaPedidosDAO.class);
		listaPedidosDTOConverter = ImplFinder.getFinalImpl(ListaPedidosDTOConverter.class);
	}
	@Override
	public ListaPedidosDTO salvar(ListaPedidosDTO listaPedidosDTO) {
		ListaPedidos listaPedidosEntidade = this.listaPedidosDTOConverter.toEntity(listaPedidosDTO);
		Long id = listaPedidosDAO.save(listaPedidosEntidade);
		listaPedidosDTO.setId(id);
		return listaPedidosDTO;
	}

	@Override
	public void atualizar(ListaPedidosDTO listaPedidos) {
		ListaPedidos listaPedidosEntidade = this.listaPedidosDTOConverter.toEntity(listaPedidos);
		this.listaPedidosDAO.update(listaPedidosEntidade);

	}

	@Override
	public List<ListaPedidosDTO> listar() {
		return this.listaPedidosDTOConverter.toDTO(listaPedidosDAO.findAll());
	}

	@Override
	public void deletar(Long listaPedidosId) {
		this.listaPedidosDAO.delete(listaPedidosId);
	}
	
	@Override
	public ListaPedidosDTO buscarPorId(Long listaPedidosId) {
		return listaPedidosDTOConverter.toDTO(this.listaPedidosDAO.findById(listaPedidosId));
	}	

}
