package br.com.fatec.sistemarestaurante.test.commons;

import org.junit.BeforeClass;

import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;
import br.com.spektro.minispring.core.liquibaseRunner.LiquibaseRunnerService;

public class TestBase {
	
	@BeforeClass
	public static void setUp(){
		//Seta o contexto, ou seja, classe base da aplicação
		ContextSpecifier.setContext("br.com.fatec.sistemarestaurante");
		
		//Você diz qual será o banco utilizado.
		ConfigDBMapper.setDefaultConnectionName("test");
		
		//Descobre onde ta o arquivo changelog.master e executa usando api do liquibase.
		LiquibaseRunnerService.run();
	}
	

}
