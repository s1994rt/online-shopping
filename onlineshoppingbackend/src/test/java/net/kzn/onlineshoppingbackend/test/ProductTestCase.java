package net.kzn.onlineshoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.onlineshoppingbackend.dao.ProductDAO;
import net.kzn.onlineshoppingbackend.dto.Product;


public class ProductTestCase {
  private static AnnotationConfigApplicationContext context;
  private static ProductDAO productDAO;
  private Product product;
  
  @BeforeClass
  public static void init() {
	  context=new AnnotationConfigApplicationContext();
	  context.scan("net.kzn.onlineshoppingbackend");
	  context.refresh();
	  productDAO=(ProductDAO)context.getBean("productDAO");
  }
  
  @Test
  public void tesstCrudProduct() {
	  product=new Product();
	  product.setName("abc");
	  product.setCategoryId(2);
	  product.setQuantity(2);
	  product.setActive(true);
	  product.setSupplierId(1);
	  product.setUnitPrice(10000);
	  assertEquals("Successsfully added product in table",true,productDAO.add(product));
  }
  
}
