package cn.com.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.com.shop.category_second.entity.CategorySecond;
import cn.com.shop.user.entity.User;
import cn.com.shop.utils.PageHibernateCallback;

public class UserDao extends HibernateDaoSupport{
	//按名称查询是否存在此用户
	public User findByUserName(String username) {
		String hql = " from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql,username);
		if(list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	public void save(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	}

	public User findByCode(String code) {
		// TODO Auto-generated method stub
		String hql = " from User where code = ?";
		List<User> list = this.getHibernateTemplate().find(hql,code);
		if(list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	public void update(User existUser) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(existUser);
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		String hql = " from User where username = ? and password = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	public int findCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User";
		List<Long> list= this.getHibernateTemplate().find(hql);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<User> findByPage(int begin, int limit) {
		String hql = "from User order by uid desc";
		List<User> list = this.getHibernateTemplate().execute(new PageHibernateCallback<User>(hql, null, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	
	public void delete(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(user);
	}

	public User findByUid(Integer uid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(User.class, uid);
	}
 }
