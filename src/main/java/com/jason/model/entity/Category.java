package com.jason.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分类实体类
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    /**
     * 分类的唯一标识
     */
    private Integer id;
    /**
     * 分类名称
     */
    private String name ;
    /**
     * 分类下的文章数量
     */
    private Integer blogNum;
    /**
     * 分类下的文章
     */
    private List<Article> blogList ;
}
