package br.com.fatec.sistemarestaurante.test.commons;

import java.util.Map;

import org.junit.Before;

import br.com.fatec.sistemarestaurante.api.dao.ComandaDAO;
import br.com.fatec.sistemarestaurante.api.dao.GarcomDAO;
import br.com.fatec.sistemarestaurante.api.dao.IngredienteDAO;
import br.com.fatec.sistemarestaurante.api.dao.ItemIngredienteDAO;
import br.com.fatec.sistemarestaurante.api.dao.ItemProdDAO;
import br.com.fatec.sistemarestaurante.api.dao.ListaPedidosDAO;
import br.com.fatec.sistemarestaurante.api.dao.PedidoDAO;
import br.com.fatec.sistemarestaurante.api.dao.ProdutoDAO;
import br.com.fatec.sistemarestaurante.api.dto.ComandaDTO;
import br.com.fatec.sistemarestaurante.api.dto.GarcomDTO;
import br.com.fatec.sistemarestaurante.api.dto.IngredienteDTO;
import br.com.fatec.sistemarestaurante.api.dto.ItemIngredienteDTO;
import br.com.fatec.sistemarestaurante.api.dto.ItemProdDTO;
import br.com.fatec.sistemarestaurante.api.dto.ListaPedidosDTO;
import br.com.fatec.sistemarestaurante.api.dto.PedidoDTO;
import br.com.fatec.sistemarestaurante.api.dto.ProdutoDTO;
import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.fatec.sistemarestaurante.api.entity.Garcom;
import br.com.fatec.sistemarestaurante.api.entity.Ingrediente;
import br.com.fatec.sistemarestaurante.api.entity.ItemIngrediente;
import br.com.fatec.sistemarestaurante.api.entity.ItemProd;
import br.com.fatec.sistemarestaurante.api.entity.ListaPedidos;
import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.fatec.sistemarestaurante.api.entity.Produto;
import br.com.fatec.sistemarestaurante.api.service.ComandaService;
import br.com.fatec.sistemarestaurante.api.service.GarcomService;
import br.com.fatec.sistemarestaurante.api.service.IngredienteService;
import br.com.fatec.sistemarestaurante.api.service.ItemIngredienteService;
import br.com.fatec.sistemarestaurante.api.service.ItemProdService;
import br.com.fatec.sistemarestaurante.api.service.ListaPedidosService;
import br.com.fatec.sistemarestaurante.api.service.PedidoService;
import br.com.fatec.sistemarestaurante.api.service.ProdutoService;
import br.com.fatec.sistemarestaurante.core.converter.ComandaDTOConverter;
import br.com.fatec.sistemarestaurante.core.converter.GarcomDTOConverter;
import br.com.fatec.sistemarestaurante.core.converter.IngredienteDTOConverter;
import br.com.fatec.sistemarestaurante.core.converter.PedidoDTOConverter;
import br.com.fatec.sistemarestaurante.core.converter.ProdutoDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

import com.google.common.collect.Maps;

public class TestCenario extends TestBase{
	
	protected ComandaDAO comandaDAO;
	protected GarcomDAO garcomDAO;
	protected IngredienteDAO ingredienteDAO;
	protected ItemIngredienteDAO itemIngredienteDAO;
	protected ItemProdDAO itemProdDAO;
	protected ListaPedidosDAO listaPedidosDAO;
	protected PedidoDAO pedidoDAO;
	protected ProdutoDAO produtoDAO;
	
	protected ComandaService comandaService;
	protected GarcomService garcomService;
	protected IngredienteService ingredienteService;
	protected ItemIngredienteService itemIngredienteService;
	protected ItemProdService itemProdService;
	protected ListaPedidosService listaPedidosService;
	protected PedidoService pedidoService;
	protected ProdutoService produtoService;
	
	protected ComandaDTOConverter comandaConverter;
	protected GarcomDTOConverter garcomConverter;
	protected PedidoDTOConverter pedidoConverter;
	protected ProdutoDTOConverter produtoConverter;
	protected IngredienteDTOConverter ingredienteConverter;
		
	protected Map<Long, Comanda> comandas = Maps.newHashMap();
	protected Map<Long, Garcom> garcons = Maps.newHashMap();
	protected Map<Long, Ingrediente> ingredientes = Maps.newHashMap();
	protected Map<String, ItemIngrediente> itenIngredientes = Maps.newHashMap();
	protected Map<Long, ItemProd> itemProd = Maps.newHashMap();
	protected Map<Long, ListaPedidos> listaPedidos = Maps.newHashMap();
	protected Map<Long, Pedido> pedidos = Maps.newHashMap();
	protected Map<Long, Produto> produtos = Maps.newHashMap();
	
	protected Map<Long, ComandaDTO> comandaDTO = Maps.newHashMap();
	protected Map<Long, GarcomDTO> garcomDTO = Maps.newHashMap();
	protected Map<Long, IngredienteDTO> ingredienteDTO = Maps.newHashMap();
	protected Map<Long, ItemIngredienteDTO> itemIngredienteDTO = Maps.newHashMap();
	protected Map<Long, ItemProdDTO> itemProdDTO = Maps.newHashMap();
	protected Map<Long, ListaPedidosDTO> listaPedidosDTO = Maps.newHashMap();
	protected Map<Long, PedidoDTO> pedidoDTO = Maps.newHashMap();
	protected Map<Long, ProdutoDTO> produtoDTO = Maps.newHashMap();
	
	@Before
	public void upCenario() {
		this.comandaDAO = ImplFinder.getImpl(ComandaDAO.class);
		this.garcomDAO = ImplFinder.getImpl(GarcomDAO.class);
		this.ingredienteDAO = ImplFinder.getImpl(IngredienteDAO.class);
		this.itemIngredienteDAO = ImplFinder.getImpl(ItemIngredienteDAO.class);
		this.itemProdDAO = ImplFinder.getImpl(ItemProdDAO.class);
		this.listaPedidosDAO = ImplFinder.getImpl(ListaPedidosDAO.class);
		this.pedidoDAO = ImplFinder.getImpl(PedidoDAO.class);
		this.produtoDAO = ImplFinder.getImpl(ProdutoDAO.class);
		
		this.comandaConverter = ImplFinder.getFinalImpl(ComandaDTOConverter.class);
		this.garcomConverter = ImplFinder.getFinalImpl(GarcomDTOConverter.class);
		this.pedidoConverter = ImplFinder.getFinalImpl(PedidoDTOConverter.class);
		this.produtoConverter = ImplFinder.getFinalImpl(ProdutoDTOConverter.class);
		this.ingredienteConverter = ImplFinder.getFinalImpl(IngredienteDTOConverter.class);
		
		this.comandaService = ImplFinder.getImpl(ComandaService.class);
		this.garcomService = ImplFinder.getImpl(GarcomService.class);
		this.pedidoService = ImplFinder.getImpl(PedidoService.class);
		this.produtoService = ImplFinder.getImpl(ProdutoService.class);
		this.ingredienteService = ImplFinder.getImpl(IngredienteService.class);
		
		/*this.comandaService = ImplFinder.getImpl(ComandaService.class);
		this.ingredienteService = ImplFinder.getImpl(IngredienteService.class);
		this.itemIngredienteService = ImplFinder.getImpl(ItemIngredienteService.class);
		this.itemProdService = ImplFinder.getImpl(ItemProdService.class);
		this.listaPedidosService = ImplFinder.getImpl(ListaPedidosService.class);
		this.pedidoService = ImplFinder.getImpl(PedidoService.class);
		this.produtoService = ImplFinder.getImpl(ProdutoService.class);*/
		
	}
	

}
