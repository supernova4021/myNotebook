package com.me.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class BeanUtils {
	/**
	 * 根据bean对象和属性名获取属性的set方法
	 * @param beanObj
	 * @param name
	 * @return
	 */
	public static Method getWriteMethod(Object beanObj,String name){
		//使用内省技术实现该方法---->基于反射，用来操作bean的一套api
		
		Method writeMethod = null;
		
			try {
				//分析Bean对象-->BeanInfo
				BeanInfo beanInfo = Introspector.getBeanInfo(beanObj.getClass());
				//获得属性描述器
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				//遍历属性
				if(propertyDescriptors!=null){
					for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
						//判断是否是我们需要的方法
						String pName =	propertyDescriptor.getName();//寻找属性
						if(name.equals(pName)){
							//找到了
							writeMethod = propertyDescriptor.getWriteMethod();//对应属性的set方法
						}
						
					}
				}
				
				//返回找到的set方法
				
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(writeMethod == null){
				//没找到
				throw new RuntimeException("没有对应的set方法吗？");
			}
		return writeMethod;
	}
}
