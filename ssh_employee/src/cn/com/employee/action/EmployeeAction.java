package cn.com.employee.action;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.com.employee.domain.Department;
import cn.com.employee.domain.Employee;
import cn.com.employee.domain.PageBean;
import cn.com.employee.service.DepartmentService;
import cn.com.employee.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{
	private Employee employee = new Employee();
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	private int currPage = 1;
	public String login(){
		Employee currentEmployee = employeeService.login(employee);
		if(currentEmployee== null){
			//登陆失败
			this.addActionError("用户名或密码错误！");
			return INPUT;
		}else {
			ActionContext context = ActionContext.getContext();
			context.getSession().put("currentEmployee",currentEmployee);
			return SUCCESS;
		}
	
	}

	public Employee getModel() {
		// TODO Auto-generated method stub
		return employee;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	//分页查询员工的执行方法
	public String findAll() {
		PageBean<Employee> pageBean = employeeService.findByPage(currPage);
		 ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//跳转到添加员工的执行方法
	public String saveUI() {
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	//保存员工方法
	public String save() {
		employeeService.save(employee);
		return "save";
	}
	
	//编辑员工的方法
	public String edit() {
		employee = employeeService.findById(employee.getEid());
		//查询所有部门
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "edit";
	}
	
	//更新员工方法
	public String update() {
		employeeService.update(employee);
		return "update";
	}
	//删除员工方法
	public String delete() {
		employee = employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "delete";
	}
}
	

