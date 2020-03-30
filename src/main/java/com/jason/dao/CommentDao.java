package com.jason.dao;

import com.jason.model.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    /**
     * 根据文章ID获取评论列表
     * @param articleId
     * @return
     */
    List<Comment> getCommentsByArticleId(Integer articleId);

    /**
     * 获取所有的评论
     * @return
     */
    List<Comment> getComments() ;

    /**
     * 添加评论
     * @param comment
     */
    void addComment(Comment comment) ;

    /**
     * 获取所有父节点
     * @return
     */
    List<Comment> getCommentsParentIsNull() ;

    /**
     * 获取父节点
     * @param articleId
     * @return
     */
    List<Comment> getCommentsByArticleIdAndParentIsNull(Integer articleId) ;

    /**
     * 获取一个父节点下的所有子节点
     * @param parentId
     * @return
     */
    List<Comment> getCommentsByParentId(Integer parentId) ;


}
