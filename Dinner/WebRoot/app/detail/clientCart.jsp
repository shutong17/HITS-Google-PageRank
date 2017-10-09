<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/detail/style/css/index.css" />
	<script type="text/javascript">
		 // 删除菜品项
		function removeSorder(id) {
			window.location.href = "${pageContext.request.contextPath}/front?method=delete&foodId="+id;
		}
		
		 // 修改菜品项数量
		function alterSorder(node) {
			var number = node.value;
			var id = node.lang;
			window.location.href = "${pageContext.request.contextPath}/front?method=orderUpdate&foodId="+id+"&number="+number;
		} 
		// 下单
		function genernateOrder() {
			window.location.href = "${pageContext.request.contextPath}/front?method=genernateOrder&totalPrice=${totalPrice}";
		}
	</script>
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 餐车div -->
			<div id="count">
				<table align="center" width="100%">
					<tr height="40">
				 		<td align="center" width="20%">菜名</td>
				 		<td align="center" width="20%">单价</td>
				 		<td align="center" width="20%">数量</td>
				 		<td align="center" width="20%">小计</td>
				 		<td align="center" width="20%">操作</td>
				 	</tr>
				 	<c:choose>
						<c:when test="${not empty requestScope.orderMap}">
							<c:forEach var="food" items="${requestScope.orderMap}">
								<tr height="60"> 
									<td align="center" width="20%">${food.key.foodName}</td>
					 				<td align="center" width="20%">${food.key.price}</td>
					 				<td align="center" width="20%">
					 					<input type="text" name ="count" value="${food.value}" size="3" lang="${food.key.id}" onblur="alterSorder(this)"/>
					 				</td>
					 				<td align="center" width="20%">${food.key.price*food.value}</td>
					 				<td align="center" width="20%">
					 					<input type="button" value="删除" class="btn_next" onclick="removeSorder(${food.key.id})" />
					 				</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							没有你要找的数据，请先保存记录再查看
						</c:otherwise>
					</c:choose>
					
					<tr>
						<td colspan="6" align="right">总计:
							<span style="font-size:36px;">&yen;&nbsp; ${totalPrice} </span>
							<label
								id="counter" style="font-size:36px"></label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
									<input type="button" value="下单" class="btn_next" onclick="genernateOrder()" />
						</td>
					</tr>
				</table>
			</div>
		</div>

		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top" style="height:50px;width:195px;">
				<ul>
					<li style="margin-right:40px;">
						<a href="${pageContext.request.contextPath}/front?method=viewOrder">
							<img src="${pageContext.request.contextPath}/app/detail/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>
		
			<div id="dish_2" style="height: 255px;width: 165px;">
				<ul style="padding-left:0px;height:255px;margin-right: 0px;width: 165px;">
					<c:choose>
						<c:when test="${not empty requestScope.listFoodType}">
							<c:forEach var="foodType" items="${requestScope.listFoodType}">
								<li>
									<a href="${pageContext.request.contextPath}/front?method=submenu&foodTypeId=${foodType.id}">
										${foodType.typeName}
									</a>
									
								</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
									没有你要找的数据，请先保存记录再查看
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<%-- <input type="hidden" value="${foodType.id}" name="foodTypeId"> --%>
			<form action="${pageContext.request.contextPath}/front?method=submenu" method="post">
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<table style="width:166px">
					<tr>
						<td>
							<input type="text" id="dish_name" name="foodName" class="select_value" /> 
							<!-- <input type="hidden" value="selectFood" name="method"> -->
						</td>
					</tr>
					<tr>
						<td><input type="submit" id="sub" value=""style="margin-left:20px;"/></td>
					</tr>
					<tr>
						<td>
							<a href="${pageContext.request.contextPath}/front?method=menu">
								<img src="${pageContext.request.contextPath}/app/detail/style/images/look.gif" />
							</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>	
	</div>
</body>
</html>
