package com.pibigstar.common.api;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import com.alibaba.fastjson.JSON;
import com.pibigstar.common.api.ApiStore.ApiRunable;
import com.pibigstar.common.exception.ApiException;
/**
 * API 处理者
 * @author pibigstar
 *
 */
public class ApiGatewayHandler implements InitializingBean,ApplicationContextAware{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String METHOD = "method";
	private static final String PARAMS = "params";
	
	ApiStore apiStore;
	final ParameterNameDiscoverer parammeterUtil;
	//构造
	public ApiGatewayHandler() {
		parammeterUtil = new LocalVariableTableParameterNameDiscoverer();
	}
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		//初始化apiStore
		apiStore = new ApiStore(context);
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		//加载API接口
		apiStore.loadApiFromSpringBeans();
	}
	
	/**
	 * 处理
	 * @param req
	 * @param resp
	 */
	public void handle(HttpServletRequest req, HttpServletResponse resp) {
		String method = req.getParameter(METHOD);
		String params = req.getParameter(PARAMS);
		
		Object result;
		ApiRunable apiRun = null;
		
		try {
			apiRun = sysParamsValidata(req);
			logger.info("请求接口={"+method+"} 参数="+params+"");
			// 验证业务参数，转换业务参数
			Object[] args = buildParams(apiRun,params,req,resp);
			result = apiRun.run(args);
		} catch (ApiException e) {
			resp.setStatus(500);
			logger.error("请求接口={"+method+"}异常   参数="+params+"",e);
			result = handleError(e);//封装异常信息并返回
		} catch (IllegalAccessException e) {
			resp.setStatus(500);
			logger.error("请求接口={"+method+"}异常   参数="+params+"",e);
			result = handleError(e);
		} catch (IllegalArgumentException e) {
			resp.setStatus(500);
			logger.error("请求接口={"+method+"}异常   参数="+params+"",e);
			result = handleError(e);
		} catch (InvocationTargetException e) {
			resp.setStatus(500);
			logger.error("请求接口={"+method+"}异常   参数="+params+"",e.getTargetException());
			result = handleError(e);
		}catch (Exception e) {
			resp.setStatus(500);
			logger.error("其他异常",e);
			result = handleError(e);
		}
		returnResult(result,resp);
	}
	
	/**
	 * 处理异常
	 * @param e
	 * @return
	 */
	private Object handleError(Exception e) {
		return e.getMessage();
	}
	
	/**
	 * 将结果输出到前台
	 * @param result
	 * @param response
	 */
	private void returnResult(Object result,HttpServletResponse response) {
		
		try {
			String resultJson = JSON.toJSONString(result);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			if (resultJson!=null) {
				response.getWriter().write(resultJson);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 验证业务参数
	 * 转换参数
	 * @param apiRun
	 * @param params2
	 * @param req
	 * @param resp
	 * @return
	 */
	private Object[] buildParams(ApiRunable apiRun, String paramsJson, HttpServletRequest req, HttpServletResponse resp) {

		Map<String, Object> map = null;
		try {
			map = JSON.parseObject(paramsJson,Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("调用失败：json字符串异常，请检查params参数");
		}
		if (map==null) {
			map = new HashMap<String, Object>();
		}
		
		Method method = apiRun.getTargetMethod();
		List<String> paramNames = Arrays.asList(parammeterUtil.getParameterNames(method));
		
		Class<?>[] paramTypes = method.getParameterTypes();
		
		for (Map.Entry<String, Object> m : map.entrySet()) {
			if (!paramNames.contains(m.getKey())) {
				throw new ApiException("调用失败：接口不存在'"+m.getKey()+"' 参数");
			}
		}
		int len = paramTypes.length;
		Object[] args = new Object[len];
		for (int i = 0; i < len; i++) {
			if (paramTypes[i].isAssignableFrom(HttpServletRequest.class)) {
				args[i] = req;
			}else if (map.containsKey(paramNames.get(i))) {
				args[i] = converJsonToBean(map.get(paramNames.get(i)), paramTypes[i]);
			}
		}
		
		return args;
	}

	/**
	 * 系统参数校验
	 * @param request
	 * @return
	 */
	private ApiRunable sysParamsValidata(HttpServletRequest request) {
		String apiName = request.getParameter(METHOD);
		String params = request.getParameter(PARAMS);
		ApiRunable api;
		if (apiName==null||apiName.trim().equals("")) {
			throw new ApiException("调用失败：参数'method'为空");
		}else if (params==null) {
			throw new ApiException("调用失败：参数'params'为空");
		}else if ((api=apiStore.findApiRunable(apiName))==null) {
			throw new ApiException("调用失败：指定API不存在,API:"+apiName);
		}
		return api;
	}

	/**
	 * 将Map转换成具体的目标方法参数对象
	 * @param val
	 * @param targetClass
	 * @return
	 */
	private <T> Object converJsonToBean(Object val,Class<T> targetClass) {
		Object result = null;
		if (val==null) {
			return null;
		}else if (Integer.class.equals(targetClass)) {
			result = Integer.parseInt(val.toString());
		}else if (Long.class.equals(targetClass)) {
			result = Long.parseLong(val.toString());
		}else if (Date.class.equals(targetClass)) {
			if (val.toString().matches("[0-9]+")) {
				result = new Date(Long.parseLong(val.toString()));
			}else {
				throw new IllegalArgumentException("日期必须是长整型的时间戳");
			}
		}else if (String.class.equals(targetClass)) {
			if (val instanceof String) {
				result = val;
			}else {
				result = val.toString();
			}
		}else {
			result = JSON.parseObject(val.toString(), targetClass);
		}
		
		return result;
	}
	

}