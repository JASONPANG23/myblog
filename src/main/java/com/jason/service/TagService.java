package com.jason.service;

import com.jason.model.entity.Tag;

import java.util.List;

public interface TagService {

    List<Integer> getTagsId(Tag[] tags) ;

    void saveTags(Tag[] tags) ;

    int saveTag(Tag tag);

    int removeTag(int[] ids);

    int updateTag(Tag tag);

    Tag getTag(int id);

    List<Tag> getTagList(Integer currentPage, Integer pageSize, Tag tag);

    int countTag(Tag Tag);

    Tag getTagByName(String tagName);

    List<Tag> getAllTag();
}
