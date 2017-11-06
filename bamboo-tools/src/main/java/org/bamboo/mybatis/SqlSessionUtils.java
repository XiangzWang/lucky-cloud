package org.bamboo.mybatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtils {
	private static SqlSessionFactory FACTORY_INSTANCE;
	static {
		String resource = "mybatis/conf.xml";
		InputStream is = SqlSessionUtils.class.getClassLoader().getResourceAsStream(resource);
		FACTORY_INSTANCE = new SqlSessionFactoryBuilder().build(is);
	}

	/**
	 * 获取SqlSessionFactory
	 * 
	 * @return SqlSessionFactory
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		if (FACTORY_INSTANCE != null) {
			return FACTORY_INSTANCE;
		}
		String resource = "mybatis/conf.xml";
		InputStream is = SqlSessionUtils.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		return factory;
	}

	/**
	 * 获取SqlSession
	 * 
	 * @return SqlSession
	 */
	public static SqlSession getSqlSession() {
		return getSqlSessionFactory().openSession();
	}

	/**
	 * 获取SqlSession
	 * 
	 * @param isAutoCommit
	 *            true表示创建的SqlSession对象在执行完SQL之后会自动提交事务,<br/>
	 *            false表示创建的SqlSession对象在执行完SQL之后不会自动提交事务,<br/>
	 *            这时就需要我们手动调用sqlSession.commit()提交事务
	 * @return SqlSession
	 */
	public static SqlSession getSqlSession(boolean isAutoCommit) {
		return getSqlSessionFactory().openSession(isAutoCommit);
	}
	
}