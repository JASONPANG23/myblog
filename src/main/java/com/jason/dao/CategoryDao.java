package com.jason.dao;

import com.jason.model.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {

    /**
     * 获取分类列表
     * @return
     */
    List<Category> getCategories() ;

    /**
     * 获取包含已发布文章的分类
     * @return
     */
    List<Category> getCategoriesIncludePublish();

    /**
     * 根据分类ID获取分类
     * @param id
     * @return
     */
    Category getCategoryById(Integer id);

    /**
     * 添加分类
     * @param category
     */
    void addCategory(Category category) ;

    /**
     * 根据分类ID删除分类
     * @param id
     */
    void deleteCategoryById(Integer id) ;

    /**
     * 根据分类信息
     * @param category
     */
    void updateCategory(Category category);
}
