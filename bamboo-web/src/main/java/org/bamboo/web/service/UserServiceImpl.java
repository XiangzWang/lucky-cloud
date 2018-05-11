package org.bamboo.web.service;

import java.util.List;

import org.bamboo.web.dao.PUserMapper;
import org.bamboo.web.domain.PUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * 使用@Autowired注解标注userMapper变量，
     * 当需要使用PUserMapper时，Spring就会自动注入PUserMapper
     */
    @Autowired
    private PUserMapper userMapper; //注入dao

    @Override
    public void addUser(PUser user) {
        userMapper.insert(user);
    }

    @Override
    public PUser getUserById(String userId) {
        return userMapper.selectByPrimaryKey(Integer.valueOf(userId));
    }

	@Override
	public List<PUser> getAllUser() {
		return userMapper.getAllUser();
	}
}
