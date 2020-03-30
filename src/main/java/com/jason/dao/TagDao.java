package com.jason.dao;

import com.jason.model.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao {

    List<Integer> getTagsId(Tag[] tags) ;

    void saveList(List<Tag> tags) ;

    int save(Tag tag);

    int remove(int[] ids);

    int update(Tag tag);

    Tag getOne(int id);

    List<Tag> getList(@Param("from")Integer from, @Param("pageSize") Integer pageSize, @Param("tag") Tag tag);

    int count(Tag tag);

    Tag getByName(String tagName);

    List<Tag> getAll();
}
