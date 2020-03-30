package com.jason.dao;

import com.jason.model.dto.ArticleGetDTO;
import com.jason.model.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao {

    /**
     * 通过分类ID删除文章
     * @param id
     */
    void deleteArticleByCategoryId(Integer id);

    /**
     * 通过文章ID删除文章
     * @param id
     */
    void deleteArticleById(Integer id);


    /**
     * 根据articleGetDTO查询对象查询文章
     * @param articleGetDTO
     * @return
     */
    List<Article> getArticles(ArticleGetDTO articleGetDTO) ;



    /**
     * 添加文章
     * @param article
     */
    void addArticle(Article article);

    /**
     * 更新文章
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 根据分类ID获取文章数量
     * @param id
     * @return
     */
    Integer getArticleCountByCategoryId(Integer id);

    /**
     * 根据文章ID获取以发布的文章数量
     * @param id
     * @return
     */
    Integer getArticlePublishCountByCategoryId(Integer id);


    /**
     * 添加阅读数
     */
    void incView(Article article) ;
}
