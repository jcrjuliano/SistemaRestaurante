package br.com.fatec.sistemarestaurante.web.action;



import br.com.fatec.sistemarestaurante.api.dto.GarcomDTO;

import com.opensymphony.xwork2.ActionSupport;



public class TestAction extends ActionSupport {

	
	private GarcomDTO garcom;

	public static final String JSP = "jsp";

	/** */
	private static final long serialVersionUID = 1071989853380980252L;

	public String blah() {
		System.out.println("teste action ok.");
		this.garcom = new GarcomDTO(1l, "Juliano", "123456", "Masculino", "26");
		return JSP;
	}

	public GarcomDTO getGarcom() {
		return this.garcom;
	}

	public void setGarcom(GarcomDTO garcom) {
		this.garcom = garcom;
	}

}
