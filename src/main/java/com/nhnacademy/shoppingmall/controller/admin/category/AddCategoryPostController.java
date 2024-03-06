package com.nhnacademy.shoppingmall.controller.admin.category;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/addCategoryAction.do")
public class AddCategoryPostController implements BaseController {
    private final CategoryService<Category> categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

        @Override
        public String execute (HttpServletRequest req, HttpServletResponse resp){
            String categoryName = req.getParameter("category_name");

            try {
                Category newCategory = new Category(categoryName);
                categoryService.save(newCategory);
                req.setAttribute("successMessage", "카테고리가 성공적으로 추가되었습니다.");
            } catch (Exception e) {
                req.setAttribute("errorMessage", "카테고리 추가 중 오류가 발생했습니다.");
            }

            return "redirect:/admin/index.do";
        }
    }

