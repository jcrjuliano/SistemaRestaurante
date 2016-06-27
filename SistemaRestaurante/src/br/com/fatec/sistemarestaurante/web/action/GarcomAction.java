package br.com.fatec.sistemarestaurante.web.action;

import br.com.fatec.sistemarestaurante.api.dto.GarcomDTO;
import br.com.fatec.sistemarestaurante.api.service.GarcomService;
import br.com.fatec.sistemarestaurante.web.context.ContextoGarcom;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class GarcomAction extends SistemaRestauranteWebAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2997459982939742190L;
	private static final String DEU_CERTO = "foi";
	
	
	private ContextoGarcom contexto = new ContextoGarcom();
	private GarcomService service;
	
	public GarcomAction(){
		this.service = ImplFinder.getImpl(GarcomService.class);
	}
	
	public String listar(){
		this.contexto.setGarcons(this.service.listar());
		return DEU_CERTO;
	}
	
	public String salvar(){
		if(this.contexto.getGarcom().getId() != null){
			this.service.atualizar(this.contexto.getGarcom());
		} else {
			this.service.salvar(this.contexto.getGarcom());
		}
		return this.listar();
	}
	
	public String editar(){
		GarcomDTO garcom = this.service.findById(this.contexto.getGarcom().getId());
		this.contexto.setGarcom(garcom);
		return this.listar();
	}
	public String deletar(){
		this.service.deletar(this.contexto.getGarcom().getId());
		return this.listar();
	}

	public ContextoGarcom getContexto() {
		return contexto;
	}

	public void setContexto(ContextoGarcom contexto) {
		this.contexto = contexto;
	}

}
