<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>网上商城管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/general.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
  color: white;
}
</style>
</head>
<body style="background:#278296">
<p align="center"> <a style="background:red" >&nbsp;&nbsp;&nbsp;SHOP&nbsp;&nbsp;&nbsp;</a>
</p>
<center><s:actionerror /></center>
<form method="post" action="${pageContext.request.contextPath }/admin_login.action" target="_parent" name="theForm">
	<table cellspacing="0" cellpadding="0" style="margin-top: 50px;margin-left: 750px;margin-right: 250px;width: 349px;" >
  		<tbody>
  			<tr>
    			<td style="padding-left: 0px">
      			<table>
      				<tr>
        				<td>管理员姓名：</td>
        				<td><input type="text" name="username" /></td>
      				</tr>
      				<tr>
        				<td>管理员密码：</td>
        				<td><input type="password" name="password" /></td>
     	 			</tr>
      			</table>
    			</td>
  			</tr>
  			<tr align="center">
       					<td >
       						<input type="submit" value="进入后台管理系统" class="buton" />
       					</td>
      		</tr>
		</tbody>
	</table>
	<input type="hidden" name="act" value="signin" />
</form>
</body>
</html>
