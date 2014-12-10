package com.ufo.framework.system.controller;

import com.ufo.framework.common.core.exception.DeleteException;
import com.ufo.framework.common.core.exception.InsertException;
import com.ufo.framework.common.core.exception.ResponseErrorInfo;

public class BasisController {

	public void getInsertException(String modeuName,String msg,int code) throws InsertException{
		 InsertException exception=	 new InsertException(); 
		 ResponseErrorInfo errorInfo= new ResponseErrorInfo();
		 errorInfo.setModueName(modeuName);
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
