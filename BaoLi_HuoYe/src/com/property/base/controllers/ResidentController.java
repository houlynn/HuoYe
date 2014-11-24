package com.property.base.controllers;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.hibernate.property.LevelInfo;
import com.ufo.framework.common.core.ext.model.JSONTreeNode;
import com.ufo.framework.system.ebi.Ebi;
@Controller
@RequestMapping("/102")
public class ResidentController {
	
	@Resource(name="ebo")
	private Ebi ebi;

	public Ebi getEbi() {
		return ebi;
	}

	public void setEbi(Ebi ebi) {
		this.ebi = ebi;
	}
	
	@RequestMapping("/getTree")
	public @ResponseBody List<JSONTreeNode>  getTree(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="vid",required=true) int vid,
			@RequestParam(value="orderSql",required=false,defaultValue=" order by tf_leveId DESC") String orderSql
			){
		List<JSONTreeNode> lists=new ArrayList<JSONTreeNode>();
		try
		{
		List<LevelInfo> leves=(List<LevelInfo>) ebi.queryByHql(" from LevelInfo where 1=1 and tf_leveId="+vid+ orderSql);
		for(LevelInfo l:leves){
			JSONTreeNode node=new JSONTreeNode();
			node.setId(l.getTf_leveId());
			node.setText(l.getTf_leveName());
			node.setCode(l.getTf_village().getTf_viid()+"");
			node.setNodeInfo("LevelInfo");
			node.setIcon(l.getIcon());
			//node.setOrderIndex(r.getOrderIndex());
			lists.add(node);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lists;
	}
	
	public @ResponseBody addLevelInfo(@RequestParam(value="vid",required=true) int vid,@RequestParam(value="leveName",required=true) String leveName){
		
		
		
		
		
	}
	
	
	
	
}
	
	

