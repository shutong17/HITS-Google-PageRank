package cn.com.shop.category_second.entity;

import java.util.HashSet;
import java.util.Set;

import cn.com.shop.category.entity.Category;
import cn.com.shop.product.entity.Product;

public class CategorySecond {
	private Integer csid;
	private String csname;
	// 一级分类的对象
	private Category category;
	//配置商品集合
	private Set<Product> product = new HashSet<Product>();
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	
}
