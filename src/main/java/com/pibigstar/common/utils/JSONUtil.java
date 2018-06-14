package com.pibigstar.common.utils;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Json工具类
 * @author pibigstar
 *
 */
public class JSONUtil {
	
	/**
	 * java对象转换为JSON字符串
	 * @param object
	 * @return
	 */
	public static String ObjectToJSON(Object object) {
		return JSON.toJSONString(object);
	}
	
	/**
	 * JSON字符串转换为Java对象
	 * @param json
	 * @param obj
	 * @return
	 */
	public static <T> T jSONToObject(String json, Class<T> obj) {
		JSONObject jsonObject = JSON.parseObject(json);
		return (T) JSON.toJavaObject(jsonObject, obj);
	}
	/**
	 * List集合转换为JSON字符串
	 * @param list
	 * @return
	 */
	public static <T> String listToJSON(List<T> list) {
		return JSONArray.toJSONString(list);
	}
	
	/**
	 * 将JSON字符串转换为List集合
	 * @param json
	 * @param obj
	 * @return
	 */
	public static <T> List<T> JSONToList(String json,Class<T> obj){
		return JSON.parseArray(json, obj);
	}
	
	/**
	 * 将JSON字符串转换为Map集合
	 * @param str
	 * @return
	 */
	public Map<?, ?> toMap(String str){
		return JSON.parseObject(str, Map.class);
	}
	
	/**
	 * 将Map转换成具体的目标方法参数对象
	 * @param val
	 * @param targetClass
	 * @return
	 */
	public <T> Object converJsonToBean(Object val,Class<T> targetClass) {
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
