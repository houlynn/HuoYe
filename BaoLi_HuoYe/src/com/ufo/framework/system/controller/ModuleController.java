package com.ufo.framework.system.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.hibernate.system._Module;
import com.ufo.framework.annotation.TableInfo;
import com.ufo.framework.annotation.TreeItemName;
import com.ufo.framework.annotation.TreeItemValue;
import com.ufo.framework.common.constant.CommConstants;
import com.ufo.framework.common.core.exception.DeleteException;
import com.ufo.framework.common.core.exception.InsertException;
import com.ufo.framework.common.core.exception.ResponseErrorInfo;
import com.ufo.framework.common.core.utils.EntityUtil;
import com.ufo.framework.common.core.utils.StringUtil;
import com.ufo.framework.common.core.web.ModuleServiceFunction;
import com.ufo.framework.common.log.LogerManager;
import com.ufo.framework.common.model.Model;
import com.ufo.framework.system.ebi.Ebi;
import com.ufo.framework.system.ebi.ModelEbi;
import com.ufo.framework.system.ebo.ApplicationService;
import com.ufo.framework.system.ebo.ModuleService;
import com.ufo.framework.system.irepertory.IModelRepertory;
import com.ufo.framework.system.model.ui.JSONTreeNode;
import com.ufo.framework.system.repertory.SqlModuleFilter;
import com.ufo.framework.system.repertory.SystemBaseDAO;
import com.ufo.framework.system.shared.module.DataDeleteResponseInfo;
import com.ufo.framework.system.shared.module.DataFetchResponseInfo;
import com.ufo.framework.system.shared.module.DataInsertResponseInfo;
import com.ufo.framework.system.shared.module.DataUpdateResponseInfo;
@Controller
@RequestMapping(value = "/module")
/**
 * 所有模块的的ＣＲＵＤ都是调用这个类的函数来完成的Controller里面完成的
 *
* @author HouLynn
* @date 2014年10月21日
  @version 1.0
 */
public class ModuleController implements LogerManager {

	public ModuleController() {
		debug(this.getClass().getName());
	}

	@Resource
	private SystemBaseDAO systemBaseDAO;

	@Resource
	private ModelEbi moduleService;

	@Resource
	private IModelRepertory moduleDAO;
	
	@Resource(name="ebo")
	private Ebi ebi;

	private static final Log log = LogFactory.getLog(ModuleController.class);

	/**
	 * 根据前台的请求取得数据
	 */
	@RequestMapping(value = "/fetchdata", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> fetchData(String moduleName, Integer start, Integer limit, String sort,
			String query, String columns, String navigates, String parentFilter,
			HttpServletRequest request) {
		DataFetchResponseInfo response = moduleService.fetchDataInner(moduleName, start, limit, sort,
				query, columns, navigates, parentFilter, (SqlModuleFilter) null, request);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("records", response.getMatchingObjects());
		result.put("totalCount", response.getTotalRows());
		return result;
	}

	/**
	 * 新增记录的时候，在后台取得缺省值
	 * 
	 * @param moduleName
	 * @param parentFilter
	 * @param navigates
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/getnewdefault.do", method = RequestMethod.POST)
	public @ResponseBody
	Object getRecordNewDefault(String moduleName, String parentFilter, String navigates,
			HttpServletRequest request) {

		return moduleService.getRecordNewDefault(moduleName, parentFilter, navigates, request);

	}

	@RequestMapping(value = "/fetchdata.do/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Object getRecordById(String moduleName, @PathVariable("id") String id, HttpServletRequest request) {
		log.debug("根据主键取得模块的一条记录:" + moduleName + "," + id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("totalCount", 1);
		List<Object> records = new ArrayList<Object>();
		try {
			records.add(moduleDAO.getModuleRecord(moduleName, id, request).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("records", records);
		log.debug("getRecordById返回值：" + result.toString());
		return result;
	}
	

	@RequestMapping(value = "/create.do/{id}", method = RequestMethod.POST)
	public @ResponseBody
	DataInsertResponseInfo add(String moduleName, @RequestBody String inserted,
			HttpServletRequest request) throws Exception {
		DataInsertResponseInfo result =new DataInsertResponseInfo();
		 String parentFilter=request.getParameter("parentFilter");
		 String navigates=request.getParameter("navigates");
		 _Module module=ApplicationService.getModuleWithName(moduleName);
		try {
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
	
	

	/***
	 * 创建一条记录
	 * 
	 * @param moduleName
	 * @param inserted
	 * @param request
	 * @return
	 * @throws Exception 
	 * */
	
	@RequestMapping(value = "/create.do", method = RequestMethod.POST)
	public @ResponseBody
	DataInsertResponseInfo addWithNoPrimaryKey(String moduleName,   @RequestBody String inserted,
			HttpServletRequest request) throws Exception {
		return add(moduleName, inserted ,request);
	}

/*	@RequestMapping(value = "/create.do/{id}", method = RequestMethod.POST)
	public @ResponseBody
	DataInsertResponseInfo add(String moduleName, @RequestBody String inserted,
			HttpServletRequest request) {
		DataInsertResponseInfo result = null;
		 String parentFilter=request.getParameter("parentFilter");
		 String navigates=request.getParameter("navigates");
		 System.out.println("naviegie :"+navigates);
		try {
			result = moduleService.add(moduleName, inserted, parentFilter,navigates, request);
			if (result.getKey() != null) // 如果是空的话，那么就没有保存，错误原因已经在errorMessage里了
				result.getRecords().add(
						moduleDAO.getModuleRecord(moduleName, result.getKey(), request).toString());
		} catch (DataAccessException e) {
			e.printStackTrace();
			if (result == null)
				result = new DataInsertResponseInfo();
			result.setResultCode(ModuleService.STATUS_VALIDATION_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			if (result == null)
				result = new DataInsertResponseInfo();
			result.getErrorMessage().put("error", e.getMessage());
			result.setResultCode(ModuleService.STATUS_FAILURE);
		}
		return result;
	}*/

	/**
	 * 修改记录
	 * 
	 * @param moduleName
	 * @param id
	 * @param oldid
	 * @param operType
	 * @param updated
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update.do/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	DataUpdateResponseInfo update(String moduleName, @PathVariable("id") String id, String oldid,
			String operType, @RequestBody String updated, HttpServletRequest request) {

		System.out.println(updated);
		DataUpdateResponseInfo result = null;
		_Module module = ApplicationService.getModuleWithName(moduleName);
		// 如果主键值修改了，那么先进行主键的修改
		if (oldid != null && (!oldid.equals(id))) {
			try {
				result = moduleService.changeRecordId(moduleName, id, oldid);
			} catch (ConstraintViolationException e) {
				e.printStackTrace();
				result = new DataUpdateResponseInfo();
				if (e.getCause().toString().toLowerCase().indexOf("primary key") >= 0)
					result.getErrorMessage().put(module.getTf_primaryKey(), "修改过后的主键与原有的主键值重复！");
				else
					result.getErrorMessage().put(module.getTf_primaryKey(), "当前主键与子模块有约束关系，不可以修改！");
				result.setResultCode(ModuleService.STATUS_VALIDATION_ERROR);
			}
			if (!result.getResultCode().equals(0))
				return result;
		}
		// 修改记录
		try {
			result = moduleService.update(moduleName, id, operType, updated,request);
			result.getRecords().add(moduleDAO.getModuleRecord(moduleName, id, request).toString());
		} catch (DataAccessException e) {
			result = new DataUpdateResponseInfo();
			result.setResultCode(ModuleService.STATUS_VALIDATION_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			result = new DataUpdateResponseInfo();
			result.getErrorMessage().put("error", e.getMessage());
			result.setResultCode(ModuleService.STATUS_FAILURE);
		}
		return result;
	}

	/**
	 * 删除记录
	 * 
	 * @param moduleName
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove.do/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	DataDeleteResponseInfo remove(String moduleName, @PathVariable String id,
			HttpServletRequest request) {
		DataDeleteResponseInfo result = null;
		try {
			result = moduleService.remove(moduleName, id, request);
		} catch (DataAccessException e) {
			result = new DataDeleteResponseInfo();
			String errormessage = ModuleServiceFunction.addPK_ConstraintMessage(e, moduleName);
			result.setResultMessage(-1, errormessage != null ? errormessage
					: "请检查与本记录相关联的其他数据是否全部清空！<br/>");
		} catch (Exception e) {
			result = new DataDeleteResponseInfo();
			result.setResultMessage(-1, e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/removerecords.do")
	public @ResponseBody
	DataDeleteResponseInfo removeRecords(String moduleName, String ids, String titles,
			HttpServletRequest request) {
		DataDeleteResponseInfo result = null;
		String[] idarray = ids.split(",");
		String[] titlearray = titles.split("~~");

		result = new DataDeleteResponseInfo();

		for (int i = 0; i < idarray.length; i++) {
			try {
				DataDeleteResponseInfo recordDeleteResult = moduleService.remove(moduleName, idarray[i],
						request);
				if (recordDeleteResult.getResultCode() == 0)

					result.getOkMessageList().add(titlearray[i]);
				else {
					if (recordDeleteResult.getErrorMessageList().size() > 0)
						result.getErrorMessageList().add(
								"【" + titlearray[i] + "】" + recordDeleteResult.getErrorMessageList().get(0));
					else
						result.getErrorMessageList().add("【" + titlearray[i] + "】" + "未知错误！");
				}
			} catch (DataAccessException e) {
				String errormessage = ModuleServiceFunction.addPK_ConstraintMessage(e, moduleName);

				result.getErrorMessageList().add(
						"【" + titlearray[i] + "】" + (errormessage != null ? errormessage : "关联的数据未清空！"));
			} catch (Exception e) {
				result.getErrorMessageList().add("【" + titlearray[i] + "】" + e.getMessage());
			}
		}
		result.setResultCode(result.getErrorMessageList().size());
		return result;
	}
	
	
	
	
	@RequestMapping(value="/navigatetree/fetchdata",produces = "application/json;text/plain;charset=UTF-8")
	public @ResponseBody List<JSONTreeNode> navigatetree(HttpServletResponse response,
			 	String moduleName,
			 	String navigatePath,
			 	String reverseOrder,
			 	String type,
			    String parentFilter,
			 	String node,
				@RequestParam(value="whereSql",required=false,defaultValue="") String whereSql,
		    	@RequestParam(value="parentSql",required=false,defaultValue="") String parentSql,
		    	@RequestParam(value="querySql",required=false,defaultValue="") String querySql,
		    	@RequestParam(value="orderSql",required=false,defaultValue="") String orderSql
			){
		
		List<JSONTreeNode> views=new ArrayList<>();
		List<String> modues=new ArrayList<>();
		try{
		if(StringUtil.isNotEmpty(navigatePath)&&navigatePath.contains("--")){
			String[] modueStrs=navigatePath.split("--");
			modues=Arrays.asList(modueStrs);
		}else{
			modues.add(navigatePath);
		}
		for(String modue :modues){
			JSONTreeNode titleNode=new JSONTreeNode();
			titleNode.setNodeType(CommConstants.NODE_TYPE_FIELDTITLE);//isCodeLevel
			Class<? extends Model> clazz=(Class<? extends Model>) ModuleServiceFunction.getModuleBeanClass(modue);
			String tilte="";
			String fieldname="";
			String fieldvalue="";
			
			if(clazz.isAnnotationPresent(TableInfo.class)){
				TableInfo tableInfo=clazz.getAnnotation(TableInfo.class);
				tilte=tableInfo.title();
			}
			 List<Field> listFields=clazz.newInstance().fielsColection(new ArrayList<Field>());
			 List<Field> listFieldValue=listFields.parallelStream().filter(o->o.isAnnotationPresent(TreeItemValue.class)).collect(Collectors.toList());
			 List<Field> listFieldVaKey=listFields.parallelStream().filter(o->o.isAnnotationPresent(TreeItemName.class)).collect(Collectors.toList());
			tilte=StringUtil.isEmpty(tilte)?"未定义":	tilte;
			titleNode.setText(tilte);
			titleNode.setNodeInfo(modue);
			titleNode.setExpanded(true);
			String hql=" from "+clazz.getSimpleName()+whereSql+parentSql+querySql+orderSql;
			List<?> listItems=ebi.queryByHql(hql);
			String fieldValue=listFieldValue.get(0).getName();
			String fieldName=listFieldVaKey.get(0).getName();
			 List<JSONTreeNode> children=listItems.parallelStream().map(item->{
				JSONTreeNode treeNode=new JSONTreeNode();
				Object  fieldnameV=EntityUtil.getPropertyValue(item,fieldName);
				Object fieldvalueV= EntityUtil.getPropertyValue(item,fieldValue);
				treeNode.setText(fieldnameV+"");//text
				treeNode.setCode(fieldvalueV+"");//val
				treeNode.setExpanded(true);
				treeNode.setNodeInfo(modue); //modue 
				treeNode.setCount(100);
				treeNode.setDescription(fieldName);
				treeNode.setNodeInfoType(CommConstants.NODE_TYPE_FIELDQUERY);//isCodeLevel
				return treeNode;
			}).collect(Collectors.toList());
			 titleNode.setChildren(children);
			 views.add(titleNode);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			//code  fieldvalue     desciton fieldname 
			
	
		
		
	/*	List<DictionaryItem> items=new ArrayList<>();
		Class<? extends Model> clazz= (Class<? extends Model>) Class.forName(modelName);
		 List<Field> listFields=clazz.newInstance().fielsColection(new ArrayList<Field>());
		 DictionaryItem item=null;
		 
		 List<?> list=ebi.queryByHql(" from "+clazz.getSimpleName()+" where enabled='1' "+wherSql);
		 System.out.println("获取到：："+modelName+list.size());
		 Field codeField =listFields.parallelStream().filter(o->o.isAnnotationPresent(DDItemCode.class)).collect(Collectors.toList()).get(0);
		 listFields.remove(codeField);
		 listFields=listFields.parallelStream().filter(o->o.isAnnotationPresent(DDItemName.class)).collect(Collectors.toList());
		 for(Object o : list)
		 {
			item=new DictionaryItem();
			String fieldName=codeField.getName();
			String itemCode=(String) EntityUtil.getPropertyValue(o,fieldName);
			item.setItemCode(itemCode);
			String itemName="";
			for(Field f : listFields){
				if(listFields.size()==1){
					itemName=(String) EntityUtil.getPropertyValue(o,f.getName());
				}else{
				 itemName+=(String) EntityUtil.getPropertyValue(o,f.getName())+"  ";
				}
			}
			item.setItemName(itemName);
			items.add(item);
			item=null;
		 }
		 return items;
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//response.setContentType("text/html;charset=UTF-8;");
		//response.setCharacterEncoding("UTF-8");
		//String str="[{\"expanded\":true,\"tooltip\":null,\"fieldname\":null,\"text\":\"省份\",\"fieldvalue\":null,\"isCodeLevel\":null,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Province\",\"count\":13,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_provinceId\",\"text\":\"河北省\",\"fieldvalue\":\"03\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Province\",\"count\":6,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_cityId\",\"text\":\"承德市\",\"fieldvalue\":\"0306\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"City\",\"count\":6,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_customerId\",\"text\":\"承德市避暑山庄有限公司\",\"fieldvalue\":\"9\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Customer\",\"count\":4,\"children\":[],\"nativeValue\":\"9\",\"tag\":-1,\"tableAsName\":\"_t6010\",\"fieldtitle\":\"客户单位\",\"leaf\":true,\"icon\":\"images/module/Customer.png\"},{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_customerId\",\"text\":\"承德市三保制造有限公司\",\"fieldvalue\":\"10\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Customer\",\"count\":2,\"children\":[],\"nativeValue\":\"10\",\"tag\":-1,\"tableAsName\":\"_t6010\",\"fieldtitle\":\"客户单位\",\"leaf\":true,\"icon\":\"images/module/Customer.png\"}],\"nativeValue\":\"0306\",\"tag\":-1,\"tableAsName\":\"_t7012\",\"fieldtitle\":\"市\",\"leaf\":false,\"icon\":\"images/module/City.png\"}],\"nativeValue\":\"03\",\"tag\":-1,\"tableAsName\":\"_t7010\",\"fieldtitle\":\"省份\",\"leaf\":false,\"icon\":\"images/module/Province.png\"},{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_provinceId\",\"text\":\"江苏省\",\"fieldvalue\":\"07\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Province\",\"count\":7,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_cityId\",\"text\":\"无锡市\",\"fieldvalue\":\"0702\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"City\",\"count\":5,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_customerId\",\"text\":\"无锡市太乙公司\",\"fieldvalue\":\"1\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Customer\",\"count\":5,\"children\":[],\"nativeValue\":\"1\",\"tag\":-1,\"tableAsName\":\"_t6010\",\"fieldtitle\":\"客户单位\",\"leaf\":true,\"icon\":\"images/module/Customer.png\"}],\"nativeValue\":\"0702\",\"tag\":-1,\"tableAsName\":\"_t7012\",\"fieldtitle\":\"市\",\"leaf\":false,\"icon\":\"images/module/City.png\"},{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_cityId\",\"text\":\"常州市\",\"fieldvalue\":\"0704\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"City\",\"count\":1,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_customerId\",\"text\":\"无锡市太湖汽车制造公司\",\"fieldvalue\":\"3\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Customer\",\"count\":1,\"children\":[],\"nativeValue\":\"3\",\"tag\":-1,\"tableAsName\":\"_t6010\",\"fieldtitle\":\"客户单位\",\"leaf\":true,\"icon\":\"images/module/Customer.png\"}],\"nativeValue\":\"0704\",\"tag\":-1,\"tableAsName\":\"_t7012\",\"fieldtitle\":\"市\",\"leaf\":false,\"icon\":\"images/module/City.png\"},{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_cityId\",\"text\":\"苏州市\",\"fieldvalue\":\"0705\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"City\",\"count\":1,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_customerId\",\"text\":\"苏州市虎丘贸易公司\",\"fieldvalue\":\"5\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Customer\",\"count\":1,\"children\":[],\"nativeValue\":\"5\",\"tag\":-1,\"tableAsName\":\"_t6010\",\"fieldtitle\":\"客户单位\",\"leaf\":true,\"icon\":\"images/module/Customer.png\"}],\"nativeValue\":\"0705\",\"tag\":-1,\"tableAsName\":\"_t7012\",\"fieldtitle\":\"市\",\"leaf\":false,\"icon\":\"images/module/City.png\"}],\"nativeValue\":\"07\",\"tag\":-1,\"tableAsName\":\"_t7010\",\"fieldtitle\":\"省份\",\"leaf\":false,\"icon\":\"images/module/Province.png\"}],\"nativeValue\":null,\"tag\":-1,\"tableAsName\":\"_t7010\",\"fieldtitle\":null,\"leaf\":false,\"icon\":null}]";
		return views;
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
