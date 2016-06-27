package br.com.fatec.sistemarestaurante.web.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;
import br.com.spektro.minispring.core.liquibaseRunner.LiquibaseRunnerService;

public class SistemaRestauranteListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ContextSpecifier.setContext("br.com.fatec.sistemarestaurante");
		//ConfigDBMapper.setDefaultConnectionName("postgres");
		ConfigDBMapper.setDefaultConnectionName("test");
		LiquibaseRunnerService.run();
	}

}
