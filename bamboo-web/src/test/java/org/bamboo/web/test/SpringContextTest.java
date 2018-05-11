package org.bamboo.web.test;

import org.bamboo.web.service.UserService;
import org.bamboo.web.util.SpringContextUtil;

public class SpringContextTest {

	public static void main(String[] args) {
		UserService userService = SpringContextUtil.getBean("userService");
		System.out.println(userService.getUserById("1"));

	}

}
