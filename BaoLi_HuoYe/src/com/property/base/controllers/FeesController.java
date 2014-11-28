package com.property.base.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.framework.system.ebi.Ebi;

public class FeesController {
	
	
	@Resource(name="ebo")
	private Ebi ebi;

	public Ebi getEbi() {
		return ebi;
	}

	
   private @ResponseBody String  getList(HttpServletRequest request){
    	
	   return null;
    }

}
