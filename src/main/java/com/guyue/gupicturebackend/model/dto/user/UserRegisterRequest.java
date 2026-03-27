package com.guyue.gupicturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 古月
 * @version 1.0
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -1419456988905529228L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;

}
