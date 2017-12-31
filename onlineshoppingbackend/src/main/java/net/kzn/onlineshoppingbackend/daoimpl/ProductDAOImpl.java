package net.kzn.onlineshoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.onlineshoppingbackend.dao.ProductDAO;
import net.kzn.onlineshoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product get(int id) {

		return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(id));
	}

	@Override
	public List<Product> listProduct() {

		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);
			return this.update(product);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}

	@Override
	public List<Product> listActiveProduct() {
		String selectActiveProduct = "FROM Product WHERE active= :active";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProduct, Product.class)
				.setParameter("active", true).getResultList();
	}

	@Override
	public List<Product> listActiveProductByCategory(int categoryId) {
		String selectActiveProductByCategory = "FROM Product WHERE active= :active and categoryId= :categoryId";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductByCategory, Product.class)
				.setParameter("active", true).setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	public List<Product> getLatestActivProduct(int count) {
		return sessionFactory.getCurrentSession().createQuery("FROM Product WHERE active= :active ORDER BY id", Product.class)
				.setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();
	}

	@Override
	public boolean add(Product product) {
		try {

			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
