package com.jason.dao;

import com.jason.model.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapDao {

    void add(@Param("articleId") Integer  articleId, @Param("tagIdList") List<Integer> tagIdList);

    void remove(@Param("articleId") Integer  articleId,@Param("tagIdList") List<Integer> tagIdList);

    List<Integer> getTagIdList(Integer  articleId);

    List<Tag> getTagList(Integer articleId) ;

    void removeArticleIds(Integer[] articleIdList );

}
