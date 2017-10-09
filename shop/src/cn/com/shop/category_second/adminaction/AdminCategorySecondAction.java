package cn.com.shop.category_second.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.com.shop.category.entity.Category;
import cn.com.shop.category.service.CategoryService;
import cn.com.shop.category_second.entity.CategorySecond;
import cn.com.shop.category_second.service.CategorySecondService;
import cn.com.shop.utils.PageBean;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	private CategorySecond  categorySecond = new CategorySecond();
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}

	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService.finByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String addPage() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPage";
	}
	
	public String save() {
		categorySecondService.save(categorySecond);
		return "save";
	}
	
	public String delete() {
		//级联删除，先查寻再删除
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "delete";
	}
	
	public String edit() {
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cLsit = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cLsit);
		return "edit";
	}
	
	public String update() {
		categorySecondService.update(categorySecond);
		return "update";
	}
}
