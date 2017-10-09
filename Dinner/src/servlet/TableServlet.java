package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Table;

@SuppressWarnings("serial")
public class TableServlet extends BaseServlet {
	
	private Object uri;
	
	public Object addTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1 获取请求的数据封装
			String tableName = request.getParameter("tableName");
			Table table = new Table();
			table.setTableName(tableName);
			table.setTableStatus(0);
			// 2调用service处理业务逻辑
			tableService.save(table);
			// 3跳转
			uri = "/table?method=list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
	
	public Object list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			List<Table> list = tableService.getAll();
			// 保存
			request.setAttribute("listTable", list);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/sys/table/table_list.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
	
	public Object viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取请求id
			String id = request.getParameter("id");
			// 根据id查询对象
			Table table = tableService.findById(Integer.parseInt(id));
			int tableStatus = table.getTableStatus();
			// 保存
			tableStatus = ((tableStatus+1) % 2 == 0) ? 0 : 1;
			if(tableStatus==1){
				table.setOrderDate(new Date());
			}else {
				Date date = null;
				table.setOrderDate(date);
			}
			table.setTableStatus(tableStatus);
			tableService.update(table);
			// 跳转
			uri = "/table?method=list";
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
			tableService.delete(Integer.parseInt(id));
			// 跳转
			uri =  "/table?method=list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}
	
	public Object search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service查询所有的类别
			String tableName = request.getParameter("keyword"); 
			List<Table> list = tableService.getAll(tableName);
			// 保存
			request.setAttribute("listTable", list);
			// 跳转到菜系列表页面
			uri = request.getRequestDispatcher("/sys/table/table_search.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		return uri;
	}

}
