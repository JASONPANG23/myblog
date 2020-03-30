package com.jason.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员信息实体类
 * 使用properties文件进行存储
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String icon;
    private String nickname;
    private String introduction;
    private String blogTitle;
    private String blogSubtitle;
    private String description ;
    private String contactMe ;
    private String descriptionFooter ;
    private String weibo ;
    private String github ;
    private String qq ;
}
