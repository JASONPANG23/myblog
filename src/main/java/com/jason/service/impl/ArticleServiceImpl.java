package com.jason.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jason.dao.ArticleDao;
import com.jason.exception.CustomizeErrorCode;
import com.jason.exception.CustomizeException;
import com.jason.model.dto.ArticleGetDTO;
import com.jason.model.entity.Article;
import com.jason.model.entity.Category;
import com.jason.service.ArticleService;
import com.jason.utils.MarkdownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao ;

    /**
     * 根据文章ID删除文章
     * @param id
     */
    @Override
    @Transactional
    public void deleteArticleById(Integer id) {
        articleDao.deleteArticleById(id);
    }

    @Override
    public List<Article> getArticles(ArticleGetDTO articleGetDTO) {
        return articleDao.getArticles(articleGetDTO);
    }

    @Override
    public PageInfo<Article> getArticles(ArticleGetDTO articleGetDTO, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Article> articles = articleDao.getArticles(articleGetDTO);
        return new PageInfo<>(articles) ;
    }

    @Override
    public PageInfo<Article> getArticles(ArticleGetDTO articleGetDTO, Integer page, Integer size, Integer pages) {
        PageHelper.startPage(page,size);
        List<Article> articles = articleDao.getArticles(articleGetDTO);
        return new PageInfo<>(articles,pages) ;
    }


    /**
     * 添加阅读数
     */
    @Override
    public void incView(Integer id){
        Article article = new Article() ;
        article.setId(id) ;
        article.setViews(1) ;
        articleDao.incView(article);
    }

}
