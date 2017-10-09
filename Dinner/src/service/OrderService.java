package service;

import java.util.List;

import dao.IOrderDao;
import entity.Order;
import entity.OrderDetail;
import factory.BeanFactory;

public class OrderService implements IOrderService {
	private IOrderDao orderDao = BeanFactory.getInstance("orderDao", IOrderDao.class);
	public void update(Order order) {
		// TODO Auto-generated method stub
		orderDao.update(order);
	}

	public Order findById(int id) {
		return orderDao.findById(id);
	}

	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return orderDao.getAll();
	}

	public List<OrderDetail> getById(int id) {
		// TODO Auto-generated method stub
		return orderDao.getById(id);
	}

	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}

	public void save(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		orderDao.save(orderDetail);
	}
	
}
