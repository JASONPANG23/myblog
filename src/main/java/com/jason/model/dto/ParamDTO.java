package com.jason.model.dto;

import lombok.Data;

@Data
public class ParamDTO {

    /**
     * 要展示的文章编号
     */
    private Integer articleId ;
    /**
     * 需要展示哪个分类下的文章
     */
    private Integer categoryId ;
    /**
     * 需要展示哪个分类下的文章
     */
    private Integer tagId ;

    /**
     * 需要展示第几页
     */
    private Integer articlePage;
    /**
     * 每页展示文章的数量
     */
    private Integer articleSize;
    /**
     * 最多展示多少页
     */
    private Integer articlePages ;
    /**
     * 展示最近更新的多少篇文章
     */
    private Integer recentArticleSize ;
    /**
     * 分类： 需要展示哪一页
     */
    private Integer categoryPage;
    /**
     * 分类：每页展示的数量
     */
    private Integer categorySize;

    public ParamDTO(){
        this.articlePage = 1 ;
        this.articleSize = 5 ;
        this.articlePages = 5 ;
        this.categoryPage = 1 ;
        this.categorySize = 8 ;
        this.recentArticleSize = 5;
    }
}
