package com.me.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.me.config.Bean;
import com.me.config.Property;
import com.me.config.parse.SourceReader;
import com.me.config.parse.XMLSourceReader;
import com.me.util.BeanUtils;

public class ClassPathXmlApplicationContext implements BeanFactory {

	private String configPath;
	protected SourceReader sourceReader;
	private Map<String, Bean> config;
	/*使用hashmap作spring容器*/
	private Map<String, Object> context = new HashMap<>();

	public ClassPathXmlApplicationContext(String path) {
		this.configPath = path;
		this.sourceReader = new XMLSourceReader();
		//获取bean配置信息
		config = sourceReader.getConfig(path);
		if(config!=null){
			for ( Entry< String, Bean> entry: config.entrySet()) {
				String name = entry.getKey();
				Bean bean = entry.getValue();
				
				Object existBean = context.get(name);
				if(existBean == null && bean.getScope().equals("singleton")){//容器不存在该bean并且scope为singleton时才创建
					//根据bean创建bean对象
					Object beanObj = createBean(bean);
					//放入容器
					context.put(name, beanObj);	
				}
				
			}
		}
	}
	private Object createBean(Bean bean) {
		String className = bean.getClassName();
		Class beanCls = null;
		Object beanObj = null;
		try {
			beanCls = Class.forName(className);
			//获得bean对象
			beanObj = beanCls.newInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(className+"这个路径不对吧，没找到这个类呢~");
		} catch (InstantiationException e) {
			throw new RuntimeException("空参构造啊，大哥");
		} catch (IllegalAccessException e) {
			throw new RuntimeException("烦死了");
		}
		
		//对bean进行属性注入
			if(bean.getProperties() != null){
				for (Property property : bean.getProperties()) {
					
					String name = property.getName();
					String value = property.getValue();
					String ref = property.getRef();
					
					//使用Apache的BeanUtils工具完成简单的属性注入
					if(value!=null){
						Map<String, String> paramMap = new HashMap<>();
						paramMap.put(name,value);
						try {
							//该工具能完成类型的自动转换
							org.apache.commons.beanutils.BeanUtils.populate(beanObj, paramMap);
						} catch (IllegalAccessException | InvocationTargetException e) {
							e.printStackTrace();
							throw new RuntimeException("属性不行了！");
						}
					}
					
//					
//					if(property.getValue()!=null){
//						//简单注入，value直接注入
//						param =  property.getValue();
//					}
					
					if(property.getRef()!=null){
						//麻烦，引用对象注入
						
						//获取set方法
						Method setMethod = BeanUtils.getWriteMethod(beanObj,name);
						
						//从容器中查找当前要注入的Bean是否已创建
						Object existBean = context.get(property.getRef());
						if(existBean == null){//把singleton的bean放入容器中
							//没创建
							existBean = createBean(config.get(property.getRef()));//创建bean
							//将bean放回容器中
							if(config.get(property.getRef()).getScope().equals("singleton")){								
								context.put(property.getRef(),existBean);
							}
						}
						try {
							//调用set方法完成属性注入
							setMethod.invoke(beanObj, existBean);	
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							throw new RuntimeException("那个...没有对应的set方法吗");
						}
					}
				
				}
			}
		
		return beanObj;
	}

	@Override
	public Object getBean(String name) {
		
		Object bean = context.get(name);
		//如果scope为prototype，则context里还没有这个bean
		if(bean == null){//scope为prototype的bean，不保存在容器中
			bean = createBean(config.get(name));
		}
		return bean;
	}

}
