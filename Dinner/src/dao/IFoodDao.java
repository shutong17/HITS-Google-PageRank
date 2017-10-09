package dao;

import java.util.List;

import entity.Food;
import utils.PageBean;

public interface IFoodDao {

	void save(Food food);

	void update(Food food);

	void delete(int id);

	Food findById(int id);

	List<Food> getAll();

		
	List<Food> getAll(String foodName);
	//分页且按条件查询所有的菜品
	void getAll(PageBean<Food> pageBean);
	//按条件统计菜品的总记录数
	int getTotalCount(PageBean<Food> pageBean);
}
