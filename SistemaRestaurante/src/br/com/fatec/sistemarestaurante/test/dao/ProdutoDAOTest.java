package br.com.fatec.sistemarestaurante.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.ProdutoDAO;
import br.com.fatec.sistemarestaurante.api.entity.Produto;
import br.com.fatec.sistemarestaurante.test.commons.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;


public class ProdutoDAOTest extends TestBase {
	
	private ProdutoDAO dao;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(ProdutoDAO.class);
	}
	
	@Test
	public void testSave(){

		Produto produtoSalvar = new Produto();
		
		produtoSalvar.setDescricao("Coca-cola");
		produtoSalvar.setStatus("Disponivel");
		produtoSalvar.setPreco(8.50);
		
		Long id = this.dao.save(produtoSalvar);
		
		Produto produtoSalvo = this.dao.findById(id);
		
		Assert.assertNotNull(produtoSalvo);
		Assert.assertEquals("Coca-cola", produtoSalvo.getDescricao());
		Assert.assertEquals("Disponivel", produtoSalvo.getStatus());
		Assert.assertEquals(new Double(8.50), produtoSalvo.getPreco());
		
		
	}
	
	@Test
	public void testUpdate(){
		Produto produtoSalvar = new Produto();
		
		produtoSalvar.setDescricao("Coca-cola");
		produtoSalvar.setStatus("Disponivel");
		produtoSalvar.setPreco(8.50);
		
		Long id = this.dao.save(produtoSalvar);
		
		Produto produtoAtualizar = this.dao.findById(id);
		
		produtoAtualizar.setDescricao("Fanta");
		produtoAtualizar.setPreco(8.00);
		produtoAtualizar.setStatus("Indisponivel");
		
		this.dao.update(produtoAtualizar);
		
		Produto produtoAtualizado = this.dao.findById(id);
				
		Assert.assertNotNull(produtoAtualizado);
		Assert.assertEquals("Fanta", produtoAtualizado .getDescricao());
		Assert.assertEquals("Indisponivel", produtoAtualizado .getStatus());
		Assert.assertEquals(new Double(8.00), produtoAtualizado.getPreco());
	}
	
	@Test
	public void testDelete(){
		Produto produtoSalvar = new Produto();
		
		produtoSalvar.setDescricao("Coca-cola");
		produtoSalvar.setStatus("Disponivel");
		produtoSalvar.setPreco(8.50);
		
		Long id = this.dao.save(produtoSalvar);
		
		this.dao.delete(id);
		
		Produto produtoDeletado = this.dao.findById(id);
		
		Assert.assertNull(produtoDeletado);
	}
	
	@Test
	public void testFindAll(){
		Produto prod1 = new Produto();
		Produto prod2 = new Produto();
		
		prod1.setDescricao("Coca-Cola");
		prod1.setPreco(8.50);
		prod1.setStatus("Disponivel");
				
		prod2.setDescricao("Fanta");
		prod2.setPreco(8.00);
		prod2.setStatus("Disponivel");
		
		this.dao.save(prod1);
		this.dao.save(prod2);
		
		List<Produto> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals("Coca-Cola", encontrados.get(0).getDescricao());
		Assert.assertEquals("Disponivel", encontrados.get(0).getStatus());
		Assert.assertEquals(new Double(8.50), encontrados.get(0).getPreco());
		
		Assert.assertEquals("Fanta", encontrados.get(1).getDescricao());
		Assert.assertEquals("Disponivel", encontrados.get(1).getStatus());
		Assert.assertEquals(new Double(8.00), encontrados.get(1).getPreco());
	}
	
	@Test
	public void testFindByStatus(){
		Produto prod1 = new Produto();
		Produto prod2 = new Produto();
		Produto prod3 = new Produto();
		Produto prod4 = new Produto();
		
		prod1.setDescricao("Coca-Cola");
		prod1.setPreco(8.50);
		prod1.setStatus("Disponivel");
				
		prod2.setDescricao("Fanta");
		prod2.setPreco(8.00);
		prod2.setStatus("Disponivel");
		
		prod3.setDescricao("Pão");
		prod3.setPreco(0.50);
		prod3.setStatus("Disponivel");
				
		prod4.setDescricao("Whisky");
		prod4.setPreco(120.00);
		prod4.setStatus("Indisponivel");
		
		this.dao.save(prod1);
		this.dao.save(prod2);
		this.dao.save(prod3);
		this.dao.save(prod4);
		
		List<Produto> disponiveis = this.dao.findByStats("Disponivel");
		List<Produto> indisponiveis = this.dao.findByStats("Indisponivel");
		
		Assert.assertEquals(3, disponiveis.size());
		Assert.assertEquals(1, indisponiveis.size());
		
		Assert.assertEquals("Coca-Cola", disponiveis.get(0).getDescricao());
		Assert.assertEquals("Disponivel", disponiveis.get(0).getStatus());
		Assert.assertEquals(new Double(8.50), disponiveis.get(0).getPreco());
		
		Assert.assertEquals("Fanta", disponiveis.get(1).getDescricao());
		Assert.assertEquals("Disponivel", disponiveis.get(1).getStatus());
		Assert.assertEquals(new Double(8.00), disponiveis.get(1).getPreco());
		
		Assert.assertEquals("Pão", disponiveis.get(2).getDescricao());
		Assert.assertEquals("Disponivel", disponiveis.get(2).getStatus());
		Assert.assertEquals(new Double(0.50), disponiveis.get(2).getPreco());
		
		Assert.assertEquals("Whisky", indisponiveis.get(0).getDescricao());
		Assert.assertEquals("Indisponivel", indisponiveis.get(0).getStatus());
		Assert.assertEquals(new Double(120.00), indisponiveis.get(0).getPreco());
		
	}

	public void runTests() {
		this.setDown();
		this.config();
		this.testSave();
		this.setDown();
		this.config();
		this.testUpdate();
		this.setDown();
		this.config();
		this.testFindAll();
		this.setDown();
		this.config();
		this.testDelete();
		this.setDown();
	}
}
