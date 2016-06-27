package br.com.fatec.sistemarestaurante.test.service;

import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dto.ProdutoDTO;
import br.com.fatec.sistemarestaurante.test.commons.TestCenario;

public class ProdutoServiceTest extends TestCenario{
	
	@Test
	public void salvar(){
		
		ProdutoDTO dto = new ProdutoDTO(null,"Presunto", "Disponivel", 29.99);
		
		ProdutoDTO salvo = this.produtoService.salvar(dto);
		
		salvo = this.produtoService.buscarPorId(salvo.getId());
		
		Assert.assertEquals(new Long(1), salvo.getId());
		Assert.assertEquals("Presunto", salvo.getDescricao());
		Assert.assertEquals("Disponivel", salvo.getStatus());
		Assert.assertEquals(new Double(29.99), salvo.getPreco());
	}
	
	@Test
	public void atualizar(){
		ProdutoDTO dto = new ProdutoDTO(null, "Apresuntado","Disponivel", 39.99);
		ProdutoDTO salvo = this.produtoService.salvar(dto);
		salvo = this.produtoService.buscarPorId(salvo.getId());
		
		salvo.setDescricao("Apresuntado");
		salvo.setStatus("Disponivel");
		salvo.setPreco(39.99);
		this.produtoService.atualizar(salvo);
		
		Assert.assertEquals(new Long(1), salvo.getId());
		Assert.assertEquals("Apresuntado", salvo.getDescricao());
		Assert.assertEquals("Disponivel", salvo.getStatus());
		Assert.assertEquals(new Double(39.99), salvo.getPreco());
	}
	
	@Test
	public void deletar() {
		ProdutoDTO dto = new ProdutoDTO(null, "Presunto", "Disponivel", 29.99);
		
		ProdutoDTO salvo = this.produtoService.salvar(dto);
		
		salvo = this.produtoService.buscarPorId(salvo.getId());
		
		this.produtoService.deletar(salvo.getId());
		dto = this.produtoService.buscarPorId(salvo.getId());
		
		Assert.assertNull(dto);
	}


}
