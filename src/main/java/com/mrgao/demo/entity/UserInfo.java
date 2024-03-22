package com.mrgao.demo.entity;

import lombok.Data;

/**
 * @author Mr.Gao
 * @date 2023/12/29 10:15
 * @apiNote:
 */
@Data
public class UserInfo {
    /**
     * 用户主键Id
     */
    private Long id;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
}
