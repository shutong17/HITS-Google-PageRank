package cn.com.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.com.shop.product.dao.ProductDao;
import cn.com.shop.product.entity.Product;
import cn.com.shop.utils.PageBean;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHot() {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}

	public PageBean<Product> findByPageCid(Integer cid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0)
			totalPage = totalCount / limit;
		else 
			totalPage = totalCount / limit + 1;
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		int begin = (page-1) * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0)
			totalPage = totalCount / limit;
		else 
			totalPage = totalCount / limit + 1;
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		int begin = (page-1) * limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置记录数
		int limit = 10;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCount();
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0)
			totalPage = totalCount / limit;
		else 
			totalPage = totalCount / limit + 1;
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		int begin = (page-1) * limit;
		List<Product> list = productDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		// TODO Auto-generated method stub
		productDao.save(product);
	}

	public void delete(Product product) {
		// TODO Auto-generated method stub
		productDao.delete(product);
	}

	public void update(Product product) {
		// TODO Auto-generated method stub
		productDao.update(product);
	}
	
}
