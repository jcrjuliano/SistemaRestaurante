package br.com.fatec.sistemarestaurante.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.ComandaDAO;
import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.spektro.minispring.core.implfinder.ImplFinder;


public class ComandaDAOTest {

	private ComandaDAO dao;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(ComandaDAO.class);
	}
	
	@Test
	public void testSave(){

		Comanda comandaSalvar = new Comanda();
		
		comandaSalvar.setDataAbertura("19/03/2016 19:20:00");
		comandaSalvar.setDataFechamento("19/03/2016 23:15:20");
		comandaSalvar.setValorTotal(153.40);	
		
		
		Long id = this.dao.save(comandaSalvar);
		
		Comanda comandaSalvo = this.dao.findById(id);
		
		Assert.assertNotNull(comandaSalvo);
		Assert.assertEquals("19/03/2016 19:20:00", comandaSalvo.getDataAbertura());
		Assert.assertEquals("19/03/2016 23:15:20", comandaSalvo.getDataFechamento());
		Assert.assertEquals(153,40, comandaSalvo.getValorTotal());
		
	}
	
	@Test
	public void testUpdate(){
		Comanda comandaSalvar = new Comanda();
		
		comandaSalvar.setDataAbertura("19/03/2016 19:20:00");
		comandaSalvar.setDataFechamento("19/03/2016 23:15:20");
		comandaSalvar.setValorTotal(153.40);	
		
		
		Long id = this.dao.save(comandaSalvar);
		
		
		Comanda comandaAtualizar = this.dao.findById(id);
		
		comandaAtualizar.setDataAbertura("20/03/2016 15:00:00");
		comandaAtualizar.setDataFechamento("20/03/2016 18:00:00");
		comandaAtualizar.setValorTotal(74.80);
		
		this.dao.update(comandaAtualizar);
		
		Comanda comandaAtualizado = this.dao.findById(id);
				
		Assert.assertNotNull(comandaAtualizado);
		Assert.assertEquals("20/03/2016 15:00:00", comandaAtualizado.getDataAbertura());
		Assert.assertEquals("20/03/2016 18:00:00", comandaAtualizado.getDataFechamento());
		Assert.assertEquals(74,80, comandaAtualizado.getValorTotal());
		
	}
	
	@Test
	public void testDelete(){
Comanda comandaSalvar = new Comanda();
		
		comandaSalvar.setDataAbertura("19/03/2016 19:20:00");
		comandaSalvar.setDataFechamento("19/03/2016 23:15:20");
		comandaSalvar.setValorTotal(153.40);	
		
		
		Long id = this.dao.save(comandaSalvar);
		
		this.dao.delete(id);
		
		Comanda comandaDeletado = this.dao.findById(id);
		
		Assert.assertNull(comandaDeletado);
	}
	
	@Test
	public void testFindAll(){
		Comanda com1 = new Comanda();
		Comanda com2 = new Comanda();
				
		com1.setDataAbertura("19/03/2016 19:20:00");
		com1.setDataFechamento("19/03/2016 23:15:20");
		com1.setValorTotal(153.40);	
		
		com2.setDataAbertura("20/03/2016 15:00:00");
		com2.setDataFechamento("20/03/2016 18:00:00");
		com2.setValorTotal(74.80);			

		
		this.dao.save(com1);
		this.dao.save(com2);
		
		List<Comanda> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals("19/03/2016 19:20:00", encontrados.get(0).getDataAbertura());
		Assert.assertEquals("19/03/2016 23:15:20", encontrados.get(0).getDataFechamento());
		Assert.assertEquals(153,40, encontrados.get(0).getValorTotal());
		
		Assert.assertEquals("20/03/2016 15:00:00", encontrados.get(1).getDataAbertura());
		Assert.assertEquals("20/03/2016 18:00:00", encontrados.get(1).getDataFechamento());
		Assert.assertEquals(74,80, encontrados.get(1).getValorTotal());
		
		
	}
}
