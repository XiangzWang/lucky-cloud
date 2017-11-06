package org.bamboo.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestUnionQuery {
	/**
	 * 一对一联合查询方式一
	 */
	@Test
	public void testGetUserWithOrg() {
		String statement = "org.bamboo.mybatis.PSysUserMapper.getUserWithOrg";
		PSysUser user = MyBatis.selectOne(statement, "wxz");
		if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName());
			OOrg org = user.getOrg();
			System.out.println(org.getOrgNo() + "," + org.getOrgName());
		}
	}
	
	/**
	 *  一对一联合查询方式二
	 */
	@Test
	public void testGetUserWithOrg2() {
		String statement = "org.bamboo.mybatis.PSysUserMapper.getUserWithOrg2";
		PSysUser user = MyBatis.selectOne(statement, "wxz");
		if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName());
			OOrg org = user.getOrg();
			System.out.println(org.getOrgNo() + "," + org.getOrgName());
		}
	}
	
	/**
	 *  一对多联合查询方式一
	 */
	@Test
	public void testGetAccessOrgWithUser() {
		String statement = "org.bamboo.mybatis.PAccessOrgMapper.getAccessOrgWithUser";
		PAccessOrg pAccessOrg = MyBatis.selectOne(statement, "13103");
		
		if (pAccessOrg != null) {
			System.out.println(pAccessOrg.getStaffNo() + "," + pAccessOrg.getOrgNo() + "," + pAccessOrg.getOrgType());
			OOrg org = pAccessOrg.getOrg();
			System.out.println(org.getOrgNo() + "," + org.getOrgName());
			List<PSysUser> lstUsers = pAccessOrg.getUsers();
			if (lstUsers != null) {
				for (PSysUser user : lstUsers){
					System.out.println(user.getStaffNo() + "," + user.getName());
				}
			}
		}
	}
	
	/**
	 *  一对多联合查询方式二
	 */
	@Test
	public void testGetAccessOrgWithUser2() {
		String statement = "org.bamboo.mybatis.PAccessOrgMapper.getAccessOrgWithUser2";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("orgNo", "13103");
		paramMap.put("staffNo", "wxz");
		PAccessOrg pAccessOrg = MyBatis.selectOne(statement, paramMap);
		
		if (pAccessOrg != null) {
			System.out.println(pAccessOrg.getStaffNo() + "," + pAccessOrg.getOrgNo() + "," + pAccessOrg.getOrgType());
			OOrg org = pAccessOrg.getOrg();
			System.out.println(org.getOrgNo() + "," + org.getOrgName());
			List<PSysUser> lstUsers = pAccessOrg.getUsers();
			if (lstUsers != null) {
				for (PSysUser user : lstUsers){
					System.out.println(user.getStaffNo() + "," + user.getName());
				}
			}
		}
	}
}
