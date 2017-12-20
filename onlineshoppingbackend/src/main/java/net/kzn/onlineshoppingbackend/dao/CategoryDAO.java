package net.kzn.onlineshoppingbackend.dao;

import java.util.List;

import net.kzn.onlineshoppingbackend.dto.Category;

public interface CategoryDAO {
	
	List<Category> categoryList();
	Category getCategory(int id);

}
