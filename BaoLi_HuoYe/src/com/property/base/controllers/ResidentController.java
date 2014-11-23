package com.property.base.controllers;

import javax.annotation.Resource;

import com.ufo.framework.system.ebi.Ebi;

public class ResidentController {
	
	@Resource(name="ebo")
	private Ebi ebi;

	public Ebi getEbi() {
		return ebi;
	}

	public void setEbi(Ebi ebi) {
		this.ebi = ebi;
	}
	
	
	//public void
	
	
	
	

}
