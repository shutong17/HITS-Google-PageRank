package servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Food;
import entity.FoodType;
import entity.Order;
import entity.OrderDetail;
import entity.Table;
import utils.Condition;
import utils.PageBean;

@SuppressWarnings("serial")
public class FrontServlet extends BaseServlet {
	private Object uri;

	public Object listTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<Food,Integer> map = new HashMap<Food,Integer>();
			HttpSession session = request.getSession();
			session.setAttribute("map", map);
			// 调用service查询所有的类别
			List<Table> emptyTable = tableService.findByStatus(0);
			// 保存
			request.setAttribute("listTable", emptyTable);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/app/index.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}

	// 前台菜单显示

	public Object menu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("Table");
			
			if (obj == null) {
				String tableID = request.getParameter("tableId");
				Table table = tableService.findById(Integer.parseInt(tableID));
				session.setAttribute("Table", table);
			}
			// 查询所以菜品信息
			// 分页参数设置
			PageBean<Food> pageBean = new PageBean<Food>();
			String curPage = request.getParameter("currentPage");
			if (curPage == null || "".equals(curPage.trim())) {
				pageBean.setCurrentPage(1);
			} else {
				pageBean.setCurrentPage(Integer.parseInt(curPage));
			}
			// 条件对象
			Condition condition = new Condition();
			// 分页参数： id
			String foodTypeId = request.getParameter("foodTypeId");
			if (foodTypeId != null && !"".equals(foodTypeId)) {
				condition.setFoodTypeId(Integer.parseInt(foodTypeId));
				request.setAttribute("curFoodTypeId", Integer.parseInt(foodTypeId));
			}
			// 分页参数： 名称
			String foodName = request.getParameter("foodName");
			condition.setFoodName(foodName);
			// 设置pageBean的条件对象
			pageBean.setCondition(condition);
			foodService.getAll(pageBean);
			request.setAttribute("pageBean", pageBean);
			// 菜系信息
			List<FoodType> listFoodType = foodTypeService.getAll();
			request.setAttribute("listFoodType", listFoodType);

			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/app/detail/menu.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}

	public Object submenu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			PageBean<Food> pageBean = new PageBean<Food>();
			String curPage = request.getParameter("currentPage");
			if (curPage == null || "".equals(curPage.trim())) {
				pageBean.setCurrentPage(1);
			} else {
				pageBean.setCurrentPage(Integer.parseInt(curPage));
			}
			// 条件对象
			Condition condition = new Condition();
			// 分页参数： id
			String foodTypeId = request.getParameter("foodTypeId");
			String search = request.getParameter("search");
			if ("true".equals(search)) {
				request.setAttribute("curFoodTypeId", 0);
			}

			if (foodTypeId != null && !"".equals(foodTypeId)) {
				condition.setFoodTypeId(Integer.parseInt(foodTypeId));
				request.setAttribute("curFoodTypeId", Integer.parseInt(foodTypeId));
			}
			// 分页参数： 名称
			String foodName = request.getParameter("foodName");
			condition.setFoodName(foodName);
			// 设置pageBean的条件对象
			pageBean.setCondition(condition);
			foodService.getAll(pageBean);
			request.setAttribute("pageBean", pageBean);
			// 菜系信息
			List<FoodType> listFoodType = foodTypeService.getAll();
			request.setAttribute("listFoodType", listFoodType);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/app/detail/submenu.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
	
	public Object detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("Table");
			if (obj == null) {
				String tableID = request.getParameter("tableId");
				Table table = tableService.findById(Integer.parseInt(tableID));
				session.setAttribute("Table", table);
			}
			String foodId = request.getParameter("foodId");
			Food food = foodService.findById(Integer.parseInt(foodId));
			request.setAttribute("food", food);
			List<FoodType> listFoodType = foodTypeService.getAll();
			request.setAttribute("listFoodType", listFoodType);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/app/detail/foodDetail.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}

	public Object order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Food,Integer> map = (Map<Food, Integer>) session.getAttribute("map");
			String foodId = request.getParameter("foodId");
			Food food = foodService.findById(Integer.parseInt(foodId));
			if(map.isEmpty()){
				map.put(food, 1);
			} 
			else {
				boolean find = false;
				for (Map.Entry<Food, Integer> entry : map.entrySet()) {
					if(entry.getKey().getId()==food.getId()){
						int value = map.get(entry.getKey());
						map.put(entry.getKey(), value+1);
						find = true;
					}
				}
				if(find == false){
					map.put(food, 1);
				}
			}
			session.setAttribute("map", map);
			DecimalFormat df = new DecimalFormat( "0.00 ");
			double totalPrice = 0;
			for (Map.Entry<Food, Integer> entry : map.entrySet()) {
				totalPrice+=entry.getKey().getPrice()*entry.getValue();
			}
			request.setAttribute("totalPrice", df.format(totalPrice));
			request.setAttribute("orderMap", map);
			List<FoodType> listFoodType = foodTypeService.getAll();
			request.setAttribute("listFoodType", listFoodType);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/app/detail/clientCart.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}

	public Object delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Food,Integer> map = (Map<Food, Integer>) session.getAttribute("map");
			String foodId = request.getParameter("foodId");
			Food food = foodService.findById(Integer.parseInt(foodId));
			for (Map.Entry<Food, Integer> entry : map.entrySet()) {
				if(entry.getKey().getId()==food.getId()){
					map.remove(entry.getKey());
					break;
				}
			}	
			session.setAttribute("map", map);
			DecimalFormat df = new DecimalFormat( "0.00 ");
			double totalPrice = 0;
			for (Map.Entry<Food, Integer> entry : map.entrySet()) {
				totalPrice+=entry.getKey().getPrice()*entry.getValue();
			}
			request.setAttribute("totalPrice", df.format(totalPrice));
			request.setAttribute("orderMap", map);
			List<FoodType> listFoodType = foodTypeService.getAll();
			request.setAttribute("listFoodType", listFoodType);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/app/detail/clientCart.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
	
	public Object orderUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Food,Integer> map = (Map<Food, Integer>) session.getAttribute("map");
			String foodId = request.getParameter("foodId");
			String number = request.getParameter("number");
			Food food = foodService.findById(Integer.parseInt(foodId));
			for (Map.Entry<Food, Integer> entry : map.entrySet()) {
					if(entry.getKey().getId()==food.getId()){
						map.put(entry.getKey(), Integer.parseInt(number));
						break;
					}
				}
			session.setAttribute("map", map);
			DecimalFormat df = new DecimalFormat( "0.00 ");
			double totalPrice = 0;
			for (Map.Entry<Food, Integer> entry : map.entrySet()) {
				totalPrice+=entry.getKey().getPrice()*entry.getValue();
			}
			request.setAttribute("totalPrice", df.format(totalPrice));
			request.setAttribute("orderMap", map);
			List<FoodType> listFoodType = foodTypeService.getAll();
			request.setAttribute("listFoodType", listFoodType);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/app/detail/clientCart.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
	
	public Object genernateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Food,Integer> map = (Map<Food, Integer>) session.getAttribute("map");
			Table table = (Table)session.getAttribute("Table");
			
			double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
			Order order = new Order();
			order.setId(order.hashCode());
			order.setOrderDate(new Date());
			order.setTable_id(table.getId());
			order.setOrderStatus(0);
			order.setTablePrice(totalPrice);
			orderService.save(order);
			for (Map.Entry<Food, Integer> entry : map.entrySet()) {
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrder_id(order.getId());
					orderDetail.setFood_id(entry.getKey().getId());
					orderDetail.setFoodCount(entry.getValue());
					orderService.save(orderDetail);
				}
			table.setTableStatus(1);
			table.setOrderDate(order.getOrderDate());
			tableService.update(table);
			request.setAttribute("totalPrice",totalPrice);
			request.setAttribute("orderMap", map);
			List<FoodType> listFoodType = foodTypeService.getAll();
			request.setAttribute("listFoodType", listFoodType);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/app/detail/clientOrderList.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
	public Object viewOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Food,Integer> map = (Map<Food, Integer>) session.getAttribute("map");
			DecimalFormat df = new DecimalFormat( "0.00 ");
			double totalPrice = 0;
			for (Map.Entry<Food, Integer> entry : map.entrySet()) {
				totalPrice+=entry.getKey().getPrice()*entry.getValue();
			}
			request.setAttribute("totalPrice", df.format(totalPrice));
			request.setAttribute("totalPrice",totalPrice);
			request.setAttribute("orderMap", map);
			List<FoodType> listFoodType = foodTypeService.getAll();
			request.setAttribute("listFoodType", listFoodType);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/app/detail/clientOrderList.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
}
