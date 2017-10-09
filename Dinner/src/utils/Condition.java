package utils;

//封装条件
public class Condition {

	// 菜的类别
	private int foodTypeId = 0;
	// 菜的名称
	private String foodName ;

	public int getFoodTypeId() {
		return foodTypeId;
	}

	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

}
