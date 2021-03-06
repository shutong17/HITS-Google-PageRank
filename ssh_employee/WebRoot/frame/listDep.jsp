<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<title></title>
<style type="text/css">
.table1 {
	border: 1px solid #ddd;
	width: 900px;
}

thead {
	background-color: red;
}
</style>
</head>
<body style="background-color:lightblue">
	<table border="0" width="900px">
		<tr>
			<td align="center" style="font-size:30px;color:#666;font-weight:bold">部门管理</td>
		</tr>
		<tr>
			<td align="right" style="font-style:italic;font-weight:bold"><a href="department_saveUI.action">添加</a></td>
		</tr>
	</table>
	<br />
	<table cellspacing="0" border="1" class="table1">
		<thead>
			<tr>
				<th width="450">部门名称</th>
				<th width="450">编辑</th>
				<th width="450">删除</th>
			</tr>
		<thead>
		<tbody>
			<s:iterator value="list" var="d">
				<tr>
					<td align="center"> <s:property value="#d.dname" /></td>
					<td align="center">
						<a href="${pageContext.request.contextPath}/department_edit.action?did=<s:property value="#d.did"/>">
							<img src="${pageContext.request.contextPath}/images/edit.png" />
						</a>
					</td>
					<td align="center">
						<a href="department_delete.action?did=<s:property value="#d.did"/>">
							<img src="${pageContext.request.contextPath}/images/trash.gif" />
						</a>
					</td>
				<tr>
			</s:iterator>
		</tbody>
	</table>
	<br />
	<table border="0" cellspacing="0" cellpadding="0" width="900px">
		<tr>
			<td align="right">
				<s:if test="totalPage>=1">
					<span>第<s:property value="currPage" />/<s:property value="totalPage" />页</span>&nbsp;&nbsp; 
				</s:if>
				<span>总记录数:<s:property value="totalCount" />&nbsp;&nbsp;每页显示：<s:property value="pageSize" />条</span>&nbsp;&nbsp; 
				<s:if test="currPage!=1">
					<a href="${pageContext.request.contextPath}/department_findAll.action?currPage=1">[首页]</a>&nbsp;&nbsp;
					<a href="department_findAll.action?currPage=<s:property value="currPage-1"/>">[上一页]</a>&nbsp;&nbsp;
   				</s:if> 
   				<s:if test="currPage!=totalPage">
					<a href="department_findAll.action?currPage=<s:property value="currPage+1"/>">[下一页]</a>&nbsp;&nbsp;
       				<a href="department_findAll.action?currPage=<s:property value="totalPage"/>">[尾页]</a>&nbsp;&nbsp;
   				</s:if>
   			</td>
		</tr>
	</table>
</body>
</html>