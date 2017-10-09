package factory;

import java.util.ResourceBundle;

public class BeanFactory {
	//加载配置文件
	public static ResourceBundle bundle;
	static {
		bundle = ResourceBundle.getBundle("instance");
	}
	/*
	 * 根据指定的key，读取配置文件类的全路径，创建对象
	 * */
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(String key,Class<T> clazz) {
		String className = bundle.getString(key);
		try {
			return (T)Class.forName(className).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
