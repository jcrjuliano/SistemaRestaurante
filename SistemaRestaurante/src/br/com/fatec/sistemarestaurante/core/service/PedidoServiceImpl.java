package br.com.fatec.sistemarestaurante.core.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dao.PedidoDAO;
import br.com.fatec.sistemarestaurante.api.dto.PedidoDTO;
import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.fatec.sistemarestaurante.api.service.PedidoService;
import br.com.fatec.sistemarestaurante.core.converter.PedidoDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class PedidoServiceImpl implements PedidoService {
	private PedidoDAO pedidoDAO;
	private PedidoDTOConverter pedidoDTOConverter;
	
	public PedidoServiceImpl(){
		pedidoDAO = ImplFinder.getImpl(PedidoDAO.class);
		pedidoDTOConverter = ImplFinder.getFinalImpl(PedidoDTOConverter.class);
	}
	@Override
	public PedidoDTO salvar(PedidoDTO pedidoDTO) {
		Pedido pedidoEntidade = this.pedidoDTOConverter.toEntity(pedidoDTO);
		Long id = pedidoDAO.save(pedidoEntidade);
		pedidoDTO.setId(id);
		return pedidoDTO;
	}

	@Override
	public void atualizar(PedidoDTO pedido) {
		Pedido pedidoEntidade = this.pedidoDTOConverter.toEntity(pedido);
		this.pedidoDAO.update(pedidoEntidade);

	}

	@Override
	public List<PedidoDTO> listar() {
		return this.pedidoDTOConverter.toDTO(pedidoDAO.findAll());
	}

	@Override
	public void deletar(Long ingredienteId) {
		this.pedidoDAO.delete(ingredienteId);
	}
	
	@Override
	public PedidoDTO buscarPorId(Long pedidoId) {
		return pedidoDTOConverter.toDTO(this.pedidoDAO.findById(pedidoId));
	}	

}
