package com.property.base.ebo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



import javax.annotation.Resource;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.stereotype.Service;



import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;



import com.model.hibernate.property.MeterInfo;
import com.model.hibernate.property.ResidentInfo;
import com.property.base.ebi.FeesEbi;
import com.ufo.framework.annotation.FieldInfo;
import com.ufo.framework.common.core.web.SortParameter;
import com.ufo.framework.system.ebi.Ebi;
import com.ufo.framework.system.repertory.SqlModuleFilter;
import com.ufo.framework.system.shared.module.DataFetchResponseInfo;

@Service
public class FeesEbo implements FeesEbi {
	
	
	private static final String  FEES_TYPE_WATER="001";//水费
	private static final String  FEES_TYPE_POWER="002";//电费
	private static final String  FEES_TYPE_COAL="003";//煤气费
	
	
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
		String hql=" from MeterInfo where 1=1 and tf_mtype='"+FEES_TYPE_WATER+"'";
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
			List<MeterInfo> rows= (List<MeterInfo>) ebi.queryByHql(hql+whereSql, start, limit);
			List<Map<String,Object>> views=new ArrayList<>();
			views=rows.parallelStream().map(item->{
				Map<String,Object> itemView=new HashMap<>();
				itemView.put("tf_startnumber", item.getTf_startnumber());
				itemView.put("tf_MeterId", item.getTf_MeterId());
				itemView.put("tf_endnumber", item.getTf_endnumber());
				itemView.put("tf_meterdate", item.getTf_meterdate());
				itemView.put("tf_mtermane", item.getTf_mtermane());
				if(item.getTf_ResidentInfo()!=null){
					itemView.put("tf_ResidentInfo", item.getTf_ResidentInfo().getTf_number()+"  "+item.getTf_ResidentInfo().getTf_residentName());	
				}
				itemView.put("tf_state", item.getTf_state());
				itemView.put("tf_acount", item.getTf_acount());
				itemView.put("tf_remark", item.getTf_remark());
				itemView.put("tf_mtype", FEES_TYPE_WATER);
				return itemView;
			}).collect(Collectors.toList());
			
			Integer count= ebi.getCount(countHql+whereSql);
			responseInfo.setTotalRows(count);
			responseInfo.setMatchingObjects(views);
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
