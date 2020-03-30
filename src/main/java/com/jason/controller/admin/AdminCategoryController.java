package com.jason.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jason.model.entity.Category;
import com.jason.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/category")
@Slf4j
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService ;

    @RequestMapping(path = {"/",""})
    public String toCategoryPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                 @RequestParam(value = "size",defaultValue = "5") Integer size,
                                 Model model,
                                 HttpServletRequest request){

        PageInfo<Category> categories = categoryService.getCategories(page,size);
        System.out.println(categories);
        model.addAttribute("categories",categories) ;


        log.info("X-Pjax {}",request.getHeader("X-Pjax"));
        if(request.getHeader("X-Pjax") != null){
            return "admin/common-template/category" ;
        }

        return "admin/category" ;
    }

    @RequestMapping("/list")
    public String listCategory(@RequestParam(value = "page",defaultValue = "1") Integer page,
                               @RequestParam(value = "size",defaultValue = "5") Integer size,
                               Model model){

        PageInfo<Category> categories = categoryService.getCategories(page,size);
        System.out.println(categories);
        model.addAttribute("categories",categories) ;
        return "admin/common-template/category::list";
    }

    @RequestMapping("/save")
    public String saveCategory(@RequestParam(value = "id",required = false) Integer id,
                               @RequestParam(value = "name",required = true) String name){

        if(id != null){
            Category category = new Category(id,name,null,null);
            categoryService.updateCategory(category);
        }else{
            Category category = new Category(null,name,null,null);
            categoryService.addCategory(category);
        }
        return "forward:/admin/category/list";
    }

    @RequestMapping("/delete")
    public String deleteCategory(@RequestParam(value = "id",required = true) Integer id){
        categoryService.deleteCategoryById(id);
        return "forward:/admin/category/list";
    }
}
