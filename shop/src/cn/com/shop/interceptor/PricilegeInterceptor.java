package cn.com.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.com.shop.admin.entity.Admin;

@SuppressWarnings("serial")
public class PricilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub
		Admin admin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("AdminUser");
		if(admin == null) {
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("您还没有登录无权限访问！");
			return "loginFail";
		}else {
			return actionInvocation.invoke();
		}
	}

}
