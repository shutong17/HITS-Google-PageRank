<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<!-- 包含公共的JSP代码片段 -->
<title>无线点餐平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/style/css/index_1.css" />
</head>
<body>
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif" />餐桌搜索结果列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>
	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>编号</td>
					<td>桌名</td>
					<td>状态</td>
					<td>预定时间</td>
					<td>操作</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">
				<c:choose>
					<c:when test="${not empty requestScope.listTable}">
						<c:forEach var="table" items="${requestScope.listTable}">
							<tr class="TableDetail1">
								<td align="center">${table.id}</td>
								<td align="center">${table.tableName}</td>
								<td align="center">
									<c:choose>
										<c:when test="${table.tableStatus%2==0}">
											空闲   
  										 </c:when>
										<c:otherwise>
											预定
  					 					</c:otherwise>
									</c:choose>
								</td>
								<td align="center">${table.orderDate}</td>
								<td align="center">
									<c:choose>
										<c:when test="${table.tableStatus%2==0}">
												<a href="${pageContext.request.contextPath}/table?id=${table.id}&method=viewUpdate" class="FunctionButton">预定</a> 
												<a href="${pageContext.request.contextPath}/table?id=${table.id}&method=delete" class="FunctionButton">删除</a>
  										 </c:when>
										<c:otherwise>
												<a href="${pageContext.request.contextPath}/table?id=${table.id}&method=viewUpdate" class="FunctionButton">退桌</a> 
												<a href="${pageContext.request.contextPath}/table?id=${table.id}&method=delete" class="FunctionButton">删除</a>
  					 					</c:otherwise>
									</c:choose>
								</td>
								
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="TableDetail1">
							<td colspan="3">没有你要找的数据，请先保存记录再查看</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<!-- 其他功能超链接 -->
		<div id="TableTail" align="center">
			<div>
				<a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
			</div>
    	</div> 
	</div>
</body>
</html>
