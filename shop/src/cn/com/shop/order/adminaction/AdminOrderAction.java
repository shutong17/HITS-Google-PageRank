package cn.com.shop.order.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.com.shop.order.entity.Order;
import cn.com.shop.order.entity.OrderItem;
import cn.com.shop.order.service.OrderService;
import cn.com.shop.utils.PageBean;

@SuppressWarnings("serial")
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
	private Order order = new Order();

	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String findAll() {
		PageBean<Order> pageBean = orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	public String findOrderItem() {
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	public String updateState() {
		Order _order = orderService.findByOid(order.getOid());
		_order.setState(3);
		orderService.update(_order);
		return "updateState";
	}
}
