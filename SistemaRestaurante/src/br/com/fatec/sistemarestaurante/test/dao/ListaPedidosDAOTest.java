package br.com.fatec.sistemarestaurante.test.dao;

import java.util.List;

// Usar este org.junit.Assert para sumir com os erros.
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.ListaPedidosDAO;
import br.com.fatec.sistemarestaurante.api.entity.ListaPedidos;
import br.com.fatec.sistemarestaurante.test.commons.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;


public class ListaPedidosDAOTest extends TestBase{
	
	private ListaPedidosDAO dao;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(ListaPedidosDAO.class);
	}
	
	@Test
	public void testSave(){

		ListaPedidos listaPedidosSalvar = new ListaPedidos();
		
		listaPedidosSalvar.setIdPedido(Long.valueOf(1));
		listaPedidosSalvar.setStatus("Aguardando");
		
		
		Long id = this.dao.save(listaPedidosSalvar);
		
		ListaPedidos listaPedidosSalvo = this.dao.findById(id);
		
		Assert.assertNotNull(listaPedidosSalvo);
		Assert.assertEquals(Long.valueOf(1), listaPedidosSalvo.getIdPedido());
		Assert.assertEquals("Aguardando", listaPedidosSalvo.getStatus());
		
	}
	
	@Test
	public void testUpdate(){
		ListaPedidos listaPedidosSalvar = new ListaPedidos();
		
		listaPedidosSalvar.setIdPedido(Long.valueOf(1));
		listaPedidosSalvar.setStatus("Aguardando");
		
		
		Long id = this.dao.save(listaPedidosSalvar);
		
		ListaPedidos listaPedidosAtualizar = this.dao.findById(id);
		
		listaPedidosAtualizar.setIdPedido(Long.valueOf(2));
		listaPedidosAtualizar.setStatus("Entregue");
		
		this.dao.update(listaPedidosAtualizar);
		
		ListaPedidos listaPedidosAtualizado = this.dao.findById(id);
				
		Assert.assertNotNull(listaPedidosAtualizado);
		Assert.assertEquals(Long.valueOf(2), listaPedidosAtualizado.getIdPedido());
		Assert.assertEquals("Entregue", listaPedidosAtualizado.getStatus());
	}
	
	@Test
	public void testDelete(){
		ListaPedidos listaPedidosSalvar = new ListaPedidos();
		
		listaPedidosSalvar.setIdPedido(Long.valueOf(1));
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
		
		list1.setIdPedido(Long.valueOf(1));
		list1.setStatus("Aguardando");
		
		list2.setIdPedido(Long.valueOf(2));
		list2.setStatus("Entregue");


		
		this.dao.save(list1);
		this.dao.save(list2);
		
		List<ListaPedidos> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals(Long.valueOf(1), encontrados.get(0).getIdPedido());
		Assert.assertEquals("Aguardando", encontrados.get(0).getStatus());
		Assert.assertEquals(Long.valueOf(2), encontrados.get(1).getIdPedido());
		Assert.assertEquals("Entregue", encontrados.get(1).getStatus());
		
	}
}