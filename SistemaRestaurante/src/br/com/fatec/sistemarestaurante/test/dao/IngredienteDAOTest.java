package br.com.fatec.sistemarestaurante.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.IngredienteDAO;
import br.com.fatec.sistemarestaurante.api.entity.Ingrediente;
import br.com.fatec.sistemarestaurante.test.commons.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class IngredienteDAOTest extends TestBase{

	private IngredienteDAO dao;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(IngredienteDAO.class);
	}
	
	@Test
	public void testSave(){

		Ingrediente ingredienteSalvar = new Ingrediente();
		
		ingredienteSalvar.setDescricao("Pão");
				
		Long id = this.dao.save(ingredienteSalvar);
		
		Ingrediente ingredienteSalvo = this.dao.findById(id);
		
		Assert.assertNotNull(ingredienteSalvo);
		Assert.assertEquals("Pão", ingredienteSalvo.getDescricao());
		
	}
	
	@Test
	public void testUpdate(){
		Ingrediente ingredienteSalvar = new Ingrediente();
		
		ingredienteSalvar.setDescricao("Pão");
		
		Long id = this.dao.save(ingredienteSalvar);
		
		Ingrediente ingredienteAtualizar = this.dao.findById(id);
		
		ingredienteAtualizar.setDescricao("Presunto");
		
		this.dao.update(ingredienteAtualizar);
		
		Ingrediente ingredienteAtualizado = this.dao.findById(id);
				
		Assert.assertNotNull(ingredienteAtualizado );
		Assert.assertEquals("Presunto", ingredienteAtualizado.getDescricao());
	}
	
	@Test
	public void testDelete(){
Ingrediente ingredienteSalvar = new Ingrediente();
		
		ingredienteSalvar.setDescricao("Pão");
		
		Long id = this.dao.save(ingredienteSalvar);
		
		this.dao.delete(id);
		
		Ingrediente ingredienteDeletado = this.dao.findById(id);
		
		Assert.assertNull(ingredienteDeletado);
	}
	
	@Test
	public void testFindAll(){
		Ingrediente ingrediente1 = new Ingrediente();
		Ingrediente ingrediente2 = new Ingrediente();
		
		ingrediente1.setDescricao("Pão");
		
		ingrediente2.setDescricao("Presunto");
		
		
		this.dao.save(ingrediente1);
		this.dao.save(ingrediente2);
		
		List<Ingrediente> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals("Pão", encontrados.get(0).getDescricao());
		
		Assert.assertEquals("Presunto", encontrados.get(1).getDescricao());

		
	}
}
