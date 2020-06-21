package club.do4u.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {
	
/**
 * Dao层
 * Service层
 * Web层
 * 都可以使用。该方法利用map中的数据自动实例化bean对象
 * @param map
 * @param bean
 * @return 通过map实例化的bean对象
 */
public static <T> T copyMapToBean(Map map,T bean) {
	try {
		BeanUtils.populate(bean,map);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	return bean;
	
}
}
