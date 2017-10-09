package dao;

import java.util.List;

import entity.Order;
import entity.OrderDetail;

public interface IOrderDao {
	// 更新
	void update(Order order);

	Order findById(int id);

	void save(Order order);
	
	void save(OrderDetail orderDetail);
	
	// 查询全部
	List<Order> getAll();
	
	//订单明细
	List<OrderDetail> getById(int id);
}
