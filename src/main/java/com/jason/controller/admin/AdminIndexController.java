package com.jason.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jason.model.ArticlePublish;
import com.jason.model.dto.ArticleGetDTO;
import com.jason.model.entity.Article;
import com.jason.model.entity.Category;
import com.jason.model.entity.Comment;
import com.jason.service.ArticleService;
import com.jason.service.CategoryService;
import com.jason.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminIndexController {

    @Autowired
    private ArticleService articleService ;

    @Autowired
    private CategoryService categoryService ;

    @Autowired
    private CommentService commentService;

    @RequestMapping(path = {"/index","/",""})
    public String toIndexPage(Model model, HttpServletRequest request){

        ArticleGetDTO articleGetDTO = new ArticleGetDTO() ;
        articleGetDTO.setPublished(ArticlePublish.All);

        PageInfo<Article> articles = articleService.getArticles(articleGetDTO,0, 5);
        PageInfo<Category> categories = categoryService.getCategories(0, 5);
        PageInfo<Comment> comments = commentService.getComments(0, 5);
        model.addAttribute("articleNum",articles.getTotal());
        model.addAttribute("categoryNum",categories.getTotal());
        model.addAttribute("commentNum",comments.getTotal());
        model.addAttribute("articles",articles);
        model.addAttribute("categories",categories);
        model.addAttribute("comments",comments);

        log.info("X-Pjax {}",request.getHeader("X-Pjax"));
        if(request.getHeader("X-Pjax") != null){
            return "admin/common-template/index" ;
        }
        return "admin/index" ;
    }

}
