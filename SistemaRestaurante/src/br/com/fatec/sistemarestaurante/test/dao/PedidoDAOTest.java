package br.com.fatec.sistemarestaurante.test.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.sistemarestaurante.api.dao.ComandaDAO;
import br.com.fatec.sistemarestaurante.api.dao.GarcomDAO;
import br.com.fatec.sistemarestaurante.api.dao.PedidoDAO;
import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.fatec.sistemarestaurante.api.entity.Garcom;
import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.fatec.sistemarestaurante.test.commons.TestBase;
import br.com.spektro.minispring.core.implfinder.ImplFinder;


public class PedidoDAOTest extends TestBase {
	
	private PedidoDAO dao;
	private ComandaDAO daoComanda;
	private GarcomDAO daoGarcom;
	
	private Comanda comanda;
	private Garcom garcom;
	
	private Calendar dataDeAbertura;
	private Calendar dataDeFechamento;
	
	private SimpleDateFormat format;
	
	@Before
	public void config(){
		this.dao = ImplFinder.getImpl(PedidoDAO.class);
		this.daoComanda = ImplFinder.getImpl(ComandaDAO.class);
		this.daoGarcom = ImplFinder.getImpl(GarcomDAO.class);
		

		/**
		 *  Criar ID comanda
		 */
		comanda = new Comanda();
		
		dataDeAbertura = Calendar.getInstance();
		dataDeAbertura.set(2016, 2, 19, 19, 20, 0);
		dataDeFechamento = Calendar.getInstance();
		dataDeFechamento.set(2016, 2, 19, 23, 15, 0);
		
		format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		comanda.setDataAbertura(dataDeAbertura.getTime());
		comanda.setDataFechamento(dataDeFechamento.getTime());
		comanda.setValorTotal(153.40);		
		
		comanda.setId(this.daoComanda.save(comanda));
		
		/**
		 *  Criar ID Garçom
		 */
		
		garcom = new Garcom();
		
		garcom.setNome("João");
		garcom.setIdade("19");
		garcom.setRegistro("0001");
		garcom.setSexo("Masculino");
		
		garcom.setId(this.daoGarcom.save(garcom));
		
		
	}
	

	@Test
	public void testSave(){
		

		Pedido pedidoSalvar = new Pedido();
		
		
		dataDeAbertura.set(2016, 2, 19, 19, 20, 0);
		
		pedidoSalvar.setStatus("Aberto");
		pedidoSalvar.setComanda(comanda);
		pedidoSalvar.setGarcom(garcom);
		pedidoSalvar.setDataAbertura(dataDeAbertura.getTime());
		pedidoSalvar.setValorTotal(12.90);
				
		Long id = this.dao.save(pedidoSalvar);
		
		Pedido pedidoSalvo = this.dao.findById(id);
		
		Assert.assertNotNull(pedidoSalvo);
		Assert.assertEquals("Aberto", pedidoSalvo.getStatus());
		Assert.assertEquals(comanda.getId(), pedidoSalvo.getComanda().getId());
		Assert.assertEquals(garcom.getId(), pedidoSalvo.getGarcom().getId());
		Assert.assertEquals("19/03/2016 19:20:00", format.format(pedidoSalvo.getDataAbertura()));
		Assert.assertEquals(new Double(12.90), pedidoSalvo.getValorTotal());
		
	}
	
	@Test
	public void testUpdate(){
		Pedido pedidoSalvar = new Pedido();
		
		dataDeAbertura.set(2016, 2, 19, 19, 20, 0);
		
		pedidoSalvar.setStatus("Aberto");
		pedidoSalvar.setComanda(comanda);
		pedidoSalvar.setGarcom(garcom);
		pedidoSalvar.setDataAbertura(dataDeAbertura.getTime());
		pedidoSalvar.setValorTotal(12.90);
		
		Long id = this.dao.save(pedidoSalvar);
		
		Pedido pedidoAtualizar = this.dao.findById(id);
		
		dataDeAbertura.set(2016, 2, 15, 15, 20, 0);
		
		/**
		 * Configura comanda atualizar
		 */
		
		pedidoAtualizar.setStatus("Fechada");
		pedidoAtualizar.setDataAbertura(dataDeAbertura.getTime());
		pedidoAtualizar.setValorTotal(100.00);
		
		this.dao.update(pedidoAtualizar);
		
		Pedido PedidoAtualizado = this.dao.findById(id);
				
		Assert.assertNotNull(PedidoAtualizado);
		Assert.assertEquals("Fechada", PedidoAtualizado.getStatus());
		Assert.assertEquals(comanda.getId(), PedidoAtualizado.getComanda().getId());
		Assert.assertEquals(garcom.getId(), PedidoAtualizado.getGarcom().getId());
		Assert.assertEquals("15/03/2016 15:20:00", format.format(PedidoAtualizado.getDataAbertura()));
		Assert.assertEquals(new Double(100.00), PedidoAtualizado.getValorTotal());
	}
	
	@Test
	public void testDelete(){

		Pedido pedidoSalvar = new Pedido();
		dataDeAbertura.set(2016, 2, 19, 19, 20, 0);
		
		pedidoSalvar.setStatus("Aberto");
		pedidoSalvar.setComanda(comanda);
		pedidoSalvar.setGarcom(garcom);
		pedidoSalvar.setDataAbertura(dataDeAbertura.getTime());
		pedidoSalvar.setValorTotal(12.90);
		
		
		Long id = this.dao.save(pedidoSalvar);
		
		this.dao.delete(id);
		
		Pedido pedidoDeletado = this.dao.findById(id);
		
		Assert.assertNull(pedidoDeletado);
	}
	
	@Test
	public void testFindAll(){
		Pedido ped1 = new Pedido();
		Pedido ped2 = new Pedido();
		dataDeAbertura.set(2016, 2, 19, 19, 20, 0);

		ped1.setStatus("Aberto");
		ped1.setComanda(comanda);
		ped1.setGarcom(garcom);
		ped1.setDataAbertura(dataDeAbertura.getTime());
		ped1.setValorTotal(12.90);
			
		
		dataDeAbertura.set(2016, 2, 15, 15, 20, 0);
		ped2.setStatus("Fechada");
		ped2.setComanda(comanda);
		ped2.setGarcom(garcom);
		ped2.setDataAbertura(dataDeAbertura.getTime());
		ped2.setValorTotal(100.00);
		
		this.dao.save(ped1);
		this.dao.save(ped2);
		
		List<Pedido> encontrados = this.dao.findAll();
		
		Assert.assertEquals(2, encontrados.size());
		Assert.assertEquals("Aberto", encontrados.get(0).getStatus());
		Assert.assertEquals(comanda.getId(), encontrados.get(0).getComanda().getId());
		Assert.assertEquals(garcom.getId(), encontrados.get(0).getGarcom().getId());
		Assert.assertEquals("19/03/2016 19:20:00", format.format(encontrados.get(0).getDataAbertura()));
		Assert.assertEquals(new Double(12.90), encontrados.get(0).getValorTotal());
		
		Assert.assertEquals("Fechada", encontrados.get(1).getStatus());
		Assert.assertEquals(comanda.getId(), encontrados.get(1).getComanda().getId());
		Assert.assertEquals(garcom.getId(), encontrados.get(1).getGarcom().getId());
		Assert.assertEquals("15/03/2016 15:20:00", format.format(encontrados.get(1).getDataAbertura()));
		Assert.assertEquals(100,00, encontrados.get(1).getValorTotal());
	}

}
