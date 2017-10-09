<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<sx:head />
<title></title>
</head>
<body>
	<table border="0" width="800px">
		<tr>
			<td align="center" style="font-size:24px; color:#666">员工编辑</td>
		</tr>
		<tr>
			<td align="right"><a
				href="javascript:document.getElementById('saveForm').submit()">保存</a>
				&nbsp;&nbsp; <a href="javascript:history.go(-1)">退回</a></td>
		</tr>
	</table>
	<br />

	<s:form action="employee_update" method="post" namespace="/" id="saveForm" theme="simple">
	<s:hidden name="eid" value="%{model.eid}"/>
		<table border='0' cellpadding="0" cellspacing="10" width="800px">
			<tr>
				<td>姓名：</td>
				<td><s:textfield name="ename" value="%{model.ename}"/></td>
				<td>性别：</td>
				<td><s:radio name="sex" list="{'男','女'}" value="%{model.sex}"/></td>
			</tr>
			<tr>
				<td>出生日期</td>
				<td>
				<sx:datetimepicker name="birthday" displayFormat="yyyy-MM-dd" value="%{model.birthday}"/></td>
				<td>入职时间：</td>
				<td><sx:datetimepicker name="joinDate" displayFormat="yyyy-MM-dd" value="%{model.joinDate}"/></td>
			</tr>
			<tr>
				<td>所属部门：</td>
				<td><s:select name="department.did" list="list" listKey="did" value="%{model.department.did}"
						listValue="dname" headerKey="" headerValue="-----请--选--择----" /></td>
				<td>编号：</td>
				<td><s:textfield name="eno" value="%{model.eno}"/></td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><s:textfield name="username" value="%{model.username}"/></td>
				<td>密码：</td>
				<td><s:password showPassword="true" name="password" value="%{model.password}" /></td>
			</tr>
		</table>
	</s:form>
</body>
</html>
