package cn.com.shop.admin.service;

import org.springframework.transaction.annotation.Transactional;

import cn.com.shop.admin.dao.AdminDao;
import cn.com.shop.admin.entity.Admin;

@Transactional
public class AdminService {
	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.login(admin);
	}
	
}
