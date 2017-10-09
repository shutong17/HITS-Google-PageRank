package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import entity.Food;
import utils.Condition;
import utils.JdbcUtils;
import utils.PageBean;


public class FoodDao implements IFoodDao {

	public void save(Food food) {
		// TODO Auto-generated method stub
		String sql = "insert into food(foodName,foodType_id,price,mprice,remark,img) values(?,?,?,?,?,?)";
		try {
			JdbcUtils.getQueryRunner().update(sql,food.getFoodName(),food.getFoodType_id(),food.getPrice(),food.getMprice(),food.getRemark(),food.getImg());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void update(Food food) {
		// TODO Auto-generated method stub
		String sql = "update food set foodName = ?, foodType_id = ?,price = ?,mprice = ?, remark = ?,img = ? where id = ?";
		try {
			JdbcUtils.getQueryRunner().update(sql,food.getFoodName(),food.getFoodType_id(),food.getPrice(),food.getMprice(),food.getRemark(),food.getImg(),food.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from food where id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Food findById(int id) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("select");
		sb.append(" f.id,");
		sb.append(" f.foodName,");
		sb.append(" f.price,");
		sb.append(" f.mprice,");
		sb.append(" f.remark,");
		sb.append(" f.img,");
		sb.append(" f.foodType_id,");
		sb.append(" t.typeName");
		sb.append(" from");
		sb.append(" food f,");
		sb.append(" foodtype t");
		sb.append(" where");
		sb.append(" f.foodType_id=t.id");
		sb.append(" and f.id=?");
		try {
			return JdbcUtils.getQueryRunner().query(sb.toString(), new BeanHandler<Food>(Food.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<Food> getAll() {
		/*// TODO Auto-generated method stub
		String sql = "select * from food";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Food>(Food.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}*/
		StringBuffer sb = new StringBuffer();
		sb.append("select");
		sb.append(" f.id,");
		sb.append(" f.foodName,");
		sb.append(" f.price,");
		sb.append(" f.mprice,");
		sb.append(" f.remark,");
		sb.append(" f.img,");
		sb.append(" f.foodType_id,");
		sb.append(" t.typeName");
		sb.append(" from");
		sb.append(" food f,");
		sb.append(" foodtype t");
		sb.append(" where");
		sb.append(" f.foodType_id=t.id");
		try {
			return JdbcUtils.getQueryRunner().query(sb.toString(), new BeanListHandler<Food>(Food.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<Food> getAll(String name) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("select");
		sb.append(" f.id,");
		sb.append(" f.foodName,");
		sb.append(" f.price,");
		sb.append(" f.mprice,");
		sb.append(" f.remark,");
		sb.append(" f.img,");
		sb.append(" f.foodType_id,");
		sb.append(" t.typeName");
		sb.append(" from");
		sb.append(" food f,");
		sb.append(" foodtype t");
		sb.append(" where");
		sb.append(" f.foodType_id=t.id");
		sb.append(" and f.foodName like ?");
		try {
			return JdbcUtils.getQueryRunner().query(sb.toString(), new BeanListHandler<Food>(Food.class), "%"+name+"%");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void getAll(PageBean<Food> pageBean) {
		// TODO Auto-generated method stub
		//获取条件对象
		Condition condition = pageBean.getCondition();
		//条件类别id
		int typeId = condition.getFoodTypeId();
		//条件之菜品名
		String foodName = condition.getFoodName();
		StringBuffer sb = new StringBuffer();
		sb.append("select");
		sb.append(" f.id,");
		sb.append(" f.foodName,");
		sb.append(" f.price,");
		sb.append(" f.mprice,");
		sb.append(" f.remark,");
		sb.append(" f.img,");
		sb.append(" f.foodType_id,");
		sb.append(" t.typeName");
		sb.append(" from");
		sb.append(" food f,");
		sb.append(" foodtype t");
		sb.append(" where");
		sb.append(" f.foodType_id=t.id");
		//拼接查询条件
		//类别id
		List<Object> list = new ArrayList<Object>();
		if (typeId > 0){
			sb.append(" and f.foodType_id=?");
			list.add(typeId);
		}
		//菜品名称
		if(foodName != null &&!"".equals(foodName.trim())) {
			sb.append(" and f.foodName like ?");
			list.add("%"+foodName+"%");
		}
		//分页条件
		sb.append(" limit ?,?");
		//判断当前页
		//查询总记录数
		int totalCount = this.getTotalCount(pageBean);
		
		pageBean.setTotalCount(totalCount);
		pageBean.setPageCount(6);
		pageBean.setTotalPage(pageBean.getTotalPage());
		if(pageBean.getCurrentPage()<1){
			pageBean.setCurrentPage(1);
		} else if(pageBean.getCurrentPage()>pageBean.getTotalPage()){
			pageBean.setCurrentPage(pageBean.getTotalPage());
		}
		int index = (pageBean.getCurrentPage() - 1) * pageBean.getPageCount();
		int count = pageBean.getPageCount();
		
		list.add(index);
		list.add(count);
		try {
			List<Food> pageData =  JdbcUtils.getQueryRunner().query(sb.toString(), new BeanListHandler<Food>(Food.class),list.toArray());
			pageBean.setPageData(pageData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public int getTotalCount(PageBean<Food> pageBean) {
		// TODO Auto-generated method stub
		Condition condition = pageBean.getCondition();
		//条件类别id
		int typeId = condition.getFoodTypeId();
		//条件之菜品名
		String foodName = condition.getFoodName();
		StringBuffer sb = new StringBuffer();
		sb.append("select");
		sb.append(" count(*)");
		sb.append(" from");
		sb.append(" food f,");
		sb.append(" foodtype t");
		sb.append(" where");
		sb.append(" f.foodType_id=t.id");
		//拼接查询条件
		//类别id
		List<Object> list = new ArrayList<Object>();
		if (typeId > 0){
			sb.append(" and f.foodType_id=?");
			list.add(typeId);
		}
		//菜品名称
		if(foodName != null &&!"".equals(foodName.trim())) {
			sb.append(" and f.foodName like ?");
			list.add("%"+foodName+"%");
			System.out.println(foodName+"哈哈哈");
		}
		try {
			Long num = JdbcUtils.getQueryRunner().query(sb.toString(), new ScalarHandler<Long>(),list.toArray());
			return num.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}

}
