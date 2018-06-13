package org.smart4j.framework.bean;

import java.util.Map;

import org.smart4j.framework.util.CastUtil;

public class Param {

	private Map<String, Object> paramMap;

	public Param(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public Map<String, Object> getMap() {
		return paramMap;
	}

	public int getInt(String name) {
		return CastUtil.castInt(paramMap.get(name));
	}

	public long getLong(String name) {
		return CastUtil.castLong(paramMap.get(name));
	}

	public String getString(String name) {
		return CastUtil.castString(paramMap.get(name));
	}

	public boolean getBoolean(String name) {
		return CastUtil.castBoolean(paramMap.get(name));
	}

	public double getDouble(String name) {
		return CastUtil.castDouble(paramMap.get(name));
	}

}
