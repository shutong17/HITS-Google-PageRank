package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.Food;
import entity.FoodType;


@SuppressWarnings("serial")
public class FoodServlet extends BaseServlet {
	private Object uri;

	public Object list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			List<Food> list = foodService.getAll();
			// 保存
			/*for(int i = 0; i<list.size(); i++) {
					list.get(i).setCaixi(foodTypeService.findById(list.get(i).getFoodType_id()).getTypeName());
			}*/
			request.setAttribute("listFood", list);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/sys/food/food_list.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;

	}
	
	public Object addFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1 获取请求的数据封装
			String foodName = request.getParameter("foodName");
			String id = request.getParameter("cid");
			String price = request.getParameter("price");
			String mprice = request.getParameter("mprice");
			String introduce = request.getParameter("introduce");
			String file = request.getParameter("imageUrl");
			Food food = new Food();
			food.setFoodName(foodName);
			food.setImg(file);
			food.setRemark(introduce);
			food.setFoodType_id(Integer.parseInt(id));
			food.setPrice(Double.parseDouble(price));
			food.setMprice(Double.parseDouble(mprice));
			// 2调用service处理业务逻辑
			foodService.save(food);
			// 3跳转
			uri = "/food?method=list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
	
	public Object add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodType> foodType_list = foodTypeService.getAll();
		request.setAttribute("foodType_list", foodType_list);
		uri = request.getRequestDispatcher("/sys/food/food_save.jsp");
		return uri;
	}
	
	public Object viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取请求id
			String id = request.getParameter("id");
			// 根据id查询对象
			Food food = foodService.findById(Integer.parseInt(id));
			List<FoodType> ft = foodTypeService.getAll();
			// 保存
			request.setAttribute("food", food);
			request.setAttribute("foodType",ft);
			// 跳转
			uri =  request.getRequestDispatcher("/sys/food/food_update.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}

	public Object delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取请求id
			String id = request.getParameter("id");
			// 调用service
			foodService.delete(Integer.parseInt(id));
			// 跳转
			uri =  "/food?method=list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}

	public Object update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			String foodName = request.getParameter("foodName");
			String cid = request.getParameter("cid");
			String price = request.getParameter("price");
			String mprice = request.getParameter("mprice");
			String introduce = request.getParameter("introduce");
			String imageUrl = request.getParameter("imageUrl");
			Food food = new Food();
			food.setId(Integer.parseInt(id));
			food.setFoodName(foodName);
			food.setImg(imageUrl);
			food.setRemark(introduce);
			food.setFoodType_id(Integer.parseInt(cid));
			food.setPrice(Double.parseDouble(price));
			food.setMprice(Double.parseDouble(mprice));
			// 调用service更新
			foodService.update(food);
			// 跳转
			// this.list(request, response);
			uri = "/food?method=list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		//转发
		return uri;
	}

	public Object search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			String name = request.getParameter("keyword"); 
			List<Food> list = foodService.getAll(name);
			// 保存
			request.setAttribute("listFood", list);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/sys/food/food_search.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}

	
	
}
