package cn.com.shop.user.adminaction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.com.shop.user.entity.User;
import cn.com.shop.user.service.UserService;
import cn.com.shop.utils.PageBean;

@SuppressWarnings("serial")
public class AdminUserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	private UserService userService;
	private Integer page;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setPage(Integer page) {
		this.page = page;
	}

	public String findAll() {
		PageBean<User> pageBean = userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	public String delete() {
		User _user = userService.findByUid(user.getUid());
		userService.delete(_user);
		return "delete";
	}
	
	public String edit() {
		user = userService.findByUid(user.getUid());
		return "edit";
	}
	
	public String update() {
		user.setCode(null);
		userService.update(user);
		return "update";
		
	}
}
