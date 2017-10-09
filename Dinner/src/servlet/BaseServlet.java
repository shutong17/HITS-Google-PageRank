package servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.BeanFactory;
import service.IFoodService;
import service.IFoodTypeService;
import service.IOrderService;
import service.ITableService;
import utils.WebUtils;

/**
 * Servlet implementation class BaseServlet
 */
@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet {
	protected ITableService tableService = BeanFactory.getInstance("tableService", ITableService.class);
	protected IFoodService foodService = BeanFactory.getInstance("foodService", IFoodService.class);
	protected IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
	protected IOrderService orderService = BeanFactory.getInstance("orderService", IOrderService.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取操作的类型
		String methodName = request.getParameter("method");
		Object returnValue = null;
		try {
			//获取当前类的字节码
			Class<? extends BaseServlet> clazz = this.getClass();
			//获取当前执行的方法的method类型
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//执行方法
			returnValue = method.invoke(this, request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnValue = "/error/error.jsp";
		}
			WebUtils.goTo(request, response, returnValue);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
