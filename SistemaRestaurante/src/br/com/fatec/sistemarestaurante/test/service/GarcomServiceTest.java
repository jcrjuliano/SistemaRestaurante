package br.com.fatec.sistemarestaurante.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dto.GarcomDTO;
import br.com.fatec.sistemarestaurante.test.commons.TestCenario;

public class GarcomServiceTest extends TestCenario{
	
	@Test
	public void salvar(){
		GarcomDTO dto = new GarcomDTO(null, "Fulano", "1111", "Masculino", "18");
		GarcomDTO salvo = this.garcomService.salvar(dto);
		salvo = this.garcomService.findById(salvo.getId());
		Assert.assertEquals(new Long(1), salvo.getId());
		Assert.assertEquals("Fulano", salvo.getNome());
		Assert.assertEquals("1111", salvo.getRegistro());
		Assert.assertEquals("Masculino", salvo.getSexo());
		Assert.assertEquals("18", salvo.getIdade());
	}
	
	@Test
	public void atualizar(){
		GarcomDTO dto = new GarcomDTO(null, "Fulano", "1111", "Masculino", "18");
		GarcomDTO salvo = this.garcomService.salvar(dto);
		salvo = this.garcomService.findById(salvo.getId());
		
		salvo.setNome("Ciclano");
		salvo.setRegistro("1234");
		salvo.setIdade("26");
		this.garcomService.atualizar(salvo);
		
		Assert.assertEquals(new Long(1), salvo.getId());
		Assert.assertEquals("Ciclano", salvo.getNome());
		Assert.assertEquals("1234", salvo.getRegistro());
		Assert.assertEquals("Masculino", salvo.getSexo());
		Assert.assertEquals("26", salvo.getIdade());
	}
	
	@Test
	public void deletar() {
		GarcomDTO dto = new GarcomDTO(null, "Fulano", "1111", "Masculino", "18");
		
		GarcomDTO salvo = this.garcomService.salvar(dto);
		
		salvo = this.garcomService.findById(salvo.getId());
		
		this.garcomService.deletar(salvo.getId());
		dto = this.garcomService.findById(salvo.getId());
		
		Assert.assertNull(dto);
	}
	
	@Test
	public void findAll(){
		GarcomDTO dto = new GarcomDTO(null, "Fulano", "1111", "Masculino", "18");
		GarcomDTO dto2 = new GarcomDTO(null, "Ciclano", "1234", "Masculino", "26");
		
		this.garcomService.salvar(dto);
		this.garcomService.salvar(dto2);
		List<GarcomDTO> salvos = new ArrayList<GarcomDTO>();	
		salvos = this.garcomService.listar();
		
		Assert.assertEquals(2, salvos.size());
		
		Assert.assertEquals(new Long(1), salvos.get(0).getId());
		Assert.assertEquals("Fulano", salvos.get(0).getNome());
		Assert.assertEquals("1111", salvos.get(0).getRegistro());
		Assert.assertEquals("Masculino", salvos.get(0).getSexo());
		Assert.assertEquals("18", salvos.get(0).getIdade());
		
		Assert.assertEquals(new Long(2), salvos.get(1).getId());
		Assert.assertEquals("Ciclano", salvos.get(1).getNome());
		Assert.assertEquals("1234", salvos.get(1).getRegistro());
		Assert.assertEquals("Masculino", salvos.get(1).getSexo());
		Assert.assertEquals("26", salvos.get(1).getIdade());
	}


}
