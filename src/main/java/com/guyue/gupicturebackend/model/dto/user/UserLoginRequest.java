package com.guyue.gupicturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 古月
 * @version 1.0
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 9070816143129045958L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

}
