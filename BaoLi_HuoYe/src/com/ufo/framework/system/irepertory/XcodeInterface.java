package com.ufo.framework.system.irepertory;

import com.ufo.framework.system.web.SecurityUserHolder;

public interface XcodeInterface {
	 default String getCurrentXcode() throws Exception{
		 return SecurityUserHolder.getIdentification();
	 }
	 default String getCurrentXcodeSql() throws Exception{
		 return  "and xcode='"+SecurityUserHolder.getIdentification()+"' ";
	 }

}
