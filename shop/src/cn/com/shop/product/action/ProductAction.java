package cn.com.shop.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.com.shop.category.service.CategoryService;
import cn.com.shop.product.entity.Product;
import cn.com.shop.product.service.ProductService;
import cn.com.shop.utils.PageBean;

@SuppressWarnings("serial")
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product = new Product();
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	private Integer cid;
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	public Integer getCid() {
		return cid;
	}

	private Integer csid;
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	//根据商品的id查询商品
	public String findByPid() {
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	public String findByCid() {
		this.setCsid(null);
		//List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);
		//将pageBean存入到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	public String findByCsid() {
		this.setCid(null);
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
