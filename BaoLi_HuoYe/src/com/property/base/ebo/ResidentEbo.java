package com.property.base.ebo;

import javax.annotation.Resource;

import com.property.base.ebi.ResidentEbi;
import com.ufo.framework.system.ebi.Ebi;

public class ResidentEbo implements ResidentEbi {

	
	@Resource(name="ebo")
	private Ebi ebi;

	public Ebi getEbi() {
		return ebi;
	}

	public void setEbi(Ebi ebi) {
		this.ebi = ebi;
	}
	@Override
	public void settingFeesItem(String  dataStr,int[] ids) throws Exception {
		 
		
		
		

	}

}
