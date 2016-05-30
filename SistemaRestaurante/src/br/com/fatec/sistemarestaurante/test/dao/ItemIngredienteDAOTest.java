package br.com.fatec.sistemarestaurante.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.IngredienteDAO;
import br.com.fatec.sistemarestaurante.api.dao.ItemIngredienteDAO;
import br.com.fatec.sistemarestaurante.api.dao.ProdutoDAO;
import br.com.fatec.sistemarestaurante.api.entity.Ingrediente;
import br.com.fatec.sistemarestaurante.api.entity.ItemIngrediente;
import br.com.fatec.sistemarestaurante.api.entity.Produto;
import br.com.fatec.sistemarestaurante.test.commons.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class ItemIngredienteDAOTest extends TestBase {

	private ItemIngredienteDAO dao;
	private ProdutoDAO produtoDao;
	private IngredienteDAO ingredienteDao;
	
	private Produto produto;
	private Ingrediente ingrediente;
	
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(ItemIngredienteDAO.class);
		this.produtoDao = ImplFinder.getImpl(ProdutoDAO.class);
		this.ingredienteDao = ImplFinder.getImpl(IngredienteDAO.class);
		/**
		 * Configura o Ingrediente base para os testes
		 */
		ingrediente = new Ingrediente();
		ingrediente.setDescricao("Pão");
		ingrediente.setId(this.ingredienteDao.save(ingrediente));
		
		/**
		 * Configura o produto base para os testes:
		 */
		
		produto = new Produto();
		produto.setDescricao("X-Salada");
		produto.setStatus("Disponivel");
		produto.setPreco(8.50);
		produto.setId(this.produtoDao.save(produto));
	}
	
	@Test
	public void testSave(){

		ItemIngrediente itemIngredienteSalvar = new ItemIngrediente();
		
		itemIngredienteSalvar.setProduto(produto);
		itemIngredienteSalvar.setQuantidade(10);
		itemIngredienteSalvar.setIngrediente(ingrediente);		
		
		this.dao.save(itemIngredienteSalvar);
		
		ItemIngrediente itemIngredienteSalvo = new ItemIngrediente();
		itemIngredienteSalvo = this.dao.findByIds(itemIngredienteSalvar.getProduto().getId(), itemIngredienteSalvar.getIngrediente().getId());
		
		Assert.assertNotNull(itemIngredienteSalvo);
		Assert.assertEquals(itemIngredienteSalvar.getProduto().getId(), itemIngredienteSalvo.getProduto().getId());
		Assert.assertEquals(itemIngredienteSalvar.getIngrediente().getId(), itemIngredienteSalvo.getIngrediente().getId());
		Assert.assertEquals(new Integer(10), itemIngredienteSalvo.getQuantidade());
		
		
	}
	
	@Test
	public void testUpdate(){
		ItemIngrediente itemIngredienteSalvar = new ItemIngrediente();
		
		itemIngredienteSalvar.setProduto(produto);
		itemIngredienteSalvar.setQuantidade(10);
		itemIngredienteSalvar.setIngrediente(ingrediente);		
		
		this.dao.save(itemIngredienteSalvar);
		
		
		ItemIngrediente itemIngredienteAtualizar = this.dao.findByIds(itemIngredienteSalvar.getProduto().getId(), itemIngredienteSalvar.getIngrediente().getId());
		
		itemIngredienteAtualizar.setQuantidade(17);
		
		this.dao.update(itemIngredienteAtualizar);
		
		ItemIngrediente itemIngredienteAtualizado = this.dao.findByIds(itemIngredienteAtualizar.getProduto().getId(), itemIngredienteAtualizar.getIngrediente().getId());
				
		Assert.assertNotNull(itemIngredienteAtualizado);
		Assert.assertEquals(new Integer(17), itemIngredienteAtualizado.getQuantidade());
		
	}
	
	@Test
	public void testDelete(){
		
		ItemIngrediente itemIngredienteSalvar = new ItemIngrediente();
		
		itemIngredienteSalvar.setProduto(produto);
		itemIngredienteSalvar.setQuantidade(10);
		itemIngredienteSalvar.setIngrediente(ingrediente);		
		
		this.dao.save(itemIngredienteSalvar);
		
		this.dao.delete(produto.getId(), ingrediente.getId());
		
		ItemIngrediente itemIngredienteDeletado = this.dao.findByIds(produto.getId(), ingrediente.getId());		
		Assert.assertNull(itemIngredienteDeletado);
	}
	
	@Test
	public void testFindByProduto(){
		
		ItemIngrediente itemIngredienteSalvar = new ItemIngrediente();
		
		itemIngredienteSalvar.setProduto(produto);
		itemIngredienteSalvar.setQuantidade(10);
		itemIngredienteSalvar.setIngrediente(ingrediente);		
				
		this.dao.save(itemIngredienteSalvar);
		Ingrediente ingrediente2 = new Ingrediente();
		ingrediente2.setDescricao("Coca-Cola");
		ingrediente2.setId(this.ingredienteDao.save(ingrediente2));
		
		itemIngredienteSalvar.setIngrediente(ingrediente2);
		itemIngredienteSalvar.setQuantidade(4);
		
		this.dao.save(itemIngredienteSalvar);

		List<ItemIngrediente> encontrados = this.dao.findByProduto(produto.getId());
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals(ingrediente.getId(), encontrados.get(0).getIngrediente().getId());
		Assert.assertEquals(produto.getId(), encontrados.get(0).getProduto().getId());
		Assert.assertEquals(new Integer(10), encontrados.get(0).getQuantidade());
		
		Assert.assertEquals(ingrediente2.getId(), encontrados.get(1).getIngrediente().getId());
		Assert.assertEquals(produto.getId(), encontrados.get(1).getProduto().getId());
		Assert.assertEquals(new Integer(4), encontrados.get(1).getQuantidade());
		
	}
	
	
	@Test
	public void testFindAll(){
		ItemIngrediente ingred1 = new ItemIngrediente();
		ItemIngrediente ingred2 = new ItemIngrediente();
		
		ingred1.setProduto(produto);
		ingred1.setQuantidade(10);
		ingred1.setIngrediente(ingrediente);		
		
		ingred2.setProduto(produto);
		ingred2.setQuantidade(17);
		ingred2.setIngrediente(ingrediente);	
		
		this.dao.save(ingred1);
		this.dao.save(ingred2);
		
		List<ItemIngrediente> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals(produto.getId(), encontrados.get(0).getProduto().getId());
		Assert.assertEquals(ingrediente.getId(), encontrados.get(0).getIngrediente().getId());
		Assert.assertEquals(new Integer(10), encontrados.get(0).getQuantidade());
		
		Assert.assertEquals(produto.getId(), encontrados.get(1).getProduto().getId());
		Assert.assertEquals(ingrediente.getId(), encontrados.get(1).getIngrediente().getId());
		Assert.assertEquals(new Integer(17), encontrados.get(1).getQuantidade());
		
	}
}