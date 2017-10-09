package cn.com.shop.admin.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.com.shop.admin.entity.Admin;

public class AdminDao extends HibernateDaoSupport{

	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		String hql = "from Admin where username = ? and password = ?";
		List<Admin> list = this.getHibernateTemplate().find(hql, admin.getUsername(),admin.getPassword());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
