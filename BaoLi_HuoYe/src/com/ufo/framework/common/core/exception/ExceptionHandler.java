package com.ufo.framework.common.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
public class ExceptionHandler extends HandlerExceptionResolverComposite {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception e) {
		// TODO Auto-generated method stub
		response.setStatus(500);
		ResponseErrorInfo errorInfo = new ResponseErrorInfo();
		if (e instanceof InsertException) {
			WebAppException appException=(WebAppException)e;
			errorInfo=appException.getErrorInfo();
		} else if (e instanceof UpdateException) {
			WebAppException appException=(WebAppException)e;
			errorInfo=appException.getErrorInfo();
		}else if (e instanceof DeleteException) {
			WebAppException appException=(WebAppException)e;
			errorInfo=appException.getErrorInfo();
		}else{
			errorInfo.setResultCode(ResponseErrorInfo.STATUS_FAILURE);
			errorInfo.getErrorMessage().put("msg", "未知错误!");
		}
		return new ModelAndView("jsonView").addObject("errorInfo", errorInfo);
	}

}
