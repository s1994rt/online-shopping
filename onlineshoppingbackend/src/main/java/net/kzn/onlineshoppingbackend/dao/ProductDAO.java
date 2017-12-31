package net.kzn.onlineshoppingbackend.dao;

import java.util.List;

import net.kzn.onlineshoppingbackend.dto.Product;

public interface ProductDAO {

	Product get(int id);
	List<Product> listProduct();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	List <Product> listActiveProduct();
	List <Product> listActiveProductByCategory(int categoryId);
	List <Product> getLatestActivProduct(int count);
}
