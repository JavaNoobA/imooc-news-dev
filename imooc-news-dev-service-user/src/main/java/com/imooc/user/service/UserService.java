package com.imooc.user.service;

import com.imooc.pojo.AppUser;
import com.imooc.pojo.bo.UpdateUserInfoBO;

/**
 * @author pengfei.zhao
 * @date 2020/11/20 19:17
 */
public interface UserService {

    /**
     * 查询用户是否存在
     * @param mobile 手机号
     * @return 用户
     */
    AppUser queryMobileIsExist(String mobile);

    /**
     * 创建用户
     * @param mobile 手机号
     * @return 用户
     */
    AppUser createUser(String mobile);

    /**
     * 根据 userId 查询用户
     * @param userId 用户id
     * @return 用户
     */
    AppUser getUser(String userId);

    /**
     * 更新用户信息
     * @param userInfoBO 用户信息
     * @return 用户
     */
    void updateUserInfo(UpdateUserInfoBO userInfoBO);
}
