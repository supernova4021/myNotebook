package com.me.config;

import java.util.ArrayList;
import java.util.List;
/**
 * BeanInfo
 * @author Administrator
 */
public class Bean {
	private String id;
	private String className;
	private String scope="singleton";
	private List<Property> properties = new ArrayList<>();
	public String getName() {
		return id;
	}
	public void setName(String name) {
		this.id = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	@Override
	public String toString() {
		return "Bean [name=" + id + ", className=" + className + ", scope=" + scope + ", properties=" + properties
				+ "]";
	}
	
}
