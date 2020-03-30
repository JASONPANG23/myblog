package com.jason.controller.site;

import com.github.pagehelper.PageInfo;
import com.jason.exception.CustomizeErrorCode;
import com.jason.exception.CustomizeException;
import com.jason.model.ArticlePublish;
import com.jason.model.dto.ArticleGetDTO;
import com.jason.model.dto.ParamDTO;
import com.jason.model.entity.Article;
import com.jason.model.entity.Category;
import com.jason.model.entity.Comment;
import com.jason.model.entity.Tag;
import com.jason.service.ArticleService;
import com.jason.service.CategoryService;
import com.jason.service.CommentService;
import com.jason.service.TagService;
import com.jason.utils.MarkdownUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class SiteIndexController {

    @Autowired
    private ArticleService articleService ;

    @Autowired
    private CategoryService categoryService ;

    @Autowired
    private TagService tagService ;

    @Autowired
    private CommentService commentService ;



    @GetMapping(path = {"/",""})
    public String index(ParamDTO paramDTO,
                        Model model,
                        HttpServletRequest request){
        ArticleGetDTO articleGetDTO = new ArticleGetDTO() ;
        articleGetDTO.setPublished(ArticlePublish.PUBLISH);
        PageInfo<Article> articleList =
                articleService.getArticles(
                        articleGetDTO,
                        paramDTO.getArticlePage(),
                        paramDTO.getArticleSize(),
                        paramDTO.getArticlePages());
        model.addAttribute("articleList",articleList) ;

        System.out.println("X-Pjax "+request.getHeader("X-Pjax"));
        if(request.getHeader("X-Pjax") != null){
            return "site/common-template/article-list" ;
        }
        return common(paramDTO,model);
    }

    @GetMapping("/tag")
    public String tag(ParamDTO paramDTO, Model model, HttpServletRequest request){
        ArticleGetDTO articleGetDTO = new ArticleGetDTO() ;
        articleGetDTO.setPublished(ArticlePublish.PUBLISH);
        articleGetDTO.setTagId(paramDTO.getTagId());
        PageInfo<Article> articleList =
                articleService.getArticles(
                        articleGetDTO,
                        paramDTO.getArticlePage(),
                        paramDTO.getArticleSize(),
                        paramDTO.getArticlePages());

        Tag tag = tagService.getTag(paramDTO.getTagId());
        model.addAttribute("articleList",articleList) ;
        model.addAttribute("tagId",tag.getId()) ;
        model.addAttribute("tagName",tag.getName()) ;
        System.out.println("X-Pjax "+request.getHeader("X-Pjax"));
        if(request.getHeader("X-Pjax") != null){
            return "site/common-template/article-list" ;
        }
        return common(paramDTO,model);
    }


    @GetMapping("/category")
    public String category(ParamDTO paramDTO, Model model, HttpServletRequest request){
        ArticleGetDTO articleGetDTO = new ArticleGetDTO() ;
        articleGetDTO.setPublished(ArticlePublish.PUBLISH);
        articleGetDTO.setCategoryId(paramDTO.getCategoryId());
        PageInfo<Article> articleList =
                articleService.getArticles(
                        articleGetDTO,
                        paramDTO.getArticlePage(),
                        paramDTO.getArticleSize(),
                        paramDTO.getArticlePages());

        Category category = categoryService.getCategoryById(paramDTO.getCategoryId());

        model.addAttribute("articleList",articleList) ;
        model.addAttribute("categoryId",category.getId()) ;
        model.addAttribute("categoryName",category.getName()) ;
        System.out.println("X-Pjax "+request.getHeader("X-Pjax"));
        if(request.getHeader("X-Pjax") != null){
            return "site/common-template/article-list" ;
        }
        return common(paramDTO,model);
    }


    @GetMapping("/article")
    public String article(ParamDTO paramDTO, Model model, HttpServletRequest request) {

        ArticleGetDTO articleGetDTO = new ArticleGetDTO() ;
        articleGetDTO.setId(paramDTO.getArticleId());
        articleGetDTO.setContents(true);
        List<Article> articles = articleService.getArticles(articleGetDTO);
        if(articles == null || articles.isEmpty()){
            throw new CustomizeException(CustomizeErrorCode.ARTICLE_NOT_FOUND) ;
        }
        Article article = articles.get(0) ;
        article.setContent(MarkdownUtil.markdownToHtml(article.getContent()));



        articleService.incView(article.getId());
        List<Comment> comments = commentService.getCommentsByArticleId(paramDTO.getArticleId());
        System.out.println("X-Pjax "+request.getHeader("X-Pjax"));
        model.addAttribute("article",article);
        model.addAttribute("comments",comments);
        model.addAttribute("articleTags",article.getTagList()) ;
        log.info("article : {}",model.getAttribute("article"));
        if(request.getHeader("X-Pjax") != null){
            return "site/common-template/article-details" ;
        }
        return common(paramDTO,model);
    }


    public String common(ParamDTO paramDTO,
                         Model model){

        ArticleGetDTO articleGetDTO = new ArticleGetDTO() ;
        articleGetDTO.setPublished(ArticlePublish.PUBLISH);

        PageInfo<Article> recentArticles =
                articleService.getArticles(articleGetDTO,1, paramDTO.getRecentArticleSize());

        PageInfo<Category> categories =
                categoryService.getCategoriesIncludePublish(
                        paramDTO.getCategoryPage(),
                        paramDTO.getCategorySize());

        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("tags",tags) ;
        model.addAttribute("recentArticles",recentArticles) ;
        model.addAttribute("categories",categories) ;


        if(model.getAttribute("article")!=null){
            return "site/blog-details";
        }
        return "site/index";
    }

//    @GetMapping("/display")
//    public String display(Model model){
//
//    }

    @RequestMapping("/comments/post")
    public String postComment(Comment comment){

        if(comment.getParent().getId() == -1)
            comment.getParent().setId(null) ;
        if("".equals(comment.getDirect())){
            comment.setDirect(null) ;
        }
        commentService.addComment(comment);
        return "forward:/comments?articleId="+comment.getArticleId();
    }

    @RequestMapping("/comments")
    public String getComments(@RequestParam("articleId")Integer articleId,
                              Model model){

        List<Comment> comments = commentService.getCommentsByArticleId(articleId);
        model.addAttribute("comments",comments) ;
        return "site/common-template/article-details::comment";
    }
}
