<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>

<table>
<!-- 
	<form action="UploadEmployIDRelation" method="post" enctype="multipart/form-data">
	<tr>ѡ����Excel��</tr>
	<tr>
		<td><input type="file" name="uploadfile.file" label="ѡ���ļ�" /></td>
		<td><input type="submit" value="�����ϴ�"/>	</td>
	</tr>
	</form>-->
		
	<s:form action="UploadEmployIDRelation"	enctype="multipart/form-data" method="POST" >
	<tr>ѡ����Excel:</tr>
	<tr>		
		<td><s:file name="uploadFile.file" label="ѡ���ļ�"/></td>
		<td><s:submit value="�ϴ�"  align="left"/></td>
	</tr>
	
	</s:form> 
</table>

<table>
    <form action="InsertSingleAccount" method="post">
	<tr>
		<td>ID:</td>
		<td><input name="user.isuid" type="text" value=""></td>
		<td>����</td>
		<td><input name="user.name" type="text" value=""></td>
		<td>��ɫ</td>
		<td><input name="user.actor" type="text" value=""></td>
	
		<td><input type="submit" value="����һ����Ա����ϵ" 
		onclick="this.from.action='InsertSingleAccount';form.submit();"/></td>
	</tr>
	</form>
</table>

<br>
<br>
<h2>��ǰԱ��IDӳ���</h2>
<table border="1">

	<tr>
		<td colspan>����</td>
		<td>Icesrcum ID</td>
	</tr>
	<tr>
		<td>��Խ</td>
		<td>231</td>
	</tr>
	

	
</table>




</body>
</html>