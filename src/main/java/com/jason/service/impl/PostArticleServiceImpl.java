package com.jason.service.impl;

import com.jason.dao.ArticleDao;
import com.jason.dao.TagMapDao;
import com.jason.model.dto.PostArticleDto;
import com.jason.model.entity.Article;
import com.jason.model.entity.Tag;
import com.jason.service.PostArticleService;
import com.jason.service.TagService;
import com.jason.utils.TagsConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PostArticleServiceImpl implements PostArticleService {


    @Autowired
    private ArticleDao articleDao ;

    @Autowired
    private TagService tagService ;

    @Autowired
    private TagMapDao tagMapDao;

    @Transactional
    @Override
    public void saveArticle(PostArticleDto postArticleDto){
        Article article = new Article();
        BeanUtils.copyProperties(postArticleDto,article);
        Date date = new Date();
        article.setUpdateTime(date);
        article.getCategory().setId(postArticleDto.getCategoryId());

        Tag[] tags = TagsConverter.parse(postArticleDto.getTags());

        if(article.getId()==null){
            article.setViews(0);
            article.setCreateTime(date);
            if(tags != null){
                tagService.saveTags(tags);
                List<Integer> tagsId = tagService.getTagsId(tags);
                // 添加文章后设置Id
                articleDao.addArticle(article);
                tagMapDao.add(article.getId(), tagsId) ;
            }else{
                articleDao.addArticle(article);
            }
        }else{
            List<Integer> getTagList = tagMapDao.getTagIdList(article.getId());
            if(getTagList!=null&& !getTagList.isEmpty()){
                tagMapDao.remove(article.getId(), getTagList);
            }
            if(tags != null){
                tagService.saveTags(tags);
                List<Integer> tagsId = tagService.getTagsId(tags);
                tagMapDao.add(article.getId(), tagsId);
            }
            articleDao.updateArticle(article);
        }
    }


}
