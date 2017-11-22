package practice.serialize;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import practice.serialize.bean.Fireball;
import practice.serialize.bean.Wizard;

public class MyXMLConverter {
	
	/**
	 * 把对象转化为XML
	 * 根节点是类的qualified name
	 * 里面的节点为其属性
	 * 如果还有非基本类型的属性，则以此递归遍历生成节点
	 * @param obj
	 * @return
	 */
	public static boolean objectToXml(Object obj){
		
		Document document = DocumentHelper.createDocument();
		objectToXml(obj, document);
		
		//生成格式
		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		outputFormat.setEncoding("utf-8");
		
		File file = new File(obj.getClass().getSimpleName()+".xml");
		XMLWriter xmlWriter = null;
		try {
			xmlWriter = new XMLWriter(new FileOutputStream(file), outputFormat);
			//设置是否转义，默认值是true，代表转义(防止被非法字符...)
			xmlWriter.setEscapeText(false);
			xmlWriter.write(document);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(xmlWriter!=null){
					xmlWriter.close();					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	private static boolean objectToXml(Object obj, Branch classRoot){
		Class<?> clazz = obj.getClass();
		String canonicalName = clazz.getCanonicalName();
		Element classNode = classRoot.addElement(canonicalName);
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {//遍历每一个属性并添加到class节点上
				field.setAccessible(true);
				Element property = classNode.addElement(field.getName());
				if(!field.getType().isPrimitive()){//如果不是基本类型，继续递归为该类型的Object添加节点
					if(field.get(obj)!=null){//不要写基本类型的包装类吧，懒得排除...
						objectToXml(field.get(obj), property);
					}
				}else {
					//设置属性的值
					property.setText(field.get(obj).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static Object xmlToObject(String filename){
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new File(filename));
			Element classRoot = document.getRootElement();
			return xmlToObject(classRoot);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object xmlToObject(Element classRoot) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String qname = classRoot.getName();
		Class<?> clazz = Class.forName(qname);
		Object obj = clazz.newInstance();
		Iterator iterator = classRoot.elementIterator();
		while (iterator.hasNext()) {
			Element property = (Element) iterator.next();
			String name = property.getName();
			if(property.elementIterator().hasNext()){
				//TODO 获取相应字段的set方法（内省机制）
				Method writeMethod = ObjectUtil.getWriteMethod(obj, name);
				Object propertyObj = xmlToObject((Element)property.elements().get(0));
				writeMethod.invoke(obj, propertyObj);
			}else {
				//TODO  获取相应字段的set方法（内省机制）
				Method writeMethod = ObjectUtil.getWriteMethod(obj, name);
				String val = property.getStringValue();
				//判断数据类型....并不准确
				if(val.contains(".")){
					writeMethod.invoke(obj, Double.valueOf(val).doubleValue());
				}else if(val.equals("true") || val.equals("false")){
					writeMethod.invoke(obj, Boolean.valueOf(val).booleanValue());
				}else{
					writeMethod.invoke(obj, Integer.valueOf(val).intValue());
				}
			}
		}
		return obj;
	}
	
		public static void main(String[] args) throws FileNotFoundException {
			Wizard wizard = (Wizard)xmlToObject("Wizard.xml");
			System.err.println(wizard);
		}
}
