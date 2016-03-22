package br.com.fatec.sistemarestaurante.test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.ItemIngredienteDAO;
import br.com.fatec.sistemarestaurante.api.entity.ItemIngrediente;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import junit.framework.Assert;

public class ItemIngredienteDAOTest {

	private ItemIngredienteDAO dao;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(ItemIngredienteDAO.class);
	}
	
	@Test
	public void testSave(){

		ItemIngrediente itemIngredienteSalvar = new ItemIngrediente();
		
		itemIngredienteSalvar.setProdId(Long.valueOf(1));
		itemIngredienteSalvar.setQuantidade(10);
				
		Long id = this.dao.save(itemIngredienteSalvar);
		
		ItemIngrediente ItemIngredienteSalvo = this.dao.findById(id);
		
		Assert.assertNotNull(ItemIngredienteSalvo);
		Assert.assertEquals(Long.valueOf(1), ItemIngredienteSalvo.getProdId());
		Assert.assertEquals(Integer.valueOf(10), ItemIngredienteSalvo.getQuantidade());
		
		
	}
	
	@Test
	public void testUpdate(){
		ItemIngrediente itemIngredienteSalvar = new ItemIngrediente();
		
		itemIngredienteSalvar.setProdId(Long.valueOf(1));
		itemIngredienteSalvar.setQuantidade(10);
				
		Long id = this.dao.save(itemIngredienteSalvar);
		
		ItemIngrediente itemIngredienteAtualizar = this.dao.findById(id);
		
		itemIngredienteAtualizar.setProdId(Long.valueOf(2));
		itemIngredienteAtualizar.setQuantidade(17);
		
		this.dao.update(itemIngredienteAtualizar);
		
		ItemIngrediente itemIngredienteAtualizado = this.dao.findById(id);
				
		Assert.assertNotNull(itemIngredienteAtualizado);
		Assert.assertEquals(Long.valueOf(2), itemIngredienteAtualizado.getProdId());
		Assert.assertEquals(Integer.valueOf(17), itemIngredienteAtualizado.getQuantidade());
	}
	
	@Test
	public void testDelete(){
ItemIngrediente itemIngredienteSalvar = new ItemIngrediente();
		
		itemIngredienteSalvar.setProdId(Long.valueOf(1));
		itemIngredienteSalvar.setQuantidade(10);
				
		Long id = this.dao.save(itemIngredienteSalvar);
		
		this.dao.delete(id);
		
		ItemIngrediente itemIngredienteDeletado = this.dao.findById(id);
		
		Assert.assertNull(itemIngredienteDeletado);
	}
	
	@Test
	public void testFindAll(){
		ItemIngrediente ingred1 = new ItemIngrediente();
		ItemIngrediente ingred2 = new ItemIngrediente();
		
		ingred1.setProdId(Long.valueOf(1));
		ingred1.setQuantidade(10);
				
		ingred2.setProdId(Long.valueOf(2));
		ingred2.setQuantidade(17);;
		
		this.dao.save(ingred1);
		this.dao.save(ingred2);
		
		List<ItemIngrediente> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals(Long.valueOf(1), encontrados.get(0).getProdId());
		Assert.assertEquals(Integer.valueOf(10), encontrados.get(0).getQuantidade());
		
		Assert.assertEquals(Long.valueOf(2), encontrados.get(1).getProdId());
		Assert.assertEquals(Integer.valueOf(17), encontrados.get(1).getQuantidade());
		
	}
}