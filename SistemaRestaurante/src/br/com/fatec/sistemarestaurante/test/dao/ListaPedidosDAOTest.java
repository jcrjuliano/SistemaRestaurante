package br.com.fatec.sistemarestaurante.test.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;




// Usar este org.junit.Assert para sumir com os erros.
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.ComandaDAO;
import br.com.fatec.sistemarestaurante.api.dao.GarcomDAO;
import br.com.fatec.sistemarestaurante.api.dao.ListaPedidosDAO;
import br.com.fatec.sistemarestaurante.api.dao.PedidoDAO;
import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.fatec.sistemarestaurante.api.entity.Garcom;
import br.com.fatec.sistemarestaurante.api.entity.ListaPedidos;
import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.fatec.sistemarestaurante.test.commons.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;


public class ListaPedidosDAOTest extends TestBase{
	
	private Pedido pedido;
	
	private ListaPedidosDAO dao;
	private PedidoDAO daoPedido;
	
	private ComandaDAO daoComanda;
	private GarcomDAO daoGarcom;
	
	private Comanda comanda;
	private Garcom garcom;
	
	private Calendar dataDeAbertura;
	private Calendar dataDeFechamento;
	
	private SimpleDateFormat format;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(ListaPedidosDAO.class);
		this.daoPedido = ImplFinder.getImpl(PedidoDAO.class);	
		this.daoComanda = ImplFinder.getImpl(ComandaDAO.class);
		this.daoGarcom = ImplFinder.getImpl(GarcomDAO.class);
		

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

		ListaPedidos listaPedidosSalvar = new ListaPedidos();
		
		listaPedidosSalvar.setPedido(pedido);
		listaPedidosSalvar.setStatus("Aguardando");
		
		
		Long id = this.dao.save(listaPedidosSalvar);
		
		ListaPedidos listaPedidosSalvo = this.dao.findById(id);
		
		Assert.assertNotNull(listaPedidosSalvo);
		Assert.assertEquals(pedido.getId(), listaPedidosSalvo.getPedido().getId());
		Assert.assertEquals("Aguardando", listaPedidosSalvo.getStatus());
		
	}
	
	@Test
	public void testUpdate(){
		ListaPedidos listaPedidosSalvar = new ListaPedidos();
		
		listaPedidosSalvar.setPedido(pedido);
		listaPedidosSalvar.setStatus("Aguardando");
		
		
		Long id = this.dao.save(listaPedidosSalvar);
		
		ListaPedidos listaPedidosAtualizar = this.dao.findById(id);
		
		listaPedidosAtualizar.setPedido(pedido);
		listaPedidosAtualizar.setStatus("Entregue");
		
		this.dao.update(listaPedidosAtualizar);
		
		ListaPedidos listaPedidosAtualizado = this.dao.findById(id);
				
		Assert.assertNotNull(listaPedidosAtualizado);
		Assert.assertEquals(pedido.getId(), listaPedidosAtualizado.getPedido().getId());
		Assert.assertEquals("Entregue", listaPedidosAtualizado.getStatus());
	}
	
	@Test
	public void testDelete(){
		ListaPedidos listaPedidosSalvar = new ListaPedidos();
		
		listaPedidosSalvar.setPedido(pedido);
		listaPedidosSalvar.setStatus("Aguardando");
		
		
		Long id = this.dao.save(listaPedidosSalvar);
		
		this.dao.delete(id);
		
		ListaPedidos listaPedidosDeletado = this.dao.findById(id);
		
		Assert.assertNull(listaPedidosDeletado);
	}
	
	@Test
	public void testFindAll(){
		ListaPedidos list1 = new ListaPedidos();
		ListaPedidos list2 = new ListaPedidos();
		
		list1.setPedido(pedido);
		list1.setStatus("Aguardando");
		
		list2.setPedido(pedido);
		list2.setStatus("Entregue");


		
		this.dao.save(list1);
		this.dao.save(list2);
		
		List<ListaPedidos> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals(pedido.getId(), encontrados.get(0).getPedido().getId());
		Assert.assertEquals("Aguardando", encontrados.get(0).getStatus());
		Assert.assertEquals(pedido.getId(), encontrados.get(1).getPedido().getId());
		Assert.assertEquals("Entregue", encontrados.get(1).getStatus());
		
	}
}