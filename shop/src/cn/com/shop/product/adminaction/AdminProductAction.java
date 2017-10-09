package cn.com.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.com.shop.category_second.entity.CategorySecond;
import cn.com.shop.category_second.service.CategorySecondService;
import cn.com.shop.product.entity.Product;
import cn.com.shop.product.service.ProductService;
import cn.com.shop.utils.PageBean;

@SuppressWarnings("serial")
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product = new Product();
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	//文件上传的参数
	private File upload;
	private String uploadFileName;
	 private String uploadContentType;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	public String addPage() {
		List<CategorySecond> list = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", list);
		return "addPage";	
	}
	
	public String save() throws IOException, ParseException {
		SimpleDateFormat temp =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		Date date = new Date();
		String date2=temp.format(date);  
        Date date3 = temp.parse(date2);
		product.setPdate(date3);
		if(upload!=null){
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile = new File(realPath + File.separator + uploadFileName);
			FileUtils.copyFile(upload,diskFile);
			
			product.setImage("products/"+uploadFileName);
		}
		productService.save(product);
		return "save";
	}
	
	public String delete() {
		product = productService.findByPid(product.getPid());
		String path = product.getImage();
		if(path!=null){
			String realPath = ServletActionContext.getServletContext().getRealPath("/" + path);
			File file = new File(realPath);
			file.delete();
		}
		productService.delete(product);
		return "delete";
	}
	
	public String edit() {
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "edit";
	}
	
	public String update() throws IOException, ParseException {
		SimpleDateFormat temp =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		Date date = new Date();
		String date2=temp.format(date);  
        Date date3 = temp.parse(date2);
		product.setPdate(date3);
		if(upload!=null){
			String path = product.getImage();
			String _realPath = ServletActionContext.getServletContext().getRealPath("/" + path);
			File file = new File(_realPath);
			file.delete();
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile = new File(realPath + File.separator + uploadFileName);
			FileUtils.copyFile(upload,diskFile);
			
			product.setImage("products/"+uploadFileName);
		}
		productService.update(product);
		return "update";
	}
}
