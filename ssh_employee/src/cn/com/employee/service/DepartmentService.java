package cn.com.employee.service;

import java.util.List;

import cn.com.employee.domain.Department;
import cn.com.employee.domain.PageBean;

public interface DepartmentService {

	PageBean<Department> findByPage(int currPage);

	Department findById(Integer did);

	void save(Department department);

	void update(Department department);

	void delete(Department department);

	List<Department> findAll();

}
