package net.kzn.onlineshoppingbackend.dao;

import java.util.List;

import net.kzn.onlineshoppingbackend.dto.Category;

public interface CategoryDAO {
	
	Category get(int id);
	List<Category> categoryList();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);

}
