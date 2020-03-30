package com.jason.service;

import com.github.pagehelper.PageInfo;
import com.jason.model.entity.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(Integer id);

    PageInfo<Category> getCategories(Integer page, Integer size) ;

    List<Category> getCategories();

    List<Category> getCategoriesIncludePublish();

    PageInfo<Category> getCategoriesIncludePublish(Integer page, Integer size) ;

    void addCategory(Category category) ;

    void deleteCategoryById(Integer id) ;

    void updateCategory(Category category);
}
