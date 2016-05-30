package br.com.fatec.sistemarestaurante.test.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.ComandaDAO;
import br.com.fatec.sistemarestaurante.api.dao.GarcomDAO;
import br.com.fatec.sistemarestaurante.api.dao.ItemProdDAO;
import br.com.fatec.sistemarestaurante.api.dao.PedidoDAO;
import br.com.fatec.sistemarestaurante.api.dao.ProdutoDAO;
import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.fatec.sistemarestaurante.api.entity.Garcom;
import br.com.fatec.sistemarestaurante.api.entity.ItemProd;
import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.fatec.sistemarestaurante.api.entity.Produto;
import br.com.fatec.sistemarestaurante.test.commons.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;


public class ItemProdDAOTest extends TestBase {
	
	private ItemProdDAO dao;
	private ProdutoDAO daoProduto;
	private PedidoDAO daoPedido;
	
	private Produto produto;
	private Pedido pedido;
	
	/**
	 * configrar o pedido precisa de um garçom e uma comanda.
	 */
	private ComandaDAO daoComanda;
	private GarcomDAO daoGarcom;
	
	private Comanda comanda;
	private Garcom garcom;
	
	private Calendar dataDeAbertura;
	private Calendar dataDeFechamento;
	
	private SimpleDateFormat format;
	
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(ItemProdDAO.class);
		this.daoProduto = ImplFinder.getImpl(ProdutoDAO.class);
		this.daoPedido = ImplFinder.getImpl(PedidoDAO.class);	
		this.daoComanda = ImplFinder.getImpl(ComandaDAO.class);
		this.daoGarcom = ImplFinder.getImpl(GarcomDAO.class);
				
		produto = new Produto();
		produto.setDescricao("X-Salada");
		produto.setStatus("Disponivel");
		produto.setPreco(8.50);
		produto.setId(this.daoProduto.save(produto));
		
		
		/**
		 * Criando garçom e comanda para o Pedido
		 */
		
		comanda = new Comanda();
		
		dataDeAbertura = Calendar.getInstance();
		dataDeAbertura.set(2016, 2, 19, 19, 20, 0);
		dataDeFechamento = Calendar.getInstance();
		dataDeFechamento.set(2016, 2, 19, 23, 15, 0);
		
		format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		comanda.setDataAbertura(dataDeAbertura.getTime());
		comanda.setDataFechamento(dataDeFechamento.getTime());
		comanda.setValorTotal(153.40);		
		
		comanda.setId(this.daoComanda.save(comanda));
		
		/**
		 *  Criar ID Garçom
		 */
		
		garcom = new Garcom();
		
		garcom.setNome("João");
		garcom.setIdade("19");
		garcom.setRegistro("0001");
		garcom.setSexo("Masculino");
		
		garcom.setId(this.daoGarcom.save(garcom));
		
		/**
		 * Salvar o pedido
		 */
		pedido = new Pedido();
		pedido .setStatus("Aberto");
		pedido .setComanda(comanda);
		pedido .setGarcom(garcom);
		pedido .setDataAbertura(dataDeAbertura.getTime());
		pedido .setValorTotal(12.90);
				
		pedido.setId(this.daoPedido.save(pedido));
		
	}
	
	@Test
	public void testSave(){

		ItemProd itemProdSalvar = new ItemProd();
		
		itemProdSalvar.setProduto(produto);
		itemProdSalvar.setPedido(pedido);
		itemProdSalvar.setPreco(10.55);
					
		Long id = this.dao.save(itemProdSalvar);
		
		ItemProd itemProdSalvo = this.dao.findById(id);
		
		Assert.assertNotNull(itemProdSalvo);
		Assert.assertEquals(produto.getId(), itemProdSalvo.getProduto().getId());
		Assert.assertEquals(pedido.getId(), itemProdSalvo.getPedido().getId());
		Assert.assertEquals(new Double(10.55), itemProdSalvo.getPreco());
		
	}
	
	@Test
	public void testUpdate(){
		ItemProd itemProdSalvar = new ItemProd();
		
		itemProdSalvar.setPedido(pedido);
		itemProdSalvar.setProduto(produto);
		itemProdSalvar.setPreco(10.55);
					
		Long id = this.dao.save(itemProdSalvar);
		
		ItemProd itemProdAtualizar = this.dao.findById(id);

		itemProdAtualizar.setPreco(16.55);
		itemProdAtualizar.setPedido(pedido);
		itemProdAtualizar.setProduto(produto);
		itemProdAtualizar.setId(id);
		this.dao.update(itemProdAtualizar);
		
		ItemProd itemProdAtualizado = this.dao.findById(id);
				
		Assert.assertNotNull(itemProdAtualizado);
		Assert.assertEquals(new Double(16.55), itemProdAtualizado.getPreco());
	}
	
	@Test
	public void testDelete(){
		ItemProd itemProdSalvar = new ItemProd();
		
		itemProdSalvar.setPedido(pedido);
		itemProdSalvar.setProduto(produto);
		itemProdSalvar.setPreco(10.55);
					
		Long id = this.dao.save(itemProdSalvar);
		
		this.dao.delete(id);
		
		ItemProd itemProdDeletado = this.dao.findById(id);
		
		Assert.assertNull(itemProdDeletado);
	}
	
	@Test
	public void testFindAll(){
		ItemProd itemProd1 = new ItemProd();
		ItemProd itemProd2 = new ItemProd();
		
		itemProd1.setPedido(pedido);
		itemProd1.setProduto(produto);
		itemProd1.setPreco(10.55);
				
		itemProd2.setPedido(pedido);
		itemProd2.setProduto(produto);
		itemProd2.setPreco(16.55);
		
		this.dao.save(itemProd1);
		this.dao.save(itemProd2);
		
		List<ItemProd> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals(pedido.getId(), encontrados.get(0).getPedido().getId());
		Assert.assertEquals(produto.getId(), encontrados.get(0).getProduto().getId());
		Assert.assertEquals(new Double(10.55), encontrados.get(0).getPreco());
		
		Assert.assertEquals(pedido.getId(), encontrados.get(1).getPedido().getId());
		Assert.assertEquals(produto.getId(), encontrados.get(1).getProduto().getId());
		Assert.assertEquals(new Double(16.55), encontrados.get(1).getPreco());
		
	}
}



