package br.com.fatec.sistemarestaurante.web.context;

import java.io.Serializable;
import java.util.List;

import br.com.fatec.sistemarestaurante.api.dto.GarcomDTO;

public class ContextoGarcom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5109235613632772387L;
	
	private GarcomDTO garcom;
	private List<GarcomDTO> garcons;
	
	public GarcomDTO getGarcom() {
		return garcom;
	}
	public void setGarcom(GarcomDTO garcom) {
		this.garcom = garcom;
	}
	public List<GarcomDTO> getGarcons() {
		return garcons;
	}
	public void setGarcons(List<GarcomDTO> garcons) {
		this.garcons = garcons;
	}
	
	

}
