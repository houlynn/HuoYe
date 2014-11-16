package com.ufo.framework.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.app.common.ApplicationInfo;
import com.ufo.framework.system.ebi.ApplicationEbi;


@Controller
public class ApplicationController {

	// spring注释，自动注入ApplicationService 的实例
	@Resource
	private ApplicationEbi applicationService;

	@RequestMapping("/applicationinfo")
	public synchronized @ResponseBody
	ApplicationInfo getApplicationInfo(HttpServletRequest request) {
		try {
			return applicationService.getApplicationInfo(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value="/navigatetree/fetchdata",produces = "application/json;text/plain;charset=UTF-8")

	public @ResponseBody String navigatetree(HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8;");
		response.setCharacterEncoding("UTF-8");
		String str="[{\"expanded\":true,\"tooltip\":null,\"fieldname\":null,\"text\":\"省份\",\"fieldvalue\":null,\"isCodeLevel\":null,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Province\",\"count\":13,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_provinceId\",\"text\":\"河北省\",\"fieldvalue\":\"03\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Province\",\"count\":6,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_cityId\",\"text\":\"承德市\",\"fieldvalue\":\"0306\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"City\",\"count\":6,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_customerId\",\"text\":\"承德市避暑山庄有限公司\",\"fieldvalue\":\"9\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Customer\",\"count\":4,\"children\":[],\"nativeValue\":\"9\",\"tag\":-1,\"tableAsName\":\"_t6010\",\"fieldtitle\":\"客户单位\",\"leaf\":true,\"icon\":\"images/module/Customer.png\"},{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_customerId\",\"text\":\"承德市三保制造有限公司\",\"fieldvalue\":\"10\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Customer\",\"count\":2,\"children\":[],\"nativeValue\":\"10\",\"tag\":-1,\"tableAsName\":\"_t6010\",\"fieldtitle\":\"客户单位\",\"leaf\":true,\"icon\":\"images/module/Customer.png\"}],\"nativeValue\":\"0306\",\"tag\":-1,\"tableAsName\":\"_t7012\",\"fieldtitle\":\"市\",\"leaf\":false,\"icon\":\"images/module/City.png\"}],\"nativeValue\":\"03\",\"tag\":-1,\"tableAsName\":\"_t7010\",\"fieldtitle\":\"省份\",\"leaf\":false,\"icon\":\"images/module/Province.png\"},{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_provinceId\",\"text\":\"江苏省\",\"fieldvalue\":\"07\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Province\",\"count\":7,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_cityId\",\"text\":\"无锡市\",\"fieldvalue\":\"0702\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"City\",\"count\":5,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_customerId\",\"text\":\"无锡市太乙公司\",\"fieldvalue\":\"1\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Customer\",\"count\":5,\"children\":[],\"nativeValue\":\"1\",\"tag\":-1,\"tableAsName\":\"_t6010\",\"fieldtitle\":\"客户单位\",\"leaf\":true,\"icon\":\"images/module/Customer.png\"}],\"nativeValue\":\"0702\",\"tag\":-1,\"tableAsName\":\"_t7012\",\"fieldtitle\":\"市\",\"leaf\":false,\"icon\":\"images/module/City.png\"},{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_cityId\",\"text\":\"常州市\",\"fieldvalue\":\"0704\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"City\",\"count\":1,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_customerId\",\"text\":\"无锡市太湖汽车制造公司\",\"fieldvalue\":\"3\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Customer\",\"count\":1,\"children\":[],\"nativeValue\":\"3\",\"tag\":-1,\"tableAsName\":\"_t6010\",\"fieldtitle\":\"客户单位\",\"leaf\":true,\"icon\":\"images/module/Customer.png\"}],\"nativeValue\":\"0704\",\"tag\":-1,\"tableAsName\":\"_t7012\",\"fieldtitle\":\"市\",\"leaf\":false,\"icon\":\"images/module/City.png\"},{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_cityId\",\"text\":\"苏州市\",\"fieldvalue\":\"0705\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"City\",\"count\":1,\"children\":[{\"expanded\":true,\"tooltip\":null,\"fieldname\":\"tf_customerId\",\"text\":\"苏州市虎丘贸易公司\",\"fieldvalue\":\"5\",\"isCodeLevel\":false,\"equalsMethod\":null,\"parentId\":null,\"moduleName\":\"Customer\",\"count\":1,\"children\":[],\"nativeValue\":\"5\",\"tag\":-1,\"tableAsName\":\"_t6010\",\"fieldtitle\":\"客户单位\",\"leaf\":true,\"icon\":\"images/module/Customer.png\"}],\"nativeValue\":\"0705\",\"tag\":-1,\"tableAsName\":\"_t7012\",\"fieldtitle\":\"市\",\"leaf\":false,\"icon\":\"images/module/City.png\"}],\"nativeValue\":\"07\",\"tag\":-1,\"tableAsName\":\"_t7010\",\"fieldtitle\":\"省份\",\"leaf\":false,\"icon\":\"images/module/Province.png\"}],\"nativeValue\":null,\"tag\":-1,\"tableAsName\":\"_t7010\",\"fieldtitle\":null,\"leaf\":false,\"icon\":null}]";
		return str;
	}
}
