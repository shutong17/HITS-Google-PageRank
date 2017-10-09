package cn.com.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.opensymphony.xwork2.ActionContext;

import cn.com.shop.product.entity.Product;
import cn.com.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport {

	public List<Product> findHot() {
		// TODO Auto-generated method stub
		// 使用离线条件查询。
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 查询热门商品
		criteria.add(Restrictions.eq("is_hot", 1));
		// 倒叙排序输出
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询
		List<Product> hList = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return hList;
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		// 使用离线条件查询。
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 倒叙排序输出
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询
		List<Product> nList = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		// 保存到值栈中
		
		return nList;
	}
	
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Product.class, pid);
		
	}

	public int findCountCid(Integer cid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid =?";
		//分页方法
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public int findCountCsid(Integer csid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list !=null && list.size() > 0){
			return list;
		}
		return null;
	}

	public int findCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
		
	}

	public List<Product> findByPage(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public void save(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(product);
	}

	public void delete(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(product);
	}

	public void update(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(product);
	}

}
