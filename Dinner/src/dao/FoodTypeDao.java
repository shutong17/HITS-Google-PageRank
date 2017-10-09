 package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.FoodType;
import utils.JdbcUtils;

public class FoodTypeDao implements IFoodTypeDao {

	public void save(FoodType foodType) {
		// TODO Auto-generated method stub
		String sql = "insert into foodtype(typeName) values(?)";
		try {
			JdbcUtils.getQueryRunner().update(sql,foodType.getTypeName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void update(FoodType foodType) {
		// TODO Auto-generated method stub
		String sql = "update foodtype set typeName = ? where id = ?";
		try {
			JdbcUtils.getQueryRunner().update(sql,foodType.getTypeName(),foodType.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from foodtype where id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FoodType findById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from foodtype where id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<FoodType>(FoodType.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<FoodType> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from foodtype";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<FoodType> getAll(String typeName) {
		// TODO Auto-generated method stub
		String sql = "select * from foodtype where typeName like ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class), "%"+typeName+"%");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
