package cn.com.shop.admin.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.com.shop.admin.entity.Admin;
import cn.com.shop.admin.service.AdminService;

@SuppressWarnings("serial")
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {
	private Admin admin = new Admin();
	public Admin getModel() {
		// TODO Auto-generated method stub
		return admin;
	}
	private AdminService adminService;
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public String login() {
		Admin existAdmin = adminService.login(admin);
		if(existAdmin == null) {
			this.addActionError("您的用户名或者密码错误！");
			return "loginFail";
		}
		ServletActionContext.getRequest().getSession().setAttribute("AdminUser", existAdmin);
		return "loginSuccess";
	}
}
