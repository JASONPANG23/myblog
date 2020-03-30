package com.jason.model.dto;

import com.jason.model.ArticlePublish;
import lombok.Data;

@Data
public class ArticleGetDTO {
    private Integer id ;
    private String title ;
    private Integer categoryId ;
    // -1获取所有 1获取已发布 0获取未发布
    private ArticlePublish published ;
    private Integer tagId ;
    private String createTime ;
    // 是否包含内容
    private boolean contents  = false;

    public static void main(String[] args) {

    }
}
//SELECT DATE_FORMAT(create_time,'%Y%m%d') months,COUNT(id) COUNT FROM tb_article GROUP BY months;