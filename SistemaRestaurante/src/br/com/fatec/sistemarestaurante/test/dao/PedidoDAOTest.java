package br.com.fatec.sistemarestaurante.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.PedidoDAO;
import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.spektro.minispring.core.implfinder.ImplFinder;


public class PedidoDAOTest {
	
	private PedidoDAO dao;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(PedidoDAO.class);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSave(){

		Pedido pedidoSalvar = new Pedido();
		
		pedidoSalvar.setStatus("Aberto");
		pedidoSalvar.setIdComanda(1l);
		pedidoSalvar.setIdGarcom(1l);
		pedidoSalvar.setDataAbertura("19/03/2016");
		pedidoSalvar.setValorTotal(12.90);
		
		
		Long id = this.dao.save(pedidoSalvar);
		
		Pedido pedidoSalvo = this.dao.findById(id);
		
		Assert.assertNotNull(pedidoSalvo);
		Assert.assertEquals("Aberto", pedidoSalvo.getStatus());
		Assert.assertEquals(Long.valueOf("1"), pedidoSalvo.getIdComanda());
		Assert.assertEquals(Long.valueOf("1"), pedidoSalvo.getIdGarcom());
		Assert.assertEquals("19/03/2016", pedidoSalvo.getDataAbertura());
		Assert.assertEquals(12,90, pedidoSalvo.getValorTotal());
		
	}
	
	@Test
	public void testUpdate(){
Pedido pedidoSalvar = new Pedido();
		
		pedidoSalvar.setStatus("Aberto");
		pedidoSalvar.setIdComanda(1l);
		pedidoSalvar.setIdGarcom(1l);
		pedidoSalvar.setDataAbertura("19/03/2016");
		pedidoSalvar.setValorTotal(12.90);
		
		Long id = this.dao.save(pedidoSalvar);
		
		Pedido pedidoAtualizar = this.dao.findById(id);
		
		pedidoAtualizar.setStatus("Fechada");
		pedidoAtualizar.setIdComanda(2l);
		pedidoAtualizar.setIdGarcom(2l);
		pedidoAtualizar.setDataAbertura("15/03/2016");
		pedidoAtualizar.setValorTotal(100.00);
		
		this.dao.update(pedidoAtualizar);
		
		Pedido PedidoAtualizado = this.dao.findById(id);
				
		Assert.assertNotNull(PedidoAtualizado);
		Assert.assertEquals("Fechada", PedidoAtualizado.getStatus());
		Assert.assertEquals(Long.valueOf("2"), PedidoAtualizado.getIdComanda());
		Assert.assertEquals(Long.valueOf("2"), PedidoAtualizado.getIdGarcom());
		Assert.assertEquals("15/03/2016", PedidoAtualizado.getDataAbertura());
		Assert.assertEquals(100,00, PedidoAtualizado.getValorTotal());
	}
	
	@Test
	public void testDelete(){

		Pedido pedidoSalvar = new Pedido();
		
		pedidoSalvar.setStatus("Aberto");
		pedidoSalvar.setIdComanda(1l);
		pedidoSalvar.setIdGarcom(1l);
		pedidoSalvar.setDataAbertura("19/03/2016");
		pedidoSalvar.setValorTotal(12.90);
		
		
		Long id = this.dao.save(pedidoSalvar);
		
		this.dao.delete(id);
		
		Pedido pedidoDeletado = this.dao.findById(id);
		
		Assert.assertNull(pedidoDeletado);
	}
	
	@Test
	public void testFindAll(){
		Pedido ped1 = new Pedido();
		Pedido ped2 = new Pedido();
		
		ped1.setStatus("Aberto");
		ped1.setIdComanda(1l);
		ped1.setIdGarcom(1l);
		ped1.setDataAbertura("19/03/2016");
		ped1.setValorTotal(12.90);
			
		ped2.setStatus("Fechada");
		ped2.setIdComanda(2l);
		ped2.setIdGarcom(2l);
		ped2.setDataAbertura("15/03/2016");
		ped2.setValorTotal(100.00);
		
		this.dao.save(ped1);
		this.dao.save(ped2);
		
		List<Pedido> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals("Aberto", encontrados.get(0).getStatus());
		Assert.assertEquals(Long.valueOf("1"), encontrados.get(0).getIdComanda());
		Assert.assertEquals(Long.valueOf("1"), encontrados.get(0).getIdGarcom());
		Assert.assertEquals("19/03/2016", encontrados.get(0).getDataAbertura());
		Assert.assertEquals(12,90, encontrados.get(0).getValorTotal());
		
		Assert.assertEquals("Fechada", encontrados.get(1).getStatus());
		Assert.assertEquals(Long.valueOf("2"), encontrados.get(1).getIdComanda());
		Assert.assertEquals(Long.valueOf("2"), encontrados.get(1).getIdGarcom());
		Assert.assertEquals("15/03/2016", encontrados.get(1).getDataAbertura());
		Assert.assertEquals(100,00, encontrados.get(1).getValorTotal());
	}

}
