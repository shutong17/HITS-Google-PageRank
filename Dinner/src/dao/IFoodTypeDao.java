package dao;

import java.util.List;

import entity.FoodType;

/*
 * 菜系模块Dao接口设计
 * */
public interface IFoodTypeDao {
	//添加
	void save(FoodType foodType);
	//更新
	void update(FoodType foodType);
	//删除
	void delete(int id);
	//根据主键查询
	FoodType findById(int id);
	//查询全部
	List<FoodType> getAll();
	//根据菜系名查询
	List<FoodType> getAll(String typeName);
}
