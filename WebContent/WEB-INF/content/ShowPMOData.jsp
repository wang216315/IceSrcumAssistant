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
             
             <th>����</th>
             <th>�Ŷ�</th>    
             <th>�ŶӸ�����</th>
             <th>��Ŀ����</th>  
             <th>������Դ</th>
             <th>��������</th>  
             <th>��������</th>
             <th>�������ȼ�</th>    
             <th>������</th>  
             <th>���幤����Ԥ�⣨���죩</th>
             <th>�з���ʼʱ��</th>
             <th>�з����ʱ��</th>    
             <th>�ƻ����ʱ��</th> 
             <th>����״̬</th>
             <th>��ǰ��չ</th>  
             <th>��ע</th>
             <th>jira���</th>                   
       </tr>

<s:iterator value="pstories">

 	<tr>
 	
 		<td><s:property value="department"/></td>
 		<td><s:property value="group"/></td>
 		<td><s:property value="groupLeader"/></td>
 		<td><s:property value="projectName"/></td>
 		<td><s:property value="userStorySource"/></td>
 		<td><s:property value="userStoryName"/></td>
 		<td><s:property value="userStoryType"/></td>
 		<td><s:property value="userStoryRank"/></td>
 		<td><s:property value="storyOwner"/></td>
 		<td><s:property value="storyWorkTime"/></td>
 		<td><s:property value="storyStartTime"/></td>
 		<td><s:property value="storyRealFinishTime"/></td>
 		<td><s:property value="storyPlanFinishTime"/></td>
 		
 		<td><s:property value="userStoryState"/></td>
 		<td><s:property value="currentState"/></td>
 		<td><s:property value="remark"/></td>
 		<td><s:property value="jiraId"/></td>

 		<!-- <td>
 			<s:iterator value="tasks">
 				<s:property value="name" />
 				<s:property value="initial" /><br>
 			</s:iterator>
 		</td> -->
 		
 	</tr>
	
  
</s:iterator>
</table>
<a href="DownloadPMOData" >����</a>
</body>
</html>