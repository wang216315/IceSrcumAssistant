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
	<tr>选择导入Excel：</tr>
	<tr>
		<td><input type="file" name="uploadfile.file" label="选择文件" /></td>
		<td><input type="submit" value="批量上传"/>	</td>
	</tr>
	</form>-->
		
	<s:form action="UploadEmployIDRelation"	enctype="multipart/form-data" method="POST" >
	<tr>选择导入Excel:</tr>
	<tr>		
		<td><s:file name="uploadFile.file" label="选择文件"/></td>
		<td><s:submit value="上传"  align="left"/></td>
	</tr>
	
	</s:form> 
</table>

<table>
    <form action="InsertSingleAccount" method="post">
	<tr>
		<td>ID:</td>
		<td><input name="user.isuid" type="text" value=""></td>
		<td>姓名</td>
		<td><input name="user.name" type="text" value=""></td>
		<td>角色</td>
		<td><input name="user.actor" type="text" value=""></td>
	
		<td><input type="submit" value="新增一个名员工关系" 
		onclick="this.from.action='InsertSingleAccount';form.submit();"/></td>
	</tr>
	</form>
</table>

<br>
<br>
<h2>当前员工ID映射表：</h2>
<table border="1">

	<tr>
		<td colspan>姓名</td>
		<td>Icesrcum ID</td>
	</tr>
	<tr>
		<td>王越</td>
		<td>231</td>
	</tr>
	

	
</table>




</body>
</html>