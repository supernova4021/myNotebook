package com.me.core;
/**
 * IOC容器顶层接口
 * @author Administrator
 *
 */
public interface BeanFactory {
	//根据bean的id获取对象
	Object getBean(String id);
}
