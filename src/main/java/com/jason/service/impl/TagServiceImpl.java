package com.jason.service.impl;

import com.jason.dao.TagDao;
import com.jason.exception.CustomizeErrorCode;
import com.jason.exception.CustomizeException;
import com.jason.model.entity.Tag;
import com.jason.service.TagService;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForReadableInstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public List<Integer> getTagsId(Tag[] tags) {
        return tagDao.getTagsId(tags) ;
    }

    /**
     * 如果该标签不存在则添加标签
     * @param tags
     */
    @Override
    @Transactional
    public void saveTags(Tag[] tags) {
        List<Tag> all = tagDao.getAll();
        List<Tag> insertList = new ArrayList<>() ;


        for (Tag tag : tags) {
            boolean flag = true;
            for (Tag value : all) {
                if (tag.getName().equals(value.getName())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                insertList.add(tag);
            }
        }

        if(!insertList.isEmpty())
            tagDao.saveList(insertList);
    }

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagDao.save(tag);
    }

    @Transactional
    @Override
    public int removeTag(int[] ids) {
        return tagDao.remove(ids);
    }

    @Transactional
    @Override
    public int updateTag(Tag tag) {
        return tagDao.update(tag);
    }

    @Override
    public Tag getTag(int id) {
        Tag one = tagDao.getOne(id);
        if (one==null){
            throw new CustomizeException(CustomizeErrorCode.TAG_NOT_FOUND);
        }
        return one;
    }

    @Override
    public List<Tag> getTagList(Integer currentPage, Integer pageSize, Tag tag) {
        List<Tag> list = tagDao.getList((currentPage - 1) * pageSize, pageSize, tag);
        if (list==null){
            throw new CustomizeException(CustomizeErrorCode.TAG_NOT_FOUND);
        }
        return list;
    }

    @Override
    public int countTag(Tag tag) {
        return tagDao.count(tag);
    }

    @Override
    public Tag getTagByName(String tagName) {
        return tagDao.getByName(tagName);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAll();
    }
}
