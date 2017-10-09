<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	
	<body>
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/adminUser_update.action" method="post" enctype="multipart/form-data">
			<input type="hidden" name="uid" value="<s:property value="model.uid"/>"/>
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>编辑用户信息</STRONG>
						</strong>
					</td>
				</tr>

				<tr>
					<td width="15%" align="center" bgColor="#f5fafe" class="ta_01">
						用户名：
					</td>
					<td class="ta_01" bgColor="#ffffff"  width="15%">
						<input type="text" name="username" value="<s:property value="model.username"/>" id="userAction_save_do_logonName" class="bg"/>
					</td>
					<td width="15%" align="center" bgColor="#f5fafe" class="ta_01">
						登录密码：
					</td>
					<td class="ta_01" bgColor="#ffffff"  width="20%">
						<input type="text" name="password" value="<s:property value="model.password"/>" id="userAction_save_do_logonName" class="bg"/>
					</td>
					<td  align="center" bgColor="#f5fafe" class="ta_01" width="15%">
						真实姓名：
					</td>
					<td class="ta_01" bgColor="#ffffff"  width="20%">
						<input type="text" name="name" value="<s:property value="model.name"/>" id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="center" bgColor="#f5fafe" class="ta_01">
						是否激活：
					</td>
					<td class="ta_01" bgColor="#ffffff" >
						<select name="state">
								<option value="1" <s:if test="model.state==1">selected</s:if>>
									是
								</option>
								<option value="0" <s:if test="model.state==0">selected</s:if>>
									否
								</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="15%" align="center" bgColor="#f5fafe" class="ta_01">
						E-mail：
					</td>
					<td class="ta_01" bgColor="#ffffff" width="15%">
						<input type="text" name="email" value="<s:property value="model.email"/>" id="userAction_save_do_logonName" class="bg"/>
					</td>
					<td width="15%" align="center" bgColor="#f5fafe" class="ta_01">
						家庭地址：
					</td>
					<td class="ta_01" bgColor="#ffffff" width="20%">
						<input type="text" name="addr" value="<s:property value="model.addr"/>" id="userAction_save_do_logonName" class="bg"/>
					</td>
					<td width="15%" align="center" bgColor="#f5fafe" class="ta_01">
						手机号码：
					</td>
					<td class="ta_01" bgColor="#ffffff" width="20%">
						<input type="text" name="phone" value="<s:property value="model.phone"/>" id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
			
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>