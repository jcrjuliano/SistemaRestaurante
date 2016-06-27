package br.com.fatec.sistemarestaurante.core.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.fatec.sistemarestaurante.api.dao.ComandaDAO;
import br.com.fatec.sistemarestaurante.api.dao.PedidoDAO;
import br.com.fatec.sistemarestaurante.api.dto.ComandaDTO;
import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.fatec.sistemarestaurante.api.service.ComandaService;
import br.com.fatec.sistemarestaurante.core.converter.ComandaDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class ComandaServiceImpl implements ComandaService {
	
	private ComandaDAO comandaDAO;
	private ComandaDTOConverter comandaDTOConverter;
	private PedidoDAO pedidoDAO;
	
	DateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	
	public ComandaServiceImpl(){
		comandaDAO = ImplFinder.getImpl(ComandaDAO.class);
		comandaDTOConverter = ImplFinder.getFinalImpl(ComandaDTOConverter.class);
		pedidoDAO = ImplFinder.getImpl(PedidoDAO.class);
	}

	@Override
	public ComandaDTO salvar(ComandaDTO comandaDTO) {
		Comanda comandaEntidade = this.comandaDTOConverter.toEntity(comandaDTO);
		if(comandaDTO.getId() == null ){
			comandaDTO.setId(comandaDAO.save(comandaEntidade));
		}else{
			comandaDAO.update(comandaEntidade);
		}
		return this.comandaDTOConverter.toDTO(comandaDAO.findById(comandaDTO.getId()));
	}

	@Override
	public ComandaDTO fecharComanda(ComandaDTO comanda) {
		Double valorTotal = pedidoDAO.calcTotalComanda(comanda.getId());
		Calendar date = Calendar.getInstance();

		valorTotal = pedidoDAO.calcTotalComanda(comanda.getId());
		comanda.setValorTotal(valorTotal);
		comanda.setDataFechamento(date.getTime());
		
		return this.salvar(comanda);
		
	}

	@Override
	public void deletar(Long comandaId) {
		this.comandaDAO.delete(comandaId);
	}

	@Override
	public List<ComandaDTO> listar() {
		return this.comandaDTOConverter.toDTO(this.comandaDAO.findAll());
	}

	@Override
	public ComandaDTO buscarPorId(Long comandaId) {
		return this.comandaDTOConverter.toDTO(this.comandaDAO.findById(comandaId));
	}

}
