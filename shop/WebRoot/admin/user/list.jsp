<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
	</HEAD>
	<body>
		<br>
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表展示</strong>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
									<td align="center" width="10%">
										序号
									</td>
									<td align="center" width="10%">
										用户编号
									</td>
									<td align="center" width="10%">
										登录用户名
									</td>
									<td align="center" width="10%">
										登录密码
									</td>
									<td width="10%" align="center">
										姓名
									</td>
									<td width="10%" align="center">
										地址
									</td>
									<td width="10%" align="center">
										电话
									</td>
									<td width="10%" align="center">
										邮箱
									</td>
									<td width="10%" align="center">
										激活状态
									</td>
									<td width="5%" align="center">
										编辑
									</td>
									<td width="5%" align="center">
										删除
									</td>
								</tr>
								<s:iterator var="user" value="pageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
												width="10%">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<s:property value="#user.uid"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
												width="10%">
												<s:property value="#user.username"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
												width="10%">
												<s:property value="#user.password"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
												width="10%">
												<s:property value="#user.name"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
												width="10%">
												<s:property value="#user.addr"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
												width="10%">
												<s:property value="#user.phone"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
												width="10%">
												<s:property value="#user.email"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" 
												width="10%">
												<s:if test="#user.state == 0">
													未激活
												</s:if>
												<s:if test="#user.state == 1">
													已激活
												</s:if>
												
											</td>
											<td align="center" style="HEIGHT: 22px" width="5%">
												<a href="${pageContext.request.contextPath}/adminUser_edit.action?uid=<s:property value="#user.uid"/>">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
											<td align="center" style="HEIGHT: 22px" width="5%">
												<a href="${pageContext.request.contextPath}/adminUser_delete.action?uid=<s:property value="#user.uid"/>">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
									</s:iterator>	
									<tr align="right" bgColor="#afd1f3">
											<td colspan="11">
												第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页&nbsp;&nbsp;&nbsp;
												<a href="${pageContext.request.contextPath}/adminUser_findAll.action?page=1">首页</a>|
												<s:if test="pageBean.page != 1">
												<a href="${pageContext.request.contextPath}/adminUser_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
												</s:if>
												<s:if test="pageBean.page != pageBean.totalPage">
												<a href="${pageContext.request.contextPath}/adminUser_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
												</s:if>
												<a href="${pageContext.request.contextPath}/adminUser_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>
											</td>
										</tr>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		
	</body>
</HTML>

