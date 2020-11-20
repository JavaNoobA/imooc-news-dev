package com.imooc.user.service.impl;

import com.imooc.common.enums.Sex;
import com.imooc.common.enums.UserStatus;
import com.imooc.common.exception.GraceException;
import com.imooc.common.grace.result.ResponseStatusEnum;
import com.imooc.common.utils.DateUtil;
import com.imooc.common.utils.DesensitizationUtil;
import com.imooc.pojo.AppUser;
import com.imooc.pojo.bo.UpdateUserInfoBO;
import com.imooc.user.mapper.AppUserMapper;
import com.imooc.user.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 19:17
 */
@Service
public class UserServiceImpl implements UserService {
    private static final String USER_FACE0 = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Autowired
    private AppUserMapper userMapper;
    @Autowired
    private Sid sid;

    @Override
    public AppUser queryMobileIsExist(String mobile) {
        Example userExample = Example.builder(AppUser.class).build();
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("mobile", mobile);

        return userMapper.selectOneByExample(userExample);
    }

    @Override
    @Transactional
    public AppUser createUser(String mobile) {
        String userId = sid.nextShort();

        AppUser user = new AppUser();

        user.setId(userId);
        user.setMobile(mobile);
        user.setNickname("用户：" + DesensitizationUtil.commonDisplay(mobile));
        user.setFace(USER_FACE0);

        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        user.setSex(Sex.secret.type);
        user.setActiveStatus(UserStatus.INACTIVE.type);

        user.setTotalIncome(0);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        userMapper.insert(user);

        return user;
    }

    @Override
    public AppUser getUser(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional
    public void updateUserInfo(UpdateUserInfoBO userInfoBO) {
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(userInfoBO, appUser);
        appUser.setUpdatedTime(new Date());
        appUser.setActiveStatus(UserStatus.ACTIVE.type);

        int result = userMapper.updateByPrimaryKeySelective(appUser);
        if (result != 1) {
            GraceException.display(ResponseStatusEnum.USER_UPDATE_ERROR);
        }
    }
}
