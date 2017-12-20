package net.kzn.onlineshoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.kzn.onlineshoppingbackend.dao.CategoryDAO;
import net.kzn.onlineshoppingbackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();
	static {

		Category category = new Category();
		category.setId(1);
		category.setName("Television");
		category.setDiscription("This is television");
		category.setImageURL("hud.jpg");
		categories.add(category);

		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDiscription("This is Mobile");
		category.setImageURL("hud.jpg");
		categories.add(category);

		category = new Category();
		category.setId(3);
		category.setName("Radio");
		category.setDiscription("This is Radio");
		category.setImageURL("hud.jpg");

		categories.add(category);
	}

	@Override
	public List<Category> categoryList() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category getCategory(int id) {
		for(Category category : categories) {
			if(category.getId()==id) {
				return category;
			}

		}
		return null;
	}

}
