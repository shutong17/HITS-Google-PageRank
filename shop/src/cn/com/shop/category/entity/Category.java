package cn.com.shop.category.entity;

import java.util.HashSet;
import java.util.Set;

import cn.com.shop.category_second.entity.CategorySecond;

@SuppressWarnings("serial")
public class Category  implements java.io.Serializable {
	private Integer cid;
	private String cname;
	//一级分类中存放二级分类集合
	private Set<CategorySecond>  categorySecond = new HashSet<CategorySecond>();
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Set<CategorySecond> getCategorySecond() {
		return categorySecond;
	}

	public void setCategorySecond(Set<CategorySecond> categorySecond) {
		this.categorySecond = categorySecond;
	}
	
	
}
