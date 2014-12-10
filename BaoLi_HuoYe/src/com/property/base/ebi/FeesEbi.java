package com.property.base.ebi;

import com.ufo.framework.system.shared.module.DataFetchResponseInfo;

public interface FeesEbi {
	public  DataFetchResponseInfo fetchData(String moduleName, Integer start, Integer limit, String sort,String query, String navigates,String nodeInfoType);
}
