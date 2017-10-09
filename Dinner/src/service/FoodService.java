package service;

import java.util.List;

import dao.IFoodDao;
import entity.Food;
import factory.BeanFactory;
import utils.PageBean;

public class FoodService implements IFoodService{
	private IFoodDao foodDao = BeanFactory.getInstance("foodDao", IFoodDao.class);
	public void save(Food food) {
		// TODO Auto-generated method stub
		try{
			foodDao.save(food);;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Food food) {
		// TODO Auto-generated method stub
		try{
			foodDao.update(food);;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		try{
			foodDao.delete(id);;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Food findById(int id) {
		// TODO Auto-generated method stub
		try{
			return foodDao.findById(id);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Food> getAll() {
		// TODO Auto-generated method stub
		try{
			return foodDao.getAll();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Food> getAll(String foodName) {
		// TODO Auto-generated method stub
		try{
			return foodDao.getAll(foodName);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void getAll(PageBean<Food> pageBean) {
		// TODO Auto-generated method stub
		try{
			foodDao.getAll(pageBean);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
