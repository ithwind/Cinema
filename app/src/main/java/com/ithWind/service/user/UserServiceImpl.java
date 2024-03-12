package com.ithWind.service.user;

import com.ithWind.common.UserContext;
import com.ithWind.domain.User;
import com.ithWind.domain.dto.UserDto;
import com.ithWind.domain.dto.UserInfo;
import com.ithWind.domain.mapper.UserMapper;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisCache redisCache;
    private final String userInfoKey = "user:";
    @Override
    public UserDto getUserByName() {
        Optional<UserInfo> currentUser = UserContext.getCurrentUser();
        String username = currentUser.map(UserInfo::getUserName).isPresent() ? currentUser.get().getUserName() : null;
        if (username == null) {
            return null;
        }
        String key = userInfoKey + username;
        User u = redisCache.getCacheObject(key);
        if (u != null) {
            UserDto userDto = new UserDto();
            BeanUtils.copyBeanProp(userDto, u);
            return userDto;
        }
        User user = userMapper.selectUserByName(username);
        if (user!= null) {
            UserDto userDto = new UserDto();
            BeanUtils.copyBeanProp(userDto, user);
            return userDto;
        }
        return null;
    }

    @Override
    public int UpdateUser(UserDto userDto) {
        String username = UserContext.getCurrentUser().isPresent() ? UserContext.getCurrentUser().get().getUserName() : null;
        if (username == null) {
            return 0;
        }
        String key = userInfoKey + username;
        redisCache.deleteObject(key);
        return userMapper.UpdateUser(userDto);
    }
}
