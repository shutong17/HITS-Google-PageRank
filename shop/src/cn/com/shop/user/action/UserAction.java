package cn.com.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.com.shop.user.entity.User;
import cn.com.shop.user.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String checkcode;
	
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	//跳转到注册的页面方法
	public String registPage() {
		return "registPage";
	}
	
	//Ajax进行异步校验用户名的执行方法
	public String findByUserName() throws IOException {
		//调用service查询
		User exitUser = userService.findByUserName(user.getUsername());
		//获取response对象，向页面输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("tetx/html;charset=UTF-8");
		//判断
		if(exitUser != null) {
			//用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}else {
			// 用户名可以使用
			response.getWriter().println("<font color='green'>用户名合法，可以使用</font>");
		}
		return NONE;
	}
	
	public String regist() {
		String checkCode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkCode)) {
			this.addActionError("您的验证码输入错误");
			return "codeerror1";
		}
		userService.save(user);
		this.addActionMessage("注册成功，请去邮箱激活");
		return "msg";
	}
	public String active() {
		User existUser = userService.findByCode(user.getCode());
		if(existUser == null) {
			this.addActionMessage("激活失败：激活码已过期！");
		}else {
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功：请去登录！");
		}
		return "msg";
	}
	
	public String loginPage() {
		return "loginPage";
	}
	
	public String login() {
		String checkCode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkCode)) {
			this.addActionError("您的验证码输入错误");
			return "codeerror2";
		}
		User existUser = userService.login(user);
		if(existUser == null) {
			this.addActionError("登录失败：用户名和密码不匹配，或者用户未激活");
			return LOGIN;
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("user", existUser);
			return SUCCESS;
		}
	}
	
	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}
}
