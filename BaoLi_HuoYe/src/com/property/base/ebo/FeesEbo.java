package com.property.base.ebo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import com.property.base.ebi.FeesEbi;
import com.ufo.framework.common.core.web.SortParameter;
import com.ufo.framework.system.ebi.Ebi;
import com.ufo.framework.system.repertory.SqlModuleFilter;
import com.ufo.framework.system.shared.module.DataFetchResponseInfo;

@Service
public class FeesEbo implements FeesEbi {
	
	@Resource(name="ebo")
	private Ebi ebi;

	public Ebi getEbi() {
		return ebi;
	}
	
	public  DataFetchResponseInfo fetchData(String moduleName, Integer start, Integer limit, String sort,String query, String navigates,String nodeInfoType
			) {
		DataFetchResponseInfo responseInfo=new DataFetchResponseInfo();
		SortParameter sorts[] = SortParameter.changeToSortParameters(sort);
		List<SqlModuleFilter> navigateFilters=  changeToNavigateFilters(navigates);
		String hql=" from MeterInfo where 1=1 ";
		String whereSql="";
		if("0".equals(nodeInfoType)){
			SqlModuleFilter nav=navigateFilters.get(0);
			whereSql+=" and  tf_ResidentInfo.tf_levelInfo.tf_parent="+nav.getEqualsValue();
		}else if("1".equals(nodeInfoType)){
			SqlModuleFilter nav=navigateFilters.get(0);
			whereSql+=" and  tf_ResidentInfo.tf_levelInfo="+nav.getEqualsValue();
		}else if("2".equals(nodeInfoType)){
			SqlModuleFilter nav=navigateFilters.get(0);
			whereSql+=" and  tf_ResidentInfo="+nav.getEqualsValue();
		}
		
		String countHql = "select count(*) from "
				+ moduleName + " where 1=1 ";
		
		  try {
			List<?> rows= ebi.queryByHql(hql+whereSql, start, limit);
			Integer count= ebi.getCount(countHql+whereSql);
			
			
			
			
			responseInfo.setTotalRows(count);
			responseInfo.setMatchingObjects(rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseInfo;
		
		
	}
	private List<SqlModuleFilter> changeToNavigateFilters(String str) {
		List<SqlModuleFilter> result = new ArrayList<SqlModuleFilter>();
		if (str != null && str.length() > 5) {
			JsonConfig config = new JsonConfig();
			config.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
			config.setRootClass(SqlModuleFilter.class);
			SqlModuleFilter[] navigateFilters = (SqlModuleFilter[]) JSONSerializer.toJava(
					JSONArray.fromObject(str), config);
			// System.out.println(navigateFilters[0]);
			for (SqlModuleFilter f : navigateFilters)
				result.add(f);
		}
		result.parallelStream().forEach(item->System.out.println(item.getFilterSql()));
		return result;

	}


}
