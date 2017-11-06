package org.bamboo.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestProducer {
	@Test
    public void testGetUserCount(){
        String statement = "org.bamboo.mybatis.UserMapper.getUserCount";//映射sql的标识字符串
        Map<String, Integer> parameterMap = new HashMap<String, Integer>();
        parameterMap.put("sexid", 1);
        parameterMap.put("usercount", -1);
        MyBatis.selectOne(statement, parameterMap);
        Integer result = parameterMap.get("usercount");
        System.out.println("存储过程返回结果:" + result);
    }

}
