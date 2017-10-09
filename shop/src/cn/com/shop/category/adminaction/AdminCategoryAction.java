package cn.com.shop.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.com.shop.category.entity.Category;
import cn.com.shop.category.service.CategoryService;

@SuppressWarnings("serial")
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	private Category category = new Category(); 
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String findAll() {
		List<Category> list = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", list);
		return "findAll";
	}
	
	public String save() {
		categoryService.save(category);
		return "save";
	}
	
	public String delete() {
		category = categoryService.finByCid(category.getCid());
		categoryService.delete(category);
		return "delete";
	}
	
	public String edit() {
		category = categoryService.finByCid(category.getCid());
		return "edit";
	}
	public String update() {
		categoryService.update(category);
		return "update";
	}
}
