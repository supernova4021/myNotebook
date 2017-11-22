package practice.serialize;

import net.sf.json.JSONObject;
import practice.serialize.bean.Fireball;
import practice.serialize.bean.Wizard;

public class MyJSONConverter {
	
	public static final String OBJECTKEY = "ClassName";
	
	public static String objectToJSON(Object object){
		JSONObject jsonObject = JSONObject.fromObject(object);
		//“‘OBJECTKEY¥Ê¥¢CanonicalName
		jsonObject.put(OBJECTKEY, object.getClass().getCanonicalName());
		return jsonObject.toString();
	}
	
	public static Object jsonToObject(String json){
		JSONObject jsonObject = JSONObject.fromObject(json);
		String qName = jsonObject.getString(OBJECTKEY);
		Class<?> clazz = null;
		try {
			clazz = Class.forName(qName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		jsonObject.remove(OBJECTKEY);
		return JSONObject.toBean(jsonObject, clazz);
	}
	
	public static void main(String[] args) {
		Wizard wizard = new Wizard(1111.0, 22.0, new Fireball());
		String wizardJson = objectToJSON(wizard);
		System.err.println(wizardJson);
		Wizard wizard2 = (Wizard)jsonToObject(wizardJson);
	}
	
}
