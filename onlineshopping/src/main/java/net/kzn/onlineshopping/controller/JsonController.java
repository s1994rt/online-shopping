package net.kzn.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.kzn.onlineshoppingbackend.dao.ProductDAO;
import net.kzn.onlineshoppingbackend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getListActiveProduct(){
		
		return productDAO.listActiveProduct();
	}
	
	@RequestMapping("/admin/all/products")
	@ResponseBody
	public List<Product> getListProduct(){
		
		return productDAO.listProduct();
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> activeProductsByCategory(@PathVariable int id){
		
		return productDAO.listActiveProductByCategory(id);
		
	}

}
