package org.bamboo.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestTwoLevelCache {
	 /*
     * 测试二级缓存
     * 使用两个不同的SqlSession对象去执行相同查询条件的查询，第二次查询时不会再发送SQL语句，而是直接从缓存中取出数据
     */
    @Test
    public void testCache2() {
        String statement = "org.bamboo.mybatis.PSysUserMapper.getUser";
        String staffNo = "wxz";
//        SqlSessionFactory factory = SqlSessionUtils.getSqlSessionFactory();
        //开启两个不同的SqlSession
        SqlSession session1 = SqlSessionUtils.getSqlSession();
        SqlSession session2 = SqlSessionUtils.getSqlSession();
        //使用二级缓存时，PSysUser类必须实现一个Serializable接口===> PSysUser implements Serializable
        PSysUser user = session1.selectOne(statement, staffNo);
//        session1.commit(); // 这个地方一定要提交事务之后二级缓存才会起作用
        session1.close(); //关闭Session之后二级缓存才会起作用
        if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName() + "," + user.getOrgNo());	
		}
        
        //由于使用的是两个不同的SqlSession对象，所以即使查询条件相同，一级缓存也不会开启使用
        staffNo = "wxz";
        user = session2.selectOne(statement, staffNo);
        if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName() + "," + user.getOrgNo());	
		}
         
        session2.close();
    }
    
    @Test
    public void testCache2_2() {
        String statement = "org.bamboo.mybatis.PSysUserMapper.getUser";
        String staffNo = "wxz";
        PSysUser user = MyBatis.selectOne(statement, staffNo);
        if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName() + "," + user.getOrgNo());	
		}
        
        //由于使用的是两个不同的SqlSession对象，所以即使查询条件相同，一级缓存也不会开启使用
        user = MyBatis.selectOne(statement, staffNo);
        if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName() + "," + user.getOrgNo());	
		}
    }
}
