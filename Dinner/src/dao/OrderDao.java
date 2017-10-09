package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.Order;
import entity.OrderDetail;
import utils.JdbcUtils;

public class OrderDao implements IOrderDao {

	public void update(Order order) {
		// TODO Auto-generated method stub
		String sql = "update orders set orderStatus = ? where id = ?";
		try {
			JdbcUtils.getQueryRunner().update(sql,order.getOrderStatus(),order.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public Order findById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from orders where id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Order>(Order.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<Order> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from orders";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Order>(Order.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<OrderDetail> getById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from orderdetails where order_id = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<OrderDetail>(OrderDetail.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public void save(Order order) {
		// TODO Auto-generated method stub
		String sql = "insert into orders(id,table_id,orderDate,tableprice,orderStatus) values(?,?,?,?,?)";
		try {
			JdbcUtils.getQueryRunner().update(sql,order.getId(),order.getTable_id(),order.getOrderDate(),order.getTablePrice(),order.getOrderStatus());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public void save(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		String sql = "insert into orderdetails(order_id,food_id,foodCount) values(?,?,?)";
		try {
			JdbcUtils.getQueryRunner().update(sql,orderDetail.getOrder_id(),orderDetail.getFood_id(),orderDetail.getFoodCount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
