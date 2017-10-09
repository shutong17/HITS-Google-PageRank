package cn.com.employee.service;

import cn.com.employee.domain.Employee;
import cn.com.employee.domain.PageBean;

//员工管理的业务层接口
public interface EmployeeService {

	Employee login(Employee employee);

	PageBean<Employee> findByPage(int currPage);

	void save(Employee employee);

	Employee findById(Integer eid);

	void update(Employee employee);

	void delete(Employee employee);
	
}
