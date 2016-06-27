package br.com.fatec.sistemarestaurante.test.service;

import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dto.IngredienteDTO;
import br.com.fatec.sistemarestaurante.test.commons.TestCenario;

public class IngredienteServiceTest extends TestCenario{
	
	@Test
	public void salvar(){
		IngredienteDTO dto = new IngredienteDTO(null,"Presunto");
		IngredienteDTO salvo = this.ingredienteService.salvar(dto);
		salvo = this.ingredienteService.findById(salvo.getId());
		Assert.assertEquals(new Long(1), salvo.getId());
		Assert.assertEquals("Presunto", salvo.getDescricao());
	}
	
	@Test
	public void atualizar(){
		IngredienteDTO dto = new IngredienteDTO(null, "Apresuntado");
		IngredienteDTO salvo = this.ingredienteService.salvar(dto);
		salvo = this.ingredienteService.findById(salvo.getId());
		
		salvo.setDescricao("Apresuntado");
		this.ingredienteService.atualizar(salvo);
		
		Assert.assertEquals(new Long(1), salvo.getId());
		Assert.assertEquals("Apresuntado", salvo.getDescricao());
	}
	
	@Test
	public void deletar() {
		IngredienteDTO dto = new IngredienteDTO(null, "Presunto");
		
		IngredienteDTO salvo = this.ingredienteService.salvar(dto);
		
		salvo = this.ingredienteService.findById(salvo.getId());
		
		this.ingredienteService.deletar(salvo.getId());
		dto = this.ingredienteService.findById(salvo.getId());
		
		Assert.assertNull(dto);
	}


}
