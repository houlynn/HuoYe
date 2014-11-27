package com.property.base.controllers;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.hibernate.property.LevelInfo;
import com.model.hibernate.property.ResidentInfo;
import com.model.hibernate.property.Village;
import com.model.hibernate.system._Module;
import com.ufo.framework.common.constant.RequestPathConstants;
import com.ufo.framework.common.core.exception.DeleteException;
import com.ufo.framework.common.core.exception.InsertException;
import com.ufo.framework.common.core.exception.ResponseErrorInfo;
import com.ufo.framework.common.core.ext.model.JSONTreeNode;
import com.ufo.framework.common.core.properties.PropUtil;
import com.ufo.framework.common.core.utils.StringUtil;
import com.ufo.framework.common.core.web.ModuleServiceFunction;
import com.ufo.framework.common.log.LogerManager;
import com.ufo.framework.system.ebi.Ebi;
import com.ufo.framework.system.ebi.ModelEbi;
import com.ufo.framework.system.ebo.ApplicationService;
import com.ufo.framework.system.ebo.ModuleService;
import com.ufo.framework.system.irepertory.IModelRepertory;
import com.ufo.framework.system.shared.module.DataDeleteResponseInfo;
import com.ufo.framework.system.shared.module.DataInsertResponseInfo;
@Controller
@RequestMapping("/102")
public class ResidentController implements LogerManager {
	@Resource(name="ebo")
	private Ebi ebi;

	public Ebi getEbi() {
		return ebi;
	}

	public void setEbi(Ebi ebi) {
		this.ebi = ebi;
	}
	
	@Resource
	private IModelRepertory moduleDAO;
	@Resource
	private ModelEbi moduleService;
	
	
	public ModelEbi getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModelEbi moduleService) {
		this.moduleService = moduleService;
	}

	@RequestMapping(RequestPathConstants.REQUEST_LOADPATH)
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
			node.setCode(l.getTf_leveId()+"");
			node.setNodeInfo("LevelInfo");
			node.setIcon(l.getIcon());
			node.setDescription("tf_leveName");
			node.setCount(100);
			lists.add(node);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lists;
	}
	
	@RequestMapping(RequestPathConstants.REQUEST_INSERTPATH)
	public @ResponseBody DataInsertResponseInfo add(@RequestParam(value="vid",required=true) int vid,@RequestParam(value="leveName",required=true) String leveName ) throws Exception{
		DataInsertResponseInfo result =new DataInsertResponseInfo();
	
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
		return result;
	}
	
	@RequestMapping(RequestPathConstants.REQUEST_DELETEPATH)
  	public @ResponseBody  DataDeleteResponseInfo remove(@RequestParam(value="tf_leveId",required=true) int tf_leveId,HttpServletRequest request) throws Exception {
		     DataDeleteResponseInfo result=new DataDeleteResponseInfo();
					try {
						result = moduleService.remove("LevelInfo", tf_leveId+"", request);
					} 
					catch (DataIntegrityViolationException e) {
						getDeleteException("LevelInfo", "请检查与本记录相关联的其他数据是否全部清空！", ResponseErrorInfo.STATUS_FAILURE, e);
						error("删除异常", e);
					} 
					catch (DataAccessException e) {
						String errormessage = ModuleServiceFunction.addPK_ConstraintMessage(e, "LevelInfo");
						getDeleteException("LevelInfo",  errormessage != null ? errormessage
								: "请检查与本记录相关联的其他数据是否全部清空！<br/>", ResponseErrorInfo.STATUS_FAILURE, e);
					} catch (Exception e) {
						error("删除异常", e);
						// TODO Auto-generated catch block
						getDeleteException("LevelInfo", " 删除楼宇失败!", ResponseErrorInfo.STATUS_FAILURE, e);
					}
					return result;
			 }
	
	
/*	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody
	DataInsertResponseInfo add(String moduleName, @RequestBody String inserted,
			HttpServletRequest request) throws Exception {
		DataInsertResponseInfo result =new DataInsertResponseInfo();
		 String parentFilter=request.getParameter("parentFilter");
		 String navigates=request.getParameter("navigates");
		 _Module module=ApplicationService.getModuleWithName(moduleName);
		try {
			JSONObject updateJsonObject = JSONObject.fromObject(inserted);
			ResidentInfo record=new ResidentInfo();
			moduleDAO.updateValueToBean(moduleName, record, updateJsonObject);
			String hql="  "
			record.seTf_residentId(record.getTf_levelInfo().getTf_leveId())
			
		
			
			
			result = moduleService.add(moduleName, inserted, parentFilter,navigates, request);
			if (result.getKey() != null) // 如果是空的话，那么就没有保存，错误原因已经在errorMessage里了
				result.getRecords().add(
						moduleDAO.getModuleRecord(moduleName, result.getKey(), request).toString());
		
			
			
			
			
			
			
			
			
		} catch (DataAccessException e) {
		      error("DataAccessException异常", e);
			if (e.getRootCause().getMessage().toLowerCase().indexOf("primary") != -1){
				getInsertException(module.getTf_moduleName(),module.getTf_primaryKey() +"插入记录的主键值与数据库中原有的值重复!",ResponseErrorInfo.STATUS_VALIDATION_ERROR);
		}else{
			
			getInsertException(module.getTf_moduleName(),e.getMessage(),ResponseErrorInfo.STATUS_VALIDATION_ERROR);
		}
	}catch (Exception e) {
		error("添加异常", e);
		// TODO Auto-generated catch block
		getInsertException(moduleName,"添加业主信息失败!",ResponseErrorInfo.STATUS_FAILURE);
	}
		
		return result;
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
		 errorInfo.setModueName(modeuName);
		 errorInfo.getErrorMessage().put("error", msg);
		 errorInfo.setResultCode(code);
		 exception.setErrorInfo(errorInfo);
		 throw exception;
		 
	}
	
	public void getDeleteException(String modeuName,String msg,int code) throws InsertException{
		 InsertException exception=	 new InsertException(); 
		 ResponseErrorInfo errorInfo= new ResponseErrorInfo();
		 errorInfo.setModueName(modeuName);
		 errorInfo.getErrorMessage().put("error", msg);
		 errorInfo.setResultCode(code);
		 exception.setErrorInfo(errorInfo);
		 throw exception;
		 
	}
	public void getDeleteException(String modeuName,String msg,int code,Exception e) throws DeleteException{
		DeleteException exception=	 new DeleteException(e); 
		 ResponseErrorInfo errorInfo= new ResponseErrorInfo();
		 errorInfo.setModueName(modeuName);
		 errorInfo.getErrorMessage().put("error", msg);
		 errorInfo.setResultCode(code);
		 exception.setErrorInfo(errorInfo);
		 throw exception;
		 
	}
	
	
}
	
	

