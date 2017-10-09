package cn.com.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.com.shop.cart.entity.Cart;
import cn.com.shop.cart.entity.CartItem;
import cn.com.shop.product.entity.Product;
import cn.com.shop.product.service.ProductService;

public class CartAction extends ActionSupport{
	//接受pid count
	private Integer pid;
	private Integer count;
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String addCart(){
		//封装一个CartItem对象
		CartItem cartItem = new CartItem();
		Product product = productService.findByPid(pid);
		cartItem.setCount(count);
		cartItem.setProduct(product);
		Cart cart= getCart();
		cart.addCart(cartItem);
		ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		return "addCart";
	}

	private Cart getCart() {
		// TODO Auto-generated method stub
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	public String clearCart() {
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}
	
	public String removeCart() {
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	
	public String myCart() {
		return "myCart";
	}
}
