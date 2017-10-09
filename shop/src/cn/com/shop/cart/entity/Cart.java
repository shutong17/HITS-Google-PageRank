package cn.com.shop.cart.entity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Cart implements java.io.Serializable{
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer,CartItem>();
	private double total;
	
	public double getTotal() {
		return total;
	}
	private Collection<CartItem> cartItems;
	public Collection<CartItem> getCartItems() {
		cartItems = map.values();
		return cartItems;
	}
	//购物车的功能:1购物项加入购物车2移除购物项3清空购物车
	public void addCart(CartItem cartItem ) {
		Integer pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else {
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal();
	}
	public void clearCart() {
		//购物项清空，总计为零
		map.clear();
		this.total = 0;
	}
	public void removeCart(Integer pid) {
		CartItem cartItem = map.remove(pid);
		this.total = total - cartItem.getSubtotal() ;
	}
}
