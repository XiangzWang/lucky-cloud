package org.bamboo.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestOneLevelCache {
	 /*
     * 一级缓存: 也就Session级的缓存(默认开启)
     */
    @Test
    public void testCache1 () {
        SqlSession session = SqlSessionUtils.getSqlSession();
        String staffNo = "wxz";
        String statement = "org.bamboo.mybatis.PSysUserMapper.getUser";
        PSysUser user = session.selectOne(statement, staffNo);
        
        if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName() + "," + user.getOrgNo());	
		}
        
        /*
         * 一级缓存默认就会被使用
         */
        user = session.selectOne(statement, staffNo);
        if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName() + "," + user.getOrgNo());	
		}
        
        session.close();
        
        /*
         1. 必须是同一个Session,如果session对象已经close()过了就不可能用了 
         */
        session = SqlSessionUtils.getSqlSession();
        user = session.selectOne(statement, staffNo);
        if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName() + "," + user.getOrgNo());	
		}
        
        /*
         2. 查询条件是一样的
         */
        user = session.selectOne(statement, staffNo);
        if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName() + "," + user.getOrgNo());	
		}
        
        /*
         3. 没有执行过session.clearCache()清理缓存
         */
        //session.clearCache(); 
        user = session.selectOne(statement, staffNo);
        if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName() + "," + user.getOrgNo());	
		}
        
        /*
         4. 没有执行过增删改的操作(这些操作都会清理缓存)
         */
        PSysUser puser = new PSysUser();
        puser.setStaffNo("zhangsan");
        puser.setName("张大山");
        puser.setOrgNo("13103");
        session.update("org.bamboo.mybatis.PSysUserMapper.updateUser", puser);
        session.commit();
        user = session.selectOne(statement, staffNo);
        if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName() + "," + user.getOrgNo());	
		}
        session.close();
        
    }
}
