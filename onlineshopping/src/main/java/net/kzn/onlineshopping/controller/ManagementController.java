package net.kzn.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.util.FileUploadUtility;
import net.kzn.onlineshopping.validator.ProductValidator;
import net.kzn.onlineshoppingbackend.dao.CategoryDAO;
import net.kzn.onlineshoppingbackend.dao.ProductDAO;
import net.kzn.onlineshoppingbackend.dto.Category;
import net.kzn.onlineshoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	   @Autowired
	   private CategoryDAO categoryDAO;
	   
	   @Autowired
	   private ProductDAO productDAO;
	   
	   @RequestMapping(value="/products",method=RequestMethod.GET)
	   public  ModelAndView showManageProducts(@RequestParam(name="operation",required=false) String operation) {
		   
		   ModelAndView mv=new ModelAndView("page");
		   mv.addObject("title","Manage Products");
		   mv.addObject("userClickManageProducts",true);
		   //for new product
		   Product nProduct=new Product();
		   nProduct.setActive(true);
		   nProduct.setSupplierId(1);
		   mv.addObject("product",nProduct);
		   if(operation!=null) {
			   
			   if(operation.equals("product")) {
				   mv.addObject("message","Product Submitted Successfully !");
			   }
		   }
		   return mv;
	   }
	   //Handling for product submission
	   @RequestMapping(value="/products",method=RequestMethod.POST)
	   public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult results,Model model,
			   HttpServletRequest request) {
		   
		   new ProductValidator().validate(mProduct,results);
		   
		   if(results.hasErrors()){
			   model.addAttribute("title","Manage Products");
			   model.addAttribute("userClickManageProducts",true);
			   model.addAttribute("message","Product Submission failed !");
			   return "page";
		   }
		   
		   productDAO.add(mProduct);
		   
		  if(!mProduct.getFile().getOriginalFilename().equals(" ")) {
			   FileUploadUtility.uploadFile( request,mProduct.getFile(),mProduct.getCode());
		   }
		   return "redirect:/manage/products?operation=product";
	   }
	   
	   //Returning categories for all the request
	   @ModelAttribute("categories")
	   public List<Category> getCategories(){
		   
		   return categoryDAO.categoryList();
	   }
	   
}
