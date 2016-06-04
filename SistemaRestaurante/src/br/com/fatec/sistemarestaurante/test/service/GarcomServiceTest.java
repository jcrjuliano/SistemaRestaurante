package br.com.fatec.sistemarestaurante.test.service;

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
	}
	

}
