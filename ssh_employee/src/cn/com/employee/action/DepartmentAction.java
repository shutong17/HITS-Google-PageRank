package cn.com.employee.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.com.employee.domain.Department;
import cn.com.employee.domain.PageBean;
import cn.com.employee.service.DepartmentService;

public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {

	//模型驱动使用的对象
	private Department department = new Department();
	public Department getModel() {
		// TODO Auto-generated method stub
		return department;
	}
	
	//分页查询
	private int currPage = 1;
	
	
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}


	//提供一个查询的方法
	public String findAll(){
		PageBean<Department> pageBean = departmentService.findByPage(currPage);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//跳转添加页面
	public String saveUI() {
		return "saveUI";
	}
	//添加部门的方法
	public String save() {
		departmentService.save(department);
		return "save";
	}
	//编辑部门
	public String edit() {
		department= departmentService.findById(department.getDid());
		return "edit";
	}
	//修改部门完成时提交的方法
	public String update() {
		departmentService.update(department);
		return "update";
	}
	//删除部门的执行方法
	public String delete() {
		department = departmentService.findById(department.getDid());
		departmentService.delete(department);
		return "delete";
	}
}
