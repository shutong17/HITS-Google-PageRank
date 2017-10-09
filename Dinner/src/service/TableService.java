package service;

import java.util.List;


import dao.ITableDao;
import entity.Table;
import factory.BeanFactory;

public class TableService implements ITableService {
	private ITableDao tableDao = BeanFactory.getInstance("tableDao", ITableDao.class);
	public void save(Table table) {
		// TODO Auto-generated method stub
		tableDao.save(table);
	}

	public void update(Table table) {
		// TODO Auto-generated method stub
		tableDao.update(table);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		tableDao.delete(id);
	}

	public Table findById(int id) {
		// TODO Auto-generated method stub
		return tableDao.findById(id);
	}

	public List<Table> getAll() {
		// TODO Auto-generated method stub
		return tableDao.getAll();
	}

	public List<Table> getAll(String tableName) {
		// TODO Auto-generated method stub
		return tableDao.getAll(tableName);
	}

	public List<Table> findByStatus(int status) {
		// TODO Auto-generated method stub
		return tableDao.findByStatus(status);
	}

}
