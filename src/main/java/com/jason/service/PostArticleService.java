package com.jason.service;

import com.jason.model.dto.PostArticleDto;
import com.jason.model.entity.Tag;

import java.util.List;

public interface PostArticleService {

    void saveArticle(PostArticleDto postArticleDto);

//    void addArticle(PostArticleDto postArticleDto, List<Tag> tagList) ;
//
//    void saveArticle(PostArticleDto postArticleDto,List<Tag> tagList) ;
}
