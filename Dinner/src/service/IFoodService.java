package service;

import java.util.List;

import entity.Food;
import utils.PageBean;


public interface IFoodService {
	//添加
	void save(Food food);
	//更新
	void update(Food food);
	//删除
	void delete(int id);
	//根据主键查询
	Food findById(int id);
	//查询全部
	List<Food> getAll();
	//根据菜系名查询
	List<Food> getAll(String typeName);
	
	void getAll(PageBean<Food> pageBean);
}
