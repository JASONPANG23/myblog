package com.jason.service;

import com.github.pagehelper.PageInfo;
import com.jason.model.dto.ArticleGetDTO;
import com.jason.model.entity.Article;

import java.util.List;

public interface ArticleService {

    void deleteArticleById(Integer id);

    /**
     *
     * @param articleGetDTO 查询对象
     * @return
     */
    List<Article> getArticles(ArticleGetDTO articleGetDTO) ;
    PageInfo<Article> getArticles(ArticleGetDTO articleGetDTO,Integer page,Integer size);
    PageInfo<Article> getArticles(ArticleGetDTO articleGetDTO,Integer page,Integer size,Integer pages);




    //增加访问量
    void incView(Integer id) ;



}
