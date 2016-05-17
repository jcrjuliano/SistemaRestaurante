package br.com.fatec.sistemarestaurante.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.GarcomDAO;
import br.com.fatec.sistemarestaurante.api.entity.Garcom;
import br.com.fatec.sistemarestaurante.test.commons.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;


public class GarcomDAOTest extends TestBase {
	
	private GarcomDAO dao;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(GarcomDAO.class);
	}
	
	@Test
	public void testSave(){

		Garcom garcomSalvar = new Garcom();
		
		garcomSalvar.setNome("João");
		garcomSalvar.setIdade("19");
		garcomSalvar.setRegistro("0001");
		garcomSalvar.setSexo("Masculino");
		
		Long id = this.dao.save(garcomSalvar);
		
		Garcom garcomSalvo = this.dao.findById(id);
		
		Assert.assertNotNull(garcomSalvo);
		Assert.assertEquals("João", garcomSalvo.getNome());
		Assert.assertEquals("19", garcomSalvo.getIdade());
		Assert.assertEquals("0001", garcomSalvo.getRegistro());
		Assert.assertEquals("Masculino", garcomSalvo.getSexo());
		
	}
	
	@Test
	public void testUpdate(){
		Garcom garcomSalvar = new Garcom();
		
		garcomSalvar.setNome("João");
		garcomSalvar.setIdade("19");
		garcomSalvar.setRegistro("0001");
		garcomSalvar.setSexo("Masculino");
		
		Long id = this.dao.save(garcomSalvar);
		
		Garcom garcomAtualizar = this.dao.findById(id);
		
		garcomAtualizar.setNome("Pedro");
		garcomAtualizar.setIdade("20");
		garcomAtualizar.setRegistro("0002");
		garcomAtualizar.setSexo("Masculino");
		
		this.dao.update(garcomAtualizar);
		
		Garcom GarcomAtualizado = this.dao.findById(id);
				
		Assert.assertNotNull(GarcomAtualizado);
		Assert.assertEquals("Pedro", GarcomAtualizado.getNome());
		Assert.assertEquals("20", GarcomAtualizado.getIdade());
		Assert.assertEquals("0002", GarcomAtualizado.getRegistro());
		Assert.assertEquals("Masculino", GarcomAtualizado.getSexo());
		
	}
	
	@Test
	public void testDelete(){
		Garcom garcomSalvar = new Garcom();
		
		garcomSalvar.setNome("João");
		garcomSalvar.setIdade("19");
		garcomSalvar.setRegistro("0001");
		garcomSalvar.setSexo("Masculino");
		
		Long id = this.dao.save(garcomSalvar);
		
		this.dao.delete(id);
		
		Garcom GarcomDeletado = this.dao.findById(id);

		Assert.assertNull(GarcomDeletado);
	}
	
	@Test
	public void testFindAll(){
		Garcom garcom1 = new Garcom();
		Garcom garcom2 = new Garcom();
		
		garcom1.setNome("João");
		garcom1.setIdade("19");
		garcom1.setRegistro("0001");
		garcom1.setSexo("Masculino");
		
		garcom2.setNome("Pedro");
		garcom2.setIdade("20");
		garcom2.setRegistro("0002");
		garcom2.setSexo("Masculino");
		
		this.dao.save(garcom1);
		this.dao.save(garcom2);
		
		List<Garcom> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals("João", encontrados.get(0).getNome());
		Assert.assertEquals("19", encontrados.get(0).getIdade());
		Assert.assertEquals("0001", encontrados.get(0).getRegistro());
		Assert.assertEquals("Masculino", encontrados.get(0).getSexo());
		
		Assert.assertEquals("Pedro", encontrados.get(1).getNome());
		Assert.assertEquals("20", encontrados.get(1).getIdade());
		Assert.assertEquals("0002", encontrados.get(1).getRegistro());
		Assert.assertEquals("Masculino", encontrados.get(1).getSexo());
		
	}
	
}
