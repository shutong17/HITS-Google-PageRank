package cn.com.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.com.shop.category.dao.CategoryDao;
import cn.com.shop.category.entity.Category;

@Transactional
public class CategoryService {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	public Category finByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}

	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}

	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}
	
}
