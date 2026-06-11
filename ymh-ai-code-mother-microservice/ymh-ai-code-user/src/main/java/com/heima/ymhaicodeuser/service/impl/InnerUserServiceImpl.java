package com.heima.ymhaicodeuser.service.impl;

import com.heima.ymhaicodermother.model.entity.User;
import com.heima.ymhaicodermother.model.vo.UserVO;
import com.heima.ymhaicodeuser.innerservice.InnerUserService;
import com.heima.ymhaicodeuser.service.UserService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 内部服务实现类
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserService userService;

    @Override
    public List<User> listByIds(Collection<? extends Serializable> ids) {
        return userService.listByIds(ids);
    }

    @Override
    public User getById(Serializable id) {
        return userService.getById(id);
    }

    @Override
    public UserVO getUserVO(User user) {
        return userService.getUserVO(user);
    }
}

