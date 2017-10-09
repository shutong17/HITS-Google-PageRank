package cn.com.shop.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.com.shop.category_second.entity.CategorySecond;
import cn.com.shop.user.dao.UserDao;
import cn.com.shop.user.entity.User;
import cn.com.shop.utils.MailUtils;
import cn.com.shop.utils.PageBean;
import cn.com.shop.utils.UUIDUtils;

@Transactional
public class UserService {
	// 注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	public void save(User user) {
		// TODO Auto-generated method stub
		user.setState(0);// 0代表未激活，1代表激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		MailUtils.sendMail(user.getEmail(), code);
	}

	public User findByCode(String code) {
		// TODO Auto-generated method stub

		return userDao.findByCode(code);
	}

	public void update(User existUser) {
		// TODO Auto-generated method stub
		userDao.update(existUser);
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	public PageBean<User> findByPage(Integer page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		PageBean<User> pageBean = new PageBean<User>();
		pageBean.setPage(page);
		int limit = 10;
		pageBean.setLimit(limit);
		int totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * limit;

		List<User> list = userDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;

	}

	public void delete(User user) {
		// TODO Auto-generated method stub
		userDao.delete(user);
	}

	public User findByUid(Integer uid) {
		// TODO Auto-generated method stub
		return userDao.findByUid(uid);
	}
}
