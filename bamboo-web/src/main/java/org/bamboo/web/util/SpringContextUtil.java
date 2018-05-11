/**
 * SpringContext工具类
 */
package org.bamboo.web.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 定义SpringContext工具,用于获取实例
 */
//@Component
public class SpringContextUtil { /*implements ApplicationContextAware {*/
	
	private static ApplicationContext context = null;
	
	static {
		context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring.xml", "classpath:spring-mybatis.xml"});
	}
	
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		SpringContextUtil.context = applicationContext;
//	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T) context.getBean(beanName);
	}
}
