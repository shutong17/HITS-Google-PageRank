<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/style/css/index_1.css" />
	<script type="text/javascript">
		setInterval(function(){
			window.location.href = "/wirelessplatform/client.html?method=list";
		},1000 * 50);
	</script>
</head>
<body>
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif" /> 餐厅订单列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>订单编号</td>
					<td>餐桌名</td>
					<td>下单日期</td>
					<td>总金额</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">
			 		<c:choose>
						<c:when test="${not empty requestScope.listOrder}">
							<c:forEach var="order" items="${requestScope.listOrder}">
								<tr height="60" bgcolor="#FFFFFF" align="center">
									<td align="center">${order.id}</td>
									<td align="center">${order.tableName}</td>
									<td align="center">${order.orderDate}</td>
									<td align="center">${order.tablePrice}</td>
									<td align="center">
										<c:choose>
											<c:when test="${order.orderStatus%2==0}">
												待结账
  										 	</c:when>
										<c:otherwise>
												已结账
  					 					</c:otherwise>
									</c:choose>
									</td>
									<td align="center">
										<c:choose>
											<c:when test="${order.orderStatus%2==0}">
												<a href="${pageContext.request.contextPath}/order?id=${order.id}&method=viewDetail" class="FunctionButton">明细</a> 
												<a href="${pageContext.request.contextPath}/order?id=${order.id}&method=check" class="FunctionButton">结账</a>
  										 	</c:when>
										<c:otherwise>
												<a href="${pageContext.request.contextPath}/order?id=${order.id}&method=viewDetail" class="FunctionButton">明细</a> 
  					 					</c:otherwise>
									</c:choose>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr bgcolor="#FFFFFF">
								<td colspan="3" align="center">没有你要找的数据，请先保存记录再查看</td>
							</tr>
						</c:otherwise>
					</c:choose>
			</tbody>
		</table>
	</div>
</body>
</html>
