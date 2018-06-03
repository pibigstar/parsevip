package com.pibigstar.utils;

import java.sql.Date;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * Json工具类
 * @author pibigstar
 *
 */
public class JsonUtil {
	
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
