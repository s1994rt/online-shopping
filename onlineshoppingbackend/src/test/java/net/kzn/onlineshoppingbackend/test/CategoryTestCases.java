package net.kzn.onlineshoppingbackend.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.Assert.assertEquals;

import org.h2.command.dml.Update;

import net.kzn.onlineshoppingbackend.dao.CategoryDAO;
import net.kzn.onlineshoppingbackend.dto.Category;

public class CategoryTestCases {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;

	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.onlineshoppingbackend");
		context.refresh();

		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");

	}


	@Test
	public void testCRUDCategory() {
		/*
		 * for adding the category
		 */
		category = new Category();
		category.setName("Tv");
		category.setActive(true);
		category.setDescription("this is for tv");
		category.setImageURL("IMG_4.jpg");
		assertEquals("Succefully added  category in a table", true, categoryDAO.add(category));

		category = new Category();
		category.setName("Radio");
		category.setActive(true);
		category.setDescription("this is for Radio");
		category.setImageURL("IMG_2.jpg");
		assertEquals("Succefully added  category in a table", true, categoryDAO.add(category));

		category = new Category();
		category.setName("Mobile");
		category.setActive(true);
		category.setDescription("this is for Mobile");
		category.setImageURL("IMG_3.jpg");

		assertEquals("Succefully added  category in a table", true, categoryDAO.add(category));

		/*
		 * for fetching the single category and updatting
		 */
		category = categoryDAO.get(2);
		category.setName("Phone");
		assertEquals("Succefully update  category in a table", true, categoryDAO.update(category));

		/*
		 * for deleting the category
		 */
		assertEquals("Succefully update  category in a table", true, categoryDAO.delete(category));

		/*
		 * for getting the list of category
		 */
		assertEquals("Succefully feetchig list of  category from a table",2, categoryDAO.categoryList().size());
		

	}

}
