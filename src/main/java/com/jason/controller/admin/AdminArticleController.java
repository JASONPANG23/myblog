package com.jason.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jason.model.ArticlePublish;
import com.jason.model.dto.ArticleGetDTO;
import com.jason.model.entity.Article;
import com.jason.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/admin/article")
@Slf4j
public class AdminArticleController {

    @Autowired
    private ArticleService articleService ;

    @RequestMapping(path = {"/",""})
    public String toBlogPage(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "8")Integer size,
                             Model model,
                             HttpServletRequest request){

        ArticleGetDTO articleGetDTO = new ArticleGetDTO() ;
        articleGetDTO.setPublished(ArticlePublish.All);
        PageInfo<Article> articles = articleService.getArticles(articleGetDTO,page, size);
        model.addAttribute("articles",articles);

        log.info("X-Pjax {}",request.getHeader("X-Pjax"));
        if(request.getHeader("X-Pjax") != null){
            return "admin/common-template/article" ;
        }
        return "admin/article" ;
    }


    @RequestMapping("/list")
    public String listCategory(@RequestParam(value = "page",defaultValue = "1") Integer page,
                               @RequestParam(value = "size",defaultValue = "8") Integer size,
                               Model model){

        ArticleGetDTO articleGetDTO = new ArticleGetDTO() ;
        articleGetDTO.setPublished(ArticlePublish.All);
        PageInfo<Article> articles = articleService.getArticles(articleGetDTO,page, size);
        model.addAttribute("articles",articles);
        return "admin/common-template/article::list";
    }

    @RequestMapping("delete")
    public String deleteArticle(Integer id,Model model){
        articleService.deleteArticleById(id);
        return "forward:/admin/article/list" ;
    }
}
