package com.property.base.controllers;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.hibernate.property.LevelInfo;
import com.model.hibernate.property.Village;
import com.ufo.framework.common.core.exception.InsertException;
import com.ufo.framework.common.core.exception.ResponseErrorInfo;
import com.ufo.framework.common.core.ext.model.JSONTreeNode;
import com.ufo.framework.common.core.properties.PropUtil;
import com.ufo.framework.common.core.utils.StringUtil;
import com.ufo.framework.common.log.LogerManager;
import com.ufo.framework.system.ebi.Ebi;
import com.ufo.framework.system.ebi.ModelEbi;
import com.ufo.framework.system.ebo.ModuleService;
import com.ufo.framework.system.shared.module.DataInsertResponseInfo;
@Controller
@RequestMapping("/102")
public class ResidentController implements LogerManager {
	
	private final static String REQUEST_INSERTPATH="/A001";
	private final static String REQUEST_UPDATEPATH="U001";
	private final static String REQUEST_DELETEPATH="D001";
	private final static String REQUEST_LOADPATH="L001";
	
	
	@Resource(name="ebo")
	private Ebi ebi;

	public Ebi getEbi() {
		return ebi;
	}

	public void setEbi(Ebi ebi) {
		this.ebi = ebi;
	}
	
	@Resource
	private ModelEbi moduleService;
	
	
	public ModelEbi getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModelEbi moduleService) {
		this.moduleService = moduleService;
	}

	@RequestMapping("/getTree")
	public @ResponseBody List<JSONTreeNode>  getTree(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="vid",required=true) int vid,
			@RequestParam(value="orderSql",required=false,defaultValue=" order by tf_leveId DESC") String orderSql
			){
		List<JSONTreeNode> lists=new ArrayList<JSONTreeNode>();
		try
		{
		List<LevelInfo> leves=(List<LevelInfo>) ebi.queryByHql(" from LevelInfo where 1=1 and tf_village="+vid+ orderSql);
		for(LevelInfo l:leves){
			JSONTreeNode node=new JSONTreeNode();
			node.setId(l.getTf_leveId()+"");
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
	
	@RequestMapping(REQUEST_INSERTPATH)
	public @ResponseBody DataInsertResponseInfo add(@RequestParam(value="vid",required=false) int vid,@RequestParam(value="leveName",required=true) String leveName ) throws Exception{
		DataInsertResponseInfo result = null;
	
			 if(StringUtil.isEmpty(vid+"")){
				 getInsertException("LevelInfo","小区ID为空，添加失败!",ResponseErrorInfo.STATUS_VALIDATION_ERROR);
			 }else{
				 LevelInfo info=new LevelInfo();
				 Village village=new Village();
				 village.setTf_viid(vid);
				 info.setTf_village(village);
				 info.setTf_leveName(leveName);
				 try {
					ebi.save(info);
				} catch (Exception e) {
					error("添加异常", e);
					// TODO Auto-generated catch block
					getInsertException("LevelInfo","添加楼宇失败!",ResponseErrorInfo.STATUS_FAILURE);
				}
			 }
		return result;
	}
	
	
	
	public void getInsertException(String modeuName,String msg,int code) throws InsertException{
		 InsertException exception=	 new InsertException(); 
		 ResponseErrorInfo errorInfo= new ResponseErrorInfo();
		 errorInfo.setModueName("LevelInfo");
		 errorInfo.getErrorMessage().put("error", msg);
		 errorInfo.setResultCode(code);
		 exception.setErrorInfo(errorInfo);
		 throw exception;
		 
	}
	public void getInsertException(String modeuName,String msg,int code,Exception e) throws InsertException{
		 InsertException exception=	 new InsertException(e); 
		 ResponseErrorInfo errorInfo= new ResponseErrorInfo();
		 errorInfo.setModueName("LevelInfo");
		 errorInfo.getErrorMessage().put("error", msg);
		 errorInfo.setResultCode(code);
		 exception.setErrorInfo(errorInfo);
		 throw exception;
		 
	}
	
	
}
	
	

