package net.kzn.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
			   else if(operation.equals("category")) {
				   mv.addObject("message","Category Submitted Successfully !");
			   }
		   }
		   return mv;
	   }
	   //Handling for product submission
	   @RequestMapping(value="/products",method=RequestMethod.POST)
	   public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult results,Model model,
			   HttpServletRequest request) {
		   
		   //handle image validation for new product
		   if(mProduct.getId()==0) {
		   new ProductValidator().validate(mProduct,results);
		   }
		   else {
			   if(!mProduct.getFile().getOriginalFilename().equals("")) {
				   new ProductValidator().validate(mProduct,results);
			   }
		   }
		   
		   if(results.hasErrors()){
			   model.addAttribute("title","Manage Products");
			   model.addAttribute("userClickManageProducts",true);
			   model.addAttribute("message","Product Submission failed !");
			   return "page";
		   }
		   if(mProduct.getId()==0) {
			   //create a new product if id is 0
		   productDAO.add(mProduct);
		   }
		   else {
			   //update the product if id is not 0
			   productDAO.update(mProduct);
		   }
		   
		  if(!mProduct.getFile().getOriginalFilename().equals(" ")) {
			   FileUploadUtility.uploadFile( request,mProduct.getFile(),mProduct.getCode());
		   }
		   return "redirect:/manage/products?operation=product";
	   }
	   
	   //for edit the product details
	   @RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	   public ModelAndView editManageProduct(@PathVariable int id) {
		   ModelAndView mv=new ModelAndView("page");
		   mv.addObject("title","Manage Products");
		   mv.addObject("userClickManageProducts",true);
		   Product nProduct=productDAO.get(id);
		   mv.addObject(nProduct);
		   return mv;
	   }
	   
	   
	   //For activate and dactivate the prroduct
	   
	   @RequestMapping(value="/product/{id}/activation",method=RequestMethod.POST)
	   @ResponseBody
	   public String handleProductActivation(@PathVariable int id) {
		   Product product=productDAO.get(id);
		   boolean isActive=product.isActive();
		   product.setActive(!product.isActive());
		   productDAO.update(product);
		   return (isActive)?" You have Successfully deactivated the product with id:"+product.getId():
			   "You have Successfully activated the product with id:"+product.getId();
	   }
	   //controlling request for adding new category
	   
	   @RequestMapping(value="/category",method=RequestMethod.POST)
	   public String handleCategorySubmission( @ModelAttribute  Category category) {
		   categoryDAO.add(category);
		   return "redirect:/manage/products?operation=category";
	   }
	   
	   //Returning categories for all the request
	   @ModelAttribute("categories")
	   public List<Category> getCategories(){
		   
		   return categoryDAO.categoryList();
	   }
	   
	   @ModelAttribute("category")
	   public Category getCategory(){
		   
		   return new Category();
	   }
	   
}
