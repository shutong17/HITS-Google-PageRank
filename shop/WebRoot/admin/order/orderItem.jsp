<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table border="0" width="100%">
	<s:iterator var="orderItem" value="list">
		<tr>
			<td width="20%">
				<img width="50" height="40" src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/>"/>
			</td>
			<td width="40%"> 
				<s:property value="#orderItem.product.pname"/>
			</td>
			<td width="20%">
				&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#orderItem.count"/>
			</td>
			<td width="20%">
				<s:property value="#orderItem.subtotal"/>
			</td>
		</tr>
	</s:iterator>
</table>