package dao;

import java.util.List;

import entity.Table;

public interface ITableDao {
	void save(Table table);

	void update(Table table);

	void delete(int id);

	Table findById(int id);

	List<Table> getAll();

	List<Table> getAll(String tableName);
	
	List<Table> findByStatus(int status);

}
