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
<table border="1">
       <tr>
             
             <th>US名字</th>
             <th>Task清单</th>       
       </tr>

<s:iterator value="stories">

 	<tr>
 	
 		<td><s:property value="name"/></td>
 		
 		<!-- <td>
 			<s:iterator value="tasks">
 				<s:property value="name" />
 				<s:property value="initial" /><br>
 			</s:iterator>
 		</td> -->
 		<td><s:property value="inProgressDate"/></td>
 	</tr>
	
  
</s:iterator>
</table>
</body>
</html>