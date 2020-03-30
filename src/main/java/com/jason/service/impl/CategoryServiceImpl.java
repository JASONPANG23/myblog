package com.jason.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jason.dao.ArticleDao;
import com.jason.dao.CategoryDao;
import com.jason.exception.CustomizeErrorCode;
import com.jason.exception.CustomizeException;
import com.jason.model.entity.Category;
import com.jason.service.ArticleService;
import com.jason.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao ;

    @Transactional(readOnly = true)
    public Category getCategoryById(Integer id){
        Category category = categoryDao.getCategoryById(id);
        if(category == null)
            throw new CustomizeException(CustomizeErrorCode.CATEGORY_NOT_FOUND) ;
        return category ;
    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo<Category> getCategories(Integer page,Integer size) {
        PageHelper.startPage(page,size);
        List<Category> categories = categoryDao.getCategories();
        return new PageInfo<>(categories);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }

    @Override
    public List<Category> getCategoriesIncludePublish() {
        return categoryDao.getCategoriesIncludePublish();
    }

    @Override
    public PageInfo<Category> getCategoriesIncludePublish(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Category> categories = categoryDao.getCategoriesIncludePublish();
        return new PageInfo<>(categories);
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }

    @Override
    @Transactional
    public void deleteCategoryById(Integer id) {
        categoryDao.deleteCategoryById(id);
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        categoryDao.updateCategory(category);
    }
}
