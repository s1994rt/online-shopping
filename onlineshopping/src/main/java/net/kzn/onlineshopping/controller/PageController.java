package net.kzn.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshoppingbackend.dao.CategoryDAO;
import net.kzn.onlineshoppingbackend.dto.Category;

@Controller
public class PageController {
	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "home");
		mv.addObject("clickOnHome", true);
		mv.addObject("categories", categoryDAO.categoryList());
		return mv;
	}

	@RequestMapping(value = { "/about" })
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About");
		mv.addObject("clickOnAbout", true);
		return mv;
	}

	@RequestMapping(value = { "/contact" })
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact");
		mv.addObject("clickOnContact", true);
		return mv;
	}
	/*
	 * for the view product
	 * */

	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
       ModelAndView mv=new ModelAndView("page");
       mv.addObject("title","All Products");
       mv.addObject("clickOnViewProducts",true);
       mv.addObject("categories",categoryDAO.categoryList());
       return mv;
	}
	
	/*
	 * for the categorry sidebar
	 * */
	
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
       ModelAndView mv=new ModelAndView("page");
       Category category=null;
       category=categoryDAO.getCategory(id);
       mv.addObject("title",category.getName());
       mv.addObject("clickOnCategoryProducts",true);
       mv.addObject("categories",categoryDAO.categoryList());
       mv.addObject("category",category);
       return mv;
	}

}
