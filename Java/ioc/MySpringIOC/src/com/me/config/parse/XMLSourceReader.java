package com.me.config.parse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.me.config.Bean;
import com.me.config.Property;
/**
 * 从xml中读取Bean信息
 * @author Administrator
 *
 */
public class XMLSourceReader implements SourceReader{
	/**
	 * 读取xml配置文件
	 * @param path
	 * @return
	 */
	public Map<String, Bean> getConfig(String path){
		
		
		Map<String, Bean> map = new HashMap<>();
		//dom4j解析xml
			//创建解析器
			SAXReader reader = new SAXReader();
			//加载配置文件=>document对象
			InputStream is = XMLSourceReader.class.getResourceAsStream(path);
			Document document = null;
			try {
				document = reader.read(is);
			} catch (DocumentException e) {
				throw new RuntimeException("可能是你的配置出问题了吧~");
			}
			//定义xpath表达式，去除所有bean元素
			String xpath = "//bean";
			//对bean元素进行遍历
			List<Element> list = document.selectNodes(xpath);
			if(list!=null){
				for (Element bean : list) {
					Bean b = new Bean();
					//将bean的name,class属性封装到bean对象中
					String name = bean.attributeValue("id");
					String className = bean.attributeValue("class");
					String scope = bean.attributeValue("scope");
					b.setName(name);
					b.setClassName(className);
					if(scope != null){
						b.setScope(scope);
					}
					
					//获取Bean下的所有property元素
					@SuppressWarnings("unchecked")
					List<Element> children = bean.elements("property");
					List<Property> properties = new ArrayList<>();
					if(children!=null){
						for (Element child : children) {
							Property p = new Property();
							properties.add(p);
							String cname = child.attributeValue("name");
							String value =child.attributeValue("value");
							String ref = child.attributeValue("ref");
							p.setName(cname);
							p.setValue(value);
							p.setRef(ref);
						}
					}
					b.setProperties(properties);
					map.put(name, b);
				}
			}
		return map;
	}
}
