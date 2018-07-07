package com.pibigstar.parsevip.common.api;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.context.ApplicationContext;

import com.pibigstar.parsevip.common.annotation.APIMapping;

/**
 * api注册中心
 * @author pibigstar
 *
 */
public class ApiStore {
	
	private ApplicationContext context;
	/**
	 * 存放所有的api接口
	 */
	private HashMap<String, ApiRunable> apiMap = new HashMap<>();
	
	public ApiStore(ApplicationContext context) {
		if (context!=null) {
			this.context = context;
		}
	}
	
	public void loadApiFromSpringBeans() {
		//获得所有的bean名称
		String[] names = context.getBeanDefinitionNames();
		
		Class<?> type;
		for (String name : names) {
			type = context.getType(name);
			Method[] methods = type.getDeclaredMethods();
			for (Method method : methods) {
				APIMapping apiMapping = method.getAnnotation(APIMapping.class);
				if (apiMapping!=null) {
					addApiItem(apiMapping,name,method);
				}
			}
		}
		
	}

	/**
	 * 添加apiMapping到Map里
	 * @param apiMapping 
	 * @param name
	 * @param method
	 */
	private void addApiItem(APIMapping apiMapping, String name, Method method) {
		ApiRunable apiRunable = new ApiRunable();
		apiRunable.apiName = apiMapping.value();
		apiRunable.targeMethod = method;
		apiRunable.targeName = name;
		apiMap.put(apiMapping.value(), apiRunable);
	}
	
	public ApiRunable findApiRunable(String apiName) {
		return apiMap.get(apiName);
	}
	public ApiRunable findApiRunable(String apiName,String version) {
		return apiMap.get(apiName+"_"+version);
	}
	public List<ApiRunable> findApiRunables(String apiName){
		if (apiName==null) {
			throw new IllegalArgumentException("api name 不能为空！");
		}
		List<ApiRunable> list = new ArrayList<ApiStore.ApiRunable>();
		for (ApiRunable api : apiMap.values()) {
			if (api.apiName.equals(apiName)) {
				list.add(api);
			}
		}
		return list;
	}
	
	
	/**
	 * 用于执行对应的API方法
	 * @author pibigstar
	 *
	 */
	public class ApiRunable {

		String apiName;//api.user.getUser
		
		String targeName;// bean名称
		
		Object targe;//UserServiceImpl实例
		
		Method targeMethod;//目标方法，getUser
		
		//多线程问题
		public Object run(Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			//懒加载
			if (targe==null) {
				targe = context.getBean(targeName);
			}
			return targeMethod.invoke(targe, args);
		}
		
		public Class<?>[] getParamTypes(){
			return targeMethod.getParameterTypes();
		}
		public Method getTargetMethod() {
			return targeMethod;
		}
		public String getApiName() {
			return apiName;
		}
		public String getTargetName() {
			return targeName;
		}
		public Object getTarget() {
			return targe;
		}
	}
	

}
