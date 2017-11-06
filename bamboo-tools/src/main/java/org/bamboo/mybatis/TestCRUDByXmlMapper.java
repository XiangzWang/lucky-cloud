package org.bamboo.mybatis;

import java.util.Date;
import java.util.List;

import org.bamboo.security.Md5Utils;
import org.junit.Test;

public class TestCRUDByXmlMapper {
	
	@Test
	public void testGetUser() {		
		String statement = "org.bamboo.mybatis.PSysUserMapper.getUser"; 
		PSysUser user = MyBatis.selectOne(statement, "wxz");
		
		if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName() + "," + user.getOrgNo());	
		}
	}
	
	/**
	 * 通过<resultMap>映射实体类属性名和表的字段名,解决字段名与实体类属性名不相同的冲突
	 */
	@Test
	public void testResultMap() {
		String statement = "org.bamboo.mybatis.PSysUserMapper.selectResultMap"; 
		PSysUser user = MyBatis.selectOne(statement, "wxz");
		if (user != null) {
			System.out.println(user.getStaffNo() + "," + user.getName() + "," + user.getOrgNo());	
		}
	}
	
	@Test
	public void testAdd() {
		/**
		 * 映射sql的标识字符串，
		 * org.bamboo.mybatis.PSysUserMapper是PSysUserMapper.xml文件中mapper标签的namespace属性的值，
		 * addUser是insert标签的id属性值，通过insert标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "org.bamboo.mybatis.PSysUserMapper.addUser"; // 映射sql的标识字符串
		PSysUser user = new PSysUser();
		user.setName("xzw");
		user.setStaffNo("xzw");
		user.setOrgNo("13103");
		user.setPwd(Md5Utils.md5("123456"));
		user.setCreateDate(new Date());
		// 执行插入操作
		int retResult = MyBatis.insert(statement, user);
		
		System.out.println("插入记录:" + retResult);
	}

	@Test
	public void testUpdate() {
		/**
		 * 映射sql的标识字符串，
		 * org.bamboo.mybatis.PSysUserMapper是PSysUserMapper.xml文件中mapper标签的namespace属性的值，
		 * updateUser是update标签的id属性值，通过update标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "org.bamboo.mybatis.PSysUserMapper.updateUser";// 映射sql的标识字符串
		PSysUser user = new PSysUser();
		user.setName("wwxxzz");
		user.setStaffNo("xzw");
		user.setOrgNo("13103");
		// 执行修改操作
		int retResult = MyBatis.update(statement, user);
		
		System.out.println("更新记录:" + retResult);
	}

	@Test
	public void testDelete() {
		/**
		 * 映射sql的标识字符串，
		 * org.bamboo.mybatis.PSysUserMapper是PSysUserMapper.xml文件中mapper标签的namespace属性的值，
		 * deleteUser是delete标签的id属性值，通过delete标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "org.bamboo.mybatis.PSysUserMapper.deleteUser";// 映射sql的标识字符串
		// 执行删除操作
		int retResult = MyBatis.delete(statement, "xzw");
		
		System.out.println("删除记录:" + retResult);
	}

	@Test
	public void testGetAll() {
		/**
		 * 映射sql的标识字符串，
		 * org.bamboo.mybatis.PSysUserMapper是PSysUserMapper.xml文件中mapper标签的namespace属性的值，
		 * getAllUsers是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "org.bamboo.mybatis.PSysUserMapper.getAllUsers";// 映射sql的标识字符串
		// 执行查询操作，将查询结果自动封装成List<PSysUser>返回
		List<PSysUser> lstUsers = MyBatis.selectList(statement);
		
		if (lstUsers != null) {
			for (PSysUser user : lstUsers){
				System.out.println(user.getStaffNo() + "," + user.getName());
			}
		}
	}

}
