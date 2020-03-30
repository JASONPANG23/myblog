package com.jason.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * 管理员实体类
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String role ;
}
