/***********************************************
 * File Name: UserService
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 24 05 2019 16:29
 ***********************************************/
package com.duochuang.service;

import com.duochuang.common.entity.UserEntity;

public interface UserService {
    UserEntity findByUsername(String username);
}
