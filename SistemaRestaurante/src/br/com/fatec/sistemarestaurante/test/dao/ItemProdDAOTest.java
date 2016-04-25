package br.com.fatec.sistemarestaurante.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.ItemProdDAO;
import br.com.fatec.sistemarestaurante.api.entity.ItemProd;
import br.com.spektro.minispring.core.implfinder.ImplFinder;


public class ItemProdDAOTest {
	
	private ItemProdDAO dao;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(ItemProdDAO.class);
	}
	
	@Test
	public void testSave(){

		ItemProd itemProdSalvar = new ItemProd();
		
		itemProdSalvar.setIdPedido(Long.valueOf(1));
		itemProdSalvar.setIdProduto(Long.valueOf(5));
		itemProdSalvar.setPreco(10.55);
					
		Long id = this.dao.save(itemProdSalvar);
		
		ItemProd itemProdSalvo = this.dao.findById(id);
		
		Assert.assertNotNull(itemProdSalvo);
		Assert.assertEquals(Long.valueOf(1), itemProdSalvo.getIdPedido());
		Assert.assertEquals(Long.valueOf(5), itemProdSalvo.getIdProduto());
		Assert.assertEquals(10,55, itemProdSalvo.getPreco());
		
	}
	
	@Test
	public void testUpdate(){
		ItemProd itemProdSalvar = new ItemProd();
		
		itemProdSalvar.setIdPedido(Long.valueOf(1));
		itemProdSalvar.setIdProduto(Long.valueOf(5));
		itemProdSalvar.setPreco(10.55);
					
		Long id = this.dao.save(itemProdSalvar);
		
		ItemProd itemProdAtualizar = this.dao.findById(id);
		
		itemProdAtualizar.setIdPedido(Long.valueOf(2));
		itemProdAtualizar.setIdProduto(Long.valueOf(3));
		itemProdAtualizar.setPreco(16.55);
		
		this.dao.update(itemProdAtualizar);
		
		ItemProd itemProdAtualizado = this.dao.findById(id);
				
		Assert.assertNotNull(itemProdAtualizado);
		Assert.assertEquals(Long.valueOf(2), itemProdAtualizado.getIdPedido());
		Assert.assertEquals(Long.valueOf(3), itemProdAtualizado.getIdProduto());
		Assert.assertEquals(16,55, itemProdAtualizado.getPreco());
	}
	
	@Test
	public void testDelete(){
ItemProd itemProdSalvar = new ItemProd();
		
		itemProdSalvar.setIdPedido(Long.valueOf(1));
		itemProdSalvar.setIdProduto(Long.valueOf(5));
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
		
		itemProd1.setIdPedido(Long.valueOf(1));
		itemProd1.setIdProduto(Long.valueOf(5));
		itemProd1.setPreco(10.55);
				
		itemProd2.setIdPedido(Long.valueOf(2));
		itemProd2.setIdProduto(Long.valueOf(3));
		itemProd2.setPreco(16.55);
		
		this.dao.save(itemProd1);
		this.dao.save(itemProd2);
		
		List<ItemProd> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals(Long.valueOf(1), encontrados.get(0).getIdPedido());
		Assert.assertEquals(Long.valueOf(1), encontrados.get(0).getIdProduto());
		Assert.assertEquals(10,55, encontrados.get(0).getPreco());
		
		Assert.assertEquals(Long.valueOf(2), encontrados.get(1).getIdPedido());
		Assert.assertEquals(Long.valueOf(3), encontrados.get(1).getIdProduto());
		Assert.assertEquals(16,55, encontrados.get(1).getPreco());
		
	}
}



