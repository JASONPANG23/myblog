package com.jason.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jason.dao.CommentDao;
import com.jason.model.entity.Comment;
import com.jason.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> getComments() {
        return commentDao.getComments();
    }

    @Override
    public PageInfo<Comment> getComments(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Comment> comments = commentDao.getComments();
        return new PageInfo<>(comments);
    }

    @Override
    public List<Comment> getCommentsByArticleId(Integer id) {
        return commentDao.getCommentsByArticleIdAndParentIsNull(id);
    }

    @Override
    @Transactional
    public void addComment(Comment comment) {
        comment.setCreateTime(new Date());
        commentDao.addComment(comment);
    }
}
