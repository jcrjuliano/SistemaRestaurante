package br.com.fatec.sistemarestaurante.test.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dto.ComandaDTO;
import br.com.fatec.sistemarestaurante.api.entity.Garcom;
import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.fatec.sistemarestaurante.test.commons.TestCenario;

public class ComandaServiceTest extends TestCenario {
	private DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Test
	public void salvar(){
		Date dataAbertura = new Date();
		
		ComandaDTO comanda = new ComandaDTO();
		comanda.setDataAbertura(dataAbertura);
		
		//Foi necessário setar o valor total inicial como 0.00 e uma data de encerramente pois estava dando null pointer ao inserir.
		comanda.setValorTotal(new Double(0.00));
		comanda.setDataFechamento(dataAbertura);
		ComandaDTO salvo = this.comandaService.salvar(comanda);
		
		salvo = this.comandaService.buscarPorId(salvo.getId());
		
		Assert.assertEquals(new Long(1), salvo.getId());
		Assert.assertEquals(dataAbertura, salvo.getDataAbertura());
		Assert.assertEquals(dataAbertura,salvo.getDataFechamento());
		Assert.assertEquals(new Double(0.00), salvo.getValorTotal());
	}
	
	@Test
	public void atualizar(){
		Date dataAbertura = new Date();
		Calendar dataFechamento = Calendar.getInstance();
		
		dataFechamento.set(2016, 2, 19, 23, 15, 0);


		ComandaDTO comanda = new ComandaDTO();
		comanda.setDataAbertura(dataAbertura);
		comanda.setValorTotal(new Double(0.00));
		comanda.setDataFechamento(dataAbertura);
		ComandaDTO salvo = this.comandaService.salvar(comanda);
		
		ComandaDTO comandaAtualizada = this.comandaService.buscarPorId(salvo.getId());
		
		comandaAtualizada.setValorTotal(new Double(11.50));
		comandaAtualizada.setDataFechamento(dataFechamento.getTime());
		comandaAtualizada = this.comandaService.salvar(comandaAtualizada);
		
		Assert.assertEquals(new Long(1), comandaAtualizada.getId());
		Assert.assertEquals(dataAbertura, comandaAtualizada.getDataAbertura());
		Assert.assertEquals(new Double(11.50), comandaAtualizada.getValorTotal());
		Assert.assertEquals("19/03/2016 23:15:00", format.format(comandaAtualizada.getDataFechamento()));
	}
	
	@Test
	public void listAll(){
		Date dataAbertura = new Date();
		
		ComandaDTO comanda = new ComandaDTO();
		
		comanda.setDataAbertura(dataAbertura);
		comanda.setValorTotal(new Double(0.00));
		comanda.setDataFechamento(dataAbertura);
		
		this.comandaService.salvar(comanda);
				
		
		ComandaDTO comanda2 = new ComandaDTO();
		Calendar dataAbertura2 = Calendar.getInstance();
		
		dataAbertura2.set(2016, 2, 19, 23, 15, 0);
		
		comanda2.setDataAbertura(dataAbertura2.getTime());
		comanda2.setValorTotal(new Double(0.00));
		comanda2.setDataFechamento(dataAbertura2.getTime());
		
		this.comandaService.salvar(comanda2);
		
		List<ComandaDTO> encontrados = this.comandaService.listar();
		
		Assert.assertEquals(2, encontrados.size());
		
		Assert.assertEquals(new Long(1), encontrados.get(0).getId());
		Assert.assertEquals(dataAbertura, encontrados.get(0).getDataAbertura());
		Assert.assertEquals(dataAbertura,encontrados.get(0).getDataFechamento());
		Assert.assertEquals(new Double(0.00), encontrados.get(0).getValorTotal());
		
		Assert.assertEquals(new Long(2), encontrados.get(1).getId());
		Assert.assertEquals(dataAbertura2.getTime(), encontrados.get(1).getDataAbertura());
		Assert.assertEquals(dataAbertura2.getTime(),encontrados.get(1).getDataFechamento());
		Assert.assertEquals(new Double(0.00), encontrados.get(1).getValorTotal());
		
	}
	
	@Test
	public void fecharComanda(){
		Date dataAbertura = new Date();
		
		ComandaDTO comanda = new ComandaDTO();
		comanda.setDataAbertura(dataAbertura);
		comanda.setValorTotal(new Double(0.00));
		comanda.setDataFechamento(dataAbertura);
		this.comandaService.salvar(comanda);
		
		
		/**
		 *  Criar Garçom
		 */
		
		Garcom garcom = new Garcom();
		
		garcom.setNome("João");
		garcom.setIdade("19");
		garcom.setRegistro("0001");
		garcom.setSexo("Masculino");
		
		garcom.setId(this.garcomDAO.save(garcom));
		
		/**
		 * Add Pedidos na comanda
		 */
		Calendar dataDeAbertura = Calendar.getInstance();
		Calendar dataDeFechamento = Calendar.getInstance();
		Pedido ped1 = new Pedido();
		Pedido ped2 = new Pedido();
		dataDeAbertura.set(2016, 2, 19, 19, 20, 0);

		ped1.setStatus("Aberto");
		ped1.setComanda(this.comandaConverter.toEntity(comanda));
		ped1.setGarcom(garcom);
		ped1.setDataAbertura(dataDeAbertura.getTime());
		ped1.setValorTotal(12.90);
			
		
		dataDeAbertura.set(2016, 2, 15, 15, 20, 0);
		ped2.setStatus("Fechada");
		ped2.setComanda(this.comandaConverter.toEntity(comanda));
		ped2.setGarcom(garcom);
		ped2.setDataAbertura(dataDeAbertura.getTime());
		ped2.setValorTotal(100.00);
		
		this.pedidoDAO.save(ped1);
		this.pedidoDAO.save(ped2);
		
		//Sleep de 2 segundos para diferenciar a data de abertura com o fechamando da comanda.
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ComandaDTO comandaFechada = this.comandaService.fecharComanda(comanda);
		Assert.assertNotEquals(dataAbertura, comandaFechada.getDataFechamento());
		Assert.assertEquals(new Double(112.90), comandaFechada.getValorTotal());
		
	}
	
	@Test
	public void deletar(){
		Date dataAbertura = new Date();
		
		ComandaDTO comanda = new ComandaDTO();
		comanda.setDataAbertura(dataAbertura);
		comanda.setValorTotal(new Double(0.00));
		comanda.setDataFechamento(dataAbertura);
		ComandaDTO salvo = this.comandaService.salvar(comanda);
		this.comandaService.deletar(salvo.getId());
		
		ComandaDTO apagado = this.comandaService.buscarPorId(salvo.getId());
		
		Assert.assertNull(apagado);
		
	}
	

}
