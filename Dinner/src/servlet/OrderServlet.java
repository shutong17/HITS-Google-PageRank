package servlet;

import java.io.IOException;

import java.util.Date;
import java.util.List;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Order;
import entity.OrderDetail;
import entity.Table;

@SuppressWarnings("serial")
public class OrderServlet extends BaseServlet {
	
	private Object uri;
	
	public Object list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			List<Order> list = orderService.getAll();
			// 保存
			for(int i = 0; i<list.size(); i++) {
				list.get(i).setTableName(tableService.findById(list.get(i).getTable_id()).getTableName());
				list.get(i).setOrderDate(tableService.findById(list.get(i).getTable_id()).getOrderDate());
			}
			request.setAttribute("listOrder", list);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/sys/order/order_list.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
	
	public Object viewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取请求id
			String id = request.getParameter("id");
			// 根据id查询对象
			Order order = orderService.findById(Integer.parseInt(id));
			List<OrderDetail> orderDetail = orderService.getById(order.getId());
			for(int i = 0; i<orderDetail.size();i++) {
				orderDetail.get(i).setFoodName(foodService.findById(orderDetail.get(i).getFood_id()).getFoodName());
				orderDetail.get(i).setPrice(foodService.findById(orderDetail.get(i).getFood_id()).getPrice());
			}
			request.setAttribute("listOrderDetail", orderDetail);
			uri =  request.getRequestDispatcher("/sys/order/order_detail.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
	
	
	
	public Object check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			String id = request.getParameter("id");
			Order order = orderService.findById(Integer.parseInt(id));
			if(order.getOrderStatus()==0){
				order.setOrderStatus(1);
				Table table = tableService.findById(orderService.findById(Integer.parseInt(id)).getTable_id());
				table.setTableStatus(0);
				Date date = null;
				table.setOrderDate(date);
				orderService.update(order);
				tableService.update(table);
			}
			uri = "/order?method=list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
	
}
