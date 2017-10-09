<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<title></title>
<style type="text/css">
.div1 {
	margin-top: 35px;
	margin-left: 1550px;
	font-size: 20px;
	color: white
}
</style>
</head>

<body bgcolor="#0099FF" >
	<div class="div1">
		欢迎您：
		<s:property value="#session.currentEmployee.ename" />&nbsp;&nbsp;&nbsp;
		证件号：
		<s:property value="#session.currentEmployee.eno" />
	</div>
</body>
</html>