package com.jason.controller.admin;

import com.jason.dao.TagMapDao;
import com.jason.exception.CustomizeErrorCode;
import com.jason.exception.CustomizeException;
import com.jason.model.dto.ArticleGetDTO;
import com.jason.model.dto.PostArticleDto;
import com.jason.model.entity.Article;
import com.jason.model.entity.Tag;
import com.jason.service.ArticleService;
import com.jason.service.CategoryService;
import com.jason.service.PostArticleService;
import com.jason.utils.MarkdownUtil;
import com.jason.utils.TagsConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/article_edit")
@Slf4j
public class AdminPostArticleController {

    @Autowired
    private PostArticleService postArticleService;

    @Autowired
    private ArticleService articleService ;

    @Autowired
    private CategoryService categoryService ;

    @Autowired
    private TagMapDao tagMapDao ;

    @RequestMapping(path = {"/",""})
    public String toArticleEditPage(@RequestParam(value = "id",required = false)Integer id,
                                    Model model,
                                    HttpServletRequest request){
        if(id != null){
            ArticleGetDTO articleGetDTO = new ArticleGetDTO() ;
            articleGetDTO.setId(id);
            articleGetDTO.setContents(true);
            List<Article> articles = articleService.getArticles(articleGetDTO);
            if(articles == null || articles.isEmpty()){
                throw new CustomizeException(CustomizeErrorCode.ARTICLE_NOT_FOUND) ;
            }
            Article article = articles.get(0) ;

            List<Tag> tagList = tagMapDao.getTagList(article.getId());

            String tags = TagsConverter.parse(tagList) ;
            model.addAttribute("tags",tags);
            model.addAttribute("article",article);

        }else{
            model.addAttribute("article",new Article());
        }
        model.addAttribute("categories",categoryService.getCategories());

        log.info("X-Pjax {}",request.getHeader("X-Pjax"));
        if(request.getHeader("X-Pjax") != null){
            return "admin/common-template/post-article" ;
        }

        return "admin/post_article";
    }

    @RequestMapping("save")
    public String saveArticle(PostArticleDto postArticleDto,
                              BindingResult result,
                              HttpServletRequest request){

        if(result.hasErrors()){
            throw new CustomizeException(CustomizeErrorCode.ILLEGAL_ERROR) ;
        }

        postArticleService.saveArticle(postArticleDto);

        log.info("X-Pjax {}",request.getHeader("X-Pjax"));
        return "redirect:/admin/article";
    }

}
