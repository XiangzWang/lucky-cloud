package org.bamboo.web.service;

import java.util.List;

import org.bamboo.web.domain.PUser;

public interface UserService {
	 /**
     * 添加用户
     * @param user
     */
    void addUser(PUser user);
    
    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    PUser getUserById(String userId);
    
    /**获取所有用户信息
     * @return List<User>
     */
    List<PUser> getAllUser();
}
