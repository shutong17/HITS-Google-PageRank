package service;

import java.util.List;

import entity.Table;

public interface ITableService {
	//添加
		void save(Table table);
		//更新
		void update(Table table);
		//删除
		void delete(int id);
		//根据主键查询
		Table findById(int id);
		//查询全部
		List<Table> getAll();
		//根据菜系名查询
		List<Table> getAll(String tableName);
		//根据餐桌状态查询
		List<Table> findByStatus(int status);
}
