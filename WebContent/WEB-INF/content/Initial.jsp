<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>icesrcum assistant</title>
</head>
<body>

<table>
	<form action="action" method="post">
		<tr>
			<td>icescrum�˺�:</td>
			<td><input name="operator.username" type="text" value="wangyue116"/></td>
		</tr>
		<tr>
			<td>icescrum����:</td>
			<td><input name="operator.password" type="password" value="!A111111a"/></td>
		</tr>
		<tr>
			<td>��ĿID</td>			
			<td>
				<!--<select name="operator.pkey">
					<s:iterator value="projects">
					<option value=<s:property value="pkey"/>><s:property value="pname"/></option>
  				</s:iterator>
				</select> -->	
				<s:iterator value="projects">
					<input name="operator.pkeys" value=<s:property value="pkey"/> type="checkbox"><s:property value="pname"/></input>
				</s:iterator>

				
			</td>
		</tr>
		
		<tr>
			<td><input type="submit" value="������ͳ��"  
			onclick="this.form.action='CountTaskTimeForEachPerson.action';form.submit();" /></td>
			<td><input type="submit" value="US����"  
			onclick="this.form.action='OutputStoryList.action';form.submit();" /></td>
			<td><input type="submit" value="PMO����"  
			onclick="this.form.action='PMOData.action';form.submit();" /></td>
		</tr>	
		<tr><a href="EmployeesList" target="_blank" title="��Աӳ���ϵ����">��Աӳ�����</a></tr>
			
	</form>	
</table>
</body>
</html>