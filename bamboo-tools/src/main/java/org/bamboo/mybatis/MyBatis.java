package org.bamboo.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MyBatis {
	
	public static <T> T selectOne(String statement, Object parameter) {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession();
		T result = sqlSession.selectOne(statement, parameter);
		sqlSession.close();  // 使用SqlSession执行完SQL之后需要关闭SqlSession
		return result;
	}
	
	public static <T> T selectOne(String statement) {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession();
		T result = sqlSession.selectOne(statement);
		sqlSession.close();  // 使用SqlSession执行完SQL之后需要关闭SqlSession
		return result;
	}
	
	public static <T> List<T> selectList(String statement, Object parameter) {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession();
		List<T> result = sqlSession.selectList(statement, parameter);
		sqlSession.close();  // 使用SqlSession执行完SQL之后需要关闭SqlSession
		return result;
	}
	
	public static <T> List<T> selectList(String statement) {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession();
		List<T> result = sqlSession.selectList(statement);
		sqlSession.close();  // 使用SqlSession执行完SQL之后需要关闭SqlSession
		return result;
	}
	
	public static int insert(String statement, Object parameter) {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
		int result = sqlSession.insert(statement, parameter);
		sqlSession.close();  // 使用SqlSession执行完SQL之后需要关闭SqlSession
		return result;
	}
	
	public static int insert(String statement) {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
		int result = sqlSession.insert(statement);
		sqlSession.close();  // 使用SqlSession执行完SQL之后需要关闭SqlSession
		return result;
	}
	
	public static int update(String statement, Object parameter) {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
		int result = sqlSession.update(statement, parameter);
		sqlSession.close();  // 使用SqlSession执行完SQL之后需要关闭SqlSession
		return result;
	}
	
	public static int update(String statement) {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
		int result = sqlSession.update(statement);
		sqlSession.close();  // 使用SqlSession执行完SQL之后需要关闭SqlSession
		return result;
	}
	
	public static int delete(String statement, Object parameter) {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
		int result = sqlSession.delete(statement, parameter);
		sqlSession.close();  // 使用SqlSession执行完SQL之后需要关闭SqlSession
		return result;
	}
	
	public static int delete(String statement) {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
		int result = sqlSession.delete(statement);
		sqlSession.close();  // 使用SqlSession执行完SQL之后需要关闭SqlSession
		return result;
	}
}
