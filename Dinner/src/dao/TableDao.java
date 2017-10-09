package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.Table;
import utils.JdbcUtils;

public class TableDao implements ITableDao{

	public void save(Table table) {
		// TODO Auto-generated method stub
		String sql = "insert into tables(tableName,tableStatus) values(?,?)";
		try {
			JdbcUtils.getQueryRunner().update(sql,table.getTableName(),table.getTableStatus());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void update(Table table) {
		// TODO Auto-generated method stub
		String sql = "update tables set tableName = ?, tableStatus = ?, orderDate = ? where id = ?";
		try {
			JdbcUtils.getQueryRunner().update(sql,table.getTableName(),table.getTableStatus(),table.getOrderDate(),table.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from tables where id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Table findById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from tables where id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Table>(Table.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<Table> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from tables";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Table>(Table.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<Table> getAll(String tableName) {
		// TODO Auto-generated method stub
		String sql = "select * from tables where tableName like ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Table>(Table.class), "%"+tableName+"%");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<Table> findByStatus(int status) {
		// TODO Auto-generated method stub
		String sql = "select * from tables where tableStatus=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Table>(Table.class),status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
