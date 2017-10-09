package cn.com.shop.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.com.shop.cart.entity.Cart;
import cn.com.shop.category.entity.Category;
import cn.com.shop.category.service.CategoryService;
import cn.com.shop.product.entity.Product;
import cn.com.shop.product.service.ProductService;

@SuppressWarnings("serial")
public class IndexAction extends ActionSupport{
	//执行的访问首页的方法
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public String execute() {
		//查询所有一级分类的集合
		List<Category> cList = categoryService.findAll();
		//将一级分类存入session的范围
		
		ActionContext.getContext().getSession().put("cList", cList);
		//查询热门商品
		List<Product> hList = productService.findHot();
		// 保存到值栈中
		ActionContext.getContext().getValueStack().set("hList", hList);
		//查询最新商品
		List<Product> nList = productService.findNew();
		// 保存到值栈中
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}
