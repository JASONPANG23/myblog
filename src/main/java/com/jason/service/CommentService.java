package com.jason.service;

import com.github.pagehelper.PageInfo;
import com.jason.model.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getComments();
    PageInfo<Comment> getComments(Integer page,Integer size);

    List<Comment> getCommentsByArticleId(Integer id);
    void addComment(Comment comment) ;
}
