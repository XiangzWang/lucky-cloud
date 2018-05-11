package org.bamboo.web.dao;

import java.util.List;

import org.bamboo.web.domain.PUser;

public interface PUserMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(PUser record);

	int insertSelective(PUser record);

	PUser selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(PUser record);

	int updateByPrimaryKey(PUser record);
	
	/**获取所有用户信息
     * @return List<User>
     */
    List<PUser> getAllUser();
}