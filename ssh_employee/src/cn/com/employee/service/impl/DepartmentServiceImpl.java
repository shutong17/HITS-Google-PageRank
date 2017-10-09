package cn.com.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.com.employee.dao.DepartmentDao;
import cn.com.employee.domain.Department;
import cn.com.employee.domain.PageBean;
import cn.com.employee.service.DepartmentService;
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentDao departmentDao;

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public PageBean<Department> findByPage(int currPage) {
		// TODO Auto-generated method stub
		PageBean<Department> pageBean = new PageBean<Department>();
		pageBean.setCurrPage(currPage);
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		int totalCount = departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		int begin = (currPage - 1)* pageSize;
		List<Department> list = departmentDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	//根据id查询部门
	public Department findById(Integer did) {
		// TODO Auto-generated method stub
		return departmentDao.findById(did);
	}

	public void save(Department department) {
		// TODO Auto-generated method stub
		departmentDao.save(department);
	}

	public void update(Department department) {
		// TODO Auto-generated method stub
		departmentDao.update(department);
	}

	public void delete(Department department) {
		// TODO Auto-generated method stub
		departmentDao.delete(department);
	}
	//查询所有部门的方法
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return departmentDao.findAll();
	}
	
}
