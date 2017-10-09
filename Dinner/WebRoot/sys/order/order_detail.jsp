<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/style/css/index_1.css" />
</head>
<body>
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/sys/style/css/images/title_arrow.gif" /> 菜单详细列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>菜名</td>
					<td>单价</td>
					<td>数量</td>
				</tr>
			</thead>
			
			<tbody id="TableData">
				<c:choose>
					<c:when test="${not empty requestScope.listOrderDetail}">
						<c:forEach var="orderDetail" items="${requestScope.listOrderDetail}">
							<tr align="center" bgcolor="#FFFFFF" height="60"> 
								<td align="center">${orderDetail.foodName}</td>
								<td align="center">${orderDetail.price}</td>
								<td align="center">${orderDetail.foodCount}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr align="center" bgcolor="#FFFFFF">
							<td colspan="3">没有你要找的数据，请先保存记录再查看</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div id="TableTail" align="center">
			 <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
		</div>
	</div>
</body>
</html>
