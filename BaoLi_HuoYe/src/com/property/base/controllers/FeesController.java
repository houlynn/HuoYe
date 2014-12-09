package com.property.base.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.hibernate.property.LevelInfo;
import com.model.hibernate.property.ResidentInfo;
import com.ufo.framework.common.constant.RequestPathConstants;
import com.ufo.framework.common.core.ext.model.JSONTreeNode;
import com.ufo.framework.common.core.properties.PropUtil;
import com.ufo.framework.system.ebi.Ebi;
import com.ufo.framework.system.web.SecurityUserHolder;

@Controller
@RequestMapping("/201")
public class FeesController {
	
	
	@Resource(name="ebo")
	private Ebi ebi;

	public Ebi getEbi() {
		return ebi;
	}

	
	@RequestMapping("/loadLR")
	public @ResponseBody List<JSONTreeNode>  getTree(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="vid",required=true) int vid,
			@RequestParam(value="orderSql",required=false,defaultValue=" order by tf_leveId DESC") String orderSql
			){
		List<JSONTreeNode> lists=new ArrayList<JSONTreeNode>();
		try
		{
		List<LevelInfo> leves=(List<LevelInfo>) ebi.queryByHql(" from LevelInfo where 1=1   and  tf_parent=null  and tf_village="+vid+ orderSql);
		for(LevelInfo l:leves){
			JSONTreeNode node=new JSONTreeNode();
			node.setId(l.getTf_leveId()+"tf_leveName");
			node.setText(l.getTf_leveName());
			node.setCode(l.getTf_leveId()+"");
			node.setNodeInfo("LevelInfo");
			node.setCls("tree_set_perm");
			node.setIcon(l.getIcon());
			node.setDescription("tf_leveName");
			node.setExpanded(true);
			node.setLeaf(false);
			node.setNodeInfoType("0");
			if(l.getTf_childs()!=null&&l.getTf_childs().size()>0){
				List<JSONTreeNode> childrens=new ArrayList<>();
				for(LevelInfo childleve :l.getTf_childs() ){
					JSONTreeNode childNode=new JSONTreeNode();
					childNode.setId(childleve.getTf_leveId()+""+"LevelInfo");
					childNode.setText(childleve.getTf_leveName());
					childNode.setCode(childleve.getTf_leveId()+"");
					childNode.setNodeInfo("LevelInfo");
					childNode.setLeaf(false);
					node.setExpanded(true);
					childNode.setIcon(PropUtil.get("sys.leve.LevelInfoChild"));
					childNode.setDescription("tf_leveName");
					childNode.setNodeInfoType("1");
					String hql=" from ResidentInfo where 1=1 and tf_levelInfo="+childleve.getTf_leveId()+ " and xcode='"+SecurityUserHolder.getIdentification()+"'";
					List<ResidentInfo> residents=(List<ResidentInfo>) ebi.queryByHql(hql); 
					List<JSONTreeNode> rchilds=new ArrayList<JSONTreeNode>();
					rchilds=residents.parallelStream().map(item->{
						JSONTreeNode  rnode=new JSONTreeNode();
						rnode.setId(String.valueOf( item.getTf_residentId())+"ResidentInfo");
						rnode.setText("<span style='color:red;font-weight:bold'>"+item.getTf_number()+"</span>" + item.getTf_residentName());
						rnode.setCode(item.getTf_residentId()+"");
						rnode.setNodeInfo("ResidentInfo");
						rnode.setLeaf(true);
						rnode.setIcon(PropUtil.get("sys.rbac.userIcon"));
						rnode.setDescription("tf_residentName");
						rnode.setNodeInfoType("2");
						return rnode;
					}).collect(Collectors.toList());
					childNode.setChildren(rchilds);
					childrens.add(childNode);
					childNode=null;
				}
				node.setChildren(childrens);
			}
			lists.add(node);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lists;
	}
	

	
   private @ResponseBody String  getList(HttpServletRequest request){
    	
	   return null;
    }

}
