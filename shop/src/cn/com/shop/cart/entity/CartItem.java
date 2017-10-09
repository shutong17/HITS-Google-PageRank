package cn.com.shop.cart.entity;

import cn.com.shop.product.entity.Product;

public class CartItem {
	private Product product;
	private int count;
	private double subtotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		subtotal = count*product.getShop_price();
		return count*product.getShop_price();
	}
	
	
	
}
