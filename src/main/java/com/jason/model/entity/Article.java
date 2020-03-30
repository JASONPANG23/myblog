package com.jason.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 文章实体类
 */
@Data
@Accessors(chain = true)
public class Article {
    /**
     * 文章标识
     */
    private Integer id ;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章描述
     */
    private String description;
    /**
     * 首图地址
     */
    private String firstPicture;
    /**
     * 是否发布
     */
    private Boolean published ;
    /**
     * 是否可评论
     */
    private Boolean comment;
    /**
     * 浏览数
     */
    private Integer views ;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 文章对应的分类
     */
    private Category category = new Category() ;
    /**
     * 评论数
     */
    private Integer commentNum ;
    /**
     * 标签
     */
    private List<Tag> tagList ;
}