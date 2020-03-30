package com.jason.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 评论实体类
 */
@Data
@Accessors(chain = true)
public class Comment {
    /**
     * 该评论的唯一标识
     */
    private Integer id ;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论者绰号
     */
    private String nickname;
    /**
     * 评论的方向，例如 Mio @ Jason
     */
    private String direct ;
    /**
     * 评论者邮箱
     */
    private String email;
    /**
     * 对应的文章ID
     */
    private Integer articleId;
    private Date createTime ;
    private List<Comment> comments ;
    /**
     * 父节点
     */
    private Comment parent ;
}
