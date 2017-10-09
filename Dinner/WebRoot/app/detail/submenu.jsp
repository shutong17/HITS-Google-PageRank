<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/app/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/app/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/detail/style/css/index_1.css" />
<link href="${pageContext.request.contextPath}/app/detail/style/css/index.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	function gotoPage(page) {
		var frm = document.forms[0];
		frm.action="${pageContext.request.contextPath}/front?method=submenu&currentPage="+page+"&foodTypeId="+${curFoodTypeId};
		frm.submit();
	}
</script>
</head>
<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 显示菜品的div -->
			<div id="top">
				<ul>
					<!-- 循环列出餐品 -->
					<c:choose>
						<c:when test="${not empty requestScope.pageBean.pageData}">
							<c:forEach var="food" items="${requestScope.pageBean.pageData}">
								<li>
									<dl>
										<dt>
											<a href="${pageContext.request.contextPath}/front?method=detail&foodId=${food.id}">
												<img src="${pageContext.request.contextPath}/food_images/${food.img}" />
											</a>
										</dt>
										<dd class="f1">
											<a href="${pageContext.request.contextPath}/front?method=detail&foodId=${food.id}">${food.foodName}</a>
										</dd>
										<dd class="f2">
											<a href="${pageContext.request.contextPath}/front?method=detail&foodId=${food.id}">&yen;${food.price}</a>
										</dd>
									</dl>
								</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
									<span>没有你要找的数据，请先保存记录再查看</span>
						</c:otherwise>
					</c:choose>
						
				</ul>
			</div>
			
			<!-- 底部分页导航条div -->
			<div id="foot" style="width:735px;margin-left: 30px;">
						<span style="float:left;line-height:53PX;margin-left: 0px;font-weight:bold;">
							<span style="font-weight:bold">&lt;&lt;</span>
						</span>
				<div id="btn" style="margin-top:0px; height:50px;">
					<ul style="margin-bottom:0px;margin-top:0px;">
						<!-- 参看 百度, 谷歌是 左 5 右 4 -->
						
							<li><a href="javascript:gotoPage(1)">首页</a></li>
						
							<li><a href="javascript:gotoPage(${pageBean.currentPage-1})">上一页</a></li>
							
							<li><a href="javascript:gotoPage(${pageBean.currentPage+1})">下一页</a></li>
							
							<li><a href="javascript:gotoPage(${pageBean.totalPage})">末页</a></li>
								
							<li style=" margin-top: 12px;"><span style="font-size:24px;">当前${pageBean.currentPage}/${pageBean.totalPage}页&nbsp;</span></li>
							
					</ul>
					
				</div>
						<span style="float:right; line-height:53px; margin-right:10px;  ">
							<a href="#" style="text-decoration:none;color:#000000;font-weight:bold;width:71px;margin-right:12px;border-top-width:0px;">&gt;&gt;</a>
						</span>
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
			<form action="${pageContext.request.contextPath}/front?method=submenu&search=true" method="post">
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<table width="166px">
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
