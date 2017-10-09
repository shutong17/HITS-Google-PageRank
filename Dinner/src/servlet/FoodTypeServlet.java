package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import entity.FoodType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 菜系管理servlet开发
 * 添加菜系
 * 菜系列表展示
 * */

@SuppressWarnings("serial")
public class FoodTypeServlet extends BaseServlet {
	// 跳转资源
	private Object uri;

	// 菜系添加
	public Object addFoodType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1 获取请求的数据封装
			String foodTypeName = request.getParameter("foodTypeName");
			FoodType ft = new FoodType();
			ft.setTypeName(foodTypeName);
			// 2调用service处理业务逻辑
			foodTypeService.save(ft);
			// 3跳转
			uri = "/foodType?method=list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}

	// 列表展示
	public Object list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			// 调用service查询所有的类别
			List<FoodType> list = foodTypeService.getAll();
			// 保存
			request.setAttribute("listFoodType", list);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/sys/foodtype/foodtype_list.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;

	}

	// 进入更新页面
	public Object viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取请求id
			String id = request.getParameter("id");
			// 根据id查询对象
			FoodType ft = foodTypeService.findById(Integer.parseInt(id));
			// 保存
			request.setAttribute("foodType", ft);
			// 跳转
			uri =  request.getRequestDispatcher("/sys/foodtype/foodtype_update.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}

	// 删除
	public Object delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取请求id
			String id = request.getParameter("id");
			// 调用service
			foodTypeService.delete(Integer.parseInt(id));
			// 跳转
			uri =  "/foodType?method=list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}

	// 更新操作
	public Object update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求数据
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("foodTypeName");
			FoodType foodType = new FoodType();
			foodType.setId(id);
			foodType.setTypeName(name);
			// 调用service更新
			foodTypeService.update(foodType);
			// 跳转
			// this.list(request, response);
			uri = "/foodType?method=list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		//转发
		return uri;
	}
	//搜索菜品
	public Object search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取搜索关键字
			String name = request.getParameter("keyword");
			// 调用service查询所有的类别
			//字符集转换
			System.out.println(name);
			List<FoodType> list = foodTypeService.getAll(name);
			// 保存
			request.setAttribute("listFoodType", list);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/sys/foodtype/foodtype_search.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
	
}
