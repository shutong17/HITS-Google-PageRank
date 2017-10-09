package cn.com.shop.order.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.com.shop.cart.entity.Cart;
import cn.com.shop.cart.entity.CartItem;
import cn.com.shop.order.entity.Order;
import cn.com.shop.order.entity.OrderItem;
import cn.com.shop.order.service.OrderService;
import cn.com.shop.user.entity.User;
import cn.com.shop.utils.PageBean;
import cn.com.shop.utils.PaymentUtil;

public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	private Order order = new Order();
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}

	private String pd_FrpId;
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	private String r6_Order;
	private String r3_Amt;
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public String save() throws ParseException {
		//1保存数据到数据库
		//订单数据补全
		SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		Date date = new Date();
		String date2=temp.format(date);  
        Date date3 = temp.parse(date2);  
		order.setOrdertime(date3);
		order.setState(1); // 1 未付款 2已经付款，没有发货， 3 已经发货，没用确认收获 4  交易完成
		//总计是购物车的数据信息
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null) {
			this.addActionError("亲！您还没有购物，请先去购物");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		//设置订单用户
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(existUser == null) {
			this.addActionError("您还没有登录！请先去登录");
			return "login";
		}
		order.setUser(existUser);
		//2将订单对象显示在页面上
		orderService.save(order);
		return "save";
	}
	
	public String findByUid() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		PageBean<Order> pageBean = orderService.findByUid(user.getUid(),page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}
	
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}
		
	public String payOrder() throws IOException {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		
		String p0_Cmd="Buy";
		String p1_MerId="10001126856";
		String p2_Order=order.getOid().toString();
		String p3_Amt="0.01";
		String p4_Cur="CNY";
		String p5_Pid="";
		String p6_Pcat="";
		String p7_Pdesc="";
		String p8_Url="http://localhost:8080/shop/order_callBack.action";
		String p9_SAF="";
		String pa_MP="";
		String pd_FrpId = this.pd_FrpId;
		String pr_NeedResponse="1";
		String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		StringBuffer stringBuffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		stringBuffer.append("p2_Order=").append(p2_Order).append("&");
		stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		stringBuffer.append("p8_Url=").append(p8_Url).append("&");
		stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		stringBuffer.append("pa_MP=").append(pa_MP).append("&");
		stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		stringBuffer.append("hmac=").append(hmac);
		
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		return NONE;
	}
	
	public String callBack() {
		//修改状态：已经付款
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);
		orderService.update(currOrder);
		//显示信息
		this.addActionMessage("订单付款成功! 订单编号："+r6_Order+"订单金额："+r3_Amt);
		return "msg";
	}
	
	public String updateState() {
		Order _order = orderService.findByOid(order.getOid());
		_order.setState(4);
		orderService.update(_order);
		
		return "updateState";
		
	}
}
