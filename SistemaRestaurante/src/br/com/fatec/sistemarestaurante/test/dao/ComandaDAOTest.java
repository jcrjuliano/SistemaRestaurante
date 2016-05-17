package br.com.fatec.sistemarestaurante.test.dao;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.ComandaDAO;
import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.fatec.sistemarestaurante.test.commons.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;


public class ComandaDAOTest extends TestBase {

	private ComandaDAO dao;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(ComandaDAO.class);
	}
	
	@Test
	public void testSave(){

		Comanda comandaSalvar = new Comanda();
		
		Calendar dataDeAbertura = Calendar.getInstance();
		dataDeAbertura.set(2016, 2, 19, 19, 20, 0);
		Calendar dataDeFechamento = Calendar.getInstance();
		dataDeFechamento.set(2016, 2, 19, 23, 15, 0);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		comandaSalvar.setDataAbertura(dataDeAbertura.getTime());
		comandaSalvar.setDataFechamento(dataDeFechamento.getTime());
		comandaSalvar.setValorTotal(153.40);	
		
		
		Long id = this.dao.save(comandaSalvar);
		
		Comanda comandaSalvo = this.dao.findById(id);
		
		Assert.assertNotNull(comandaSalvo);
		Assert.assertEquals("19/03/2016 19:20:00", format.format(comandaSalvo.getDataAbertura()));
		Assert.assertEquals("19/03/2016 23:15:00", format.format(comandaSalvo.getDataFechamento()));
		Assert.assertEquals(153,40, comandaSalvo.getValorTotal());
		
	}
	
	@Test
	public void testUpdate(){
		Comanda comandaSalvar = new Comanda();
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar dataDeAbertura = Calendar.getInstance();
		Calendar dataDeFechamento = Calendar.getInstance();
		
		dataDeAbertura.set(2016, 2, 19, 19, 20, 0);
		dataDeFechamento.set(2016, 2, 19, 23, 15, 0);
		
		comandaSalvar.setDataAbertura(dataDeAbertura.getTime());
		comandaSalvar.setDataFechamento(dataDeFechamento.getTime());
		comandaSalvar.setValorTotal(153.40);	
		
		
		Long id = this.dao.save(comandaSalvar);
		
		
		Comanda comandaAtualizar = this.dao.findById(id);
		
		dataDeAbertura.set(2016, 2, 20, 15, 00, 0);
		dataDeFechamento.set(2016, 2, 20, 18, 00, 0);
		
		comandaAtualizar.setDataAbertura(dataDeAbertura.getTime());
		comandaAtualizar.setDataFechamento(dataDeFechamento.getTime());
		comandaAtualizar.setValorTotal(74.80);
		
		this.dao.update(comandaAtualizar);
		
		Comanda comandaAtualizado = this.dao.findById(id);
				
		Assert.assertNotNull(comandaAtualizado);
		Assert.assertEquals("20/03/2016 15:00:00", format.format(comandaAtualizado.getDataAbertura()));
		Assert.assertEquals("20/03/2016 18:00:00", format.format(comandaAtualizado.getDataFechamento()));
		Assert.assertEquals(74,80, comandaAtualizado.getValorTotal());
	}
	
	@Test
	public void testDelete(){
		Comanda comandaSalvar = new Comanda();
				
		Calendar dataDeAbertura = Calendar.getInstance();
		Calendar dataDeFechamento = Calendar.getInstance();
		
		dataDeAbertura.set(2016, 2, 19, 19, 20, 0);
		dataDeFechamento.set(2016, 2, 19, 23, 15, 0);
		
		comandaSalvar.setDataAbertura(dataDeAbertura.getTime());
		comandaSalvar.setDataFechamento(dataDeFechamento.getTime());
		comandaSalvar.setValorTotal(153.40);
		
		
		Long id = this.dao.save(comandaSalvar);
		
		this.dao.delete(id);
		
		Comanda comandaDeletado = this.dao.findById(id);
		
		Assert.assertNull(comandaDeletado);
	}
	
	@Test
	public void testFindAll(){
		
		Calendar dataDeAbertura = Calendar.getInstance();
		Calendar dataDeFechamento = Calendar.getInstance();
		
		dataDeAbertura.set(2016, 2, 19, 19, 20, 0);
		dataDeFechamento.set(2016, 2, 19, 23, 15, 0);

			
		Comanda com1 = new Comanda();
		Comanda com2 = new Comanda();
				
		dataDeAbertura.set(2016, 2, 19, 19, 20, 0);
		dataDeFechamento.set(2016, 2, 19, 23, 15, 0);
		
		com1.setDataAbertura(dataDeAbertura.getTime());
		com1.setDataFechamento(dataDeFechamento.getTime());
		com1.setValorTotal(153.40);
		
		//Set data para com2
		
		dataDeAbertura.set(2016, 2, 20, 15, 00, 0);
		dataDeFechamento.set(2016, 2, 20, 18, 00, 0);
		
		com2.setDataAbertura(dataDeAbertura.getTime());
		com2.setDataFechamento(dataDeFechamento.getTime());
		com2.setValorTotal(74.80);			

		
		this.dao.save(com1);
		this.dao.save(com2);
		
		List<Comanda> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		//SimpleDateFormat foi criado para fazer a máscara da data, para podermos comparar com uma string.
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Assert.assertEquals("19/03/2016 19:20:00", format.format(encontrados.get(0).getDataAbertura()));
		Assert.assertEquals("19/03/2016 23:15:00", format.format(encontrados.get(0).getDataFechamento()));
		Assert.assertEquals(153,40, encontrados.get(0).getValorTotal());
		
		Assert.assertEquals("20/03/2016 15:00:00", format.format(encontrados.get(1).getDataAbertura()));
		Assert.assertEquals("20/03/2016 18:00:00", format.format(encontrados.get(1).getDataFechamento()));
		Assert.assertEquals(74,80, encontrados.get(1).getValorTotal());
		
		
	}
}
