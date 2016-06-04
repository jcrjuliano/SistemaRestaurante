package br.com.fatec.sistemarestaurante.test.commons;

import java.util.Map;

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
	

}
