<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title>��ӭ</title>
</head>
<body>
<table border="1">
       <tr>
             <th>����</th>
             <th>С��</th>
             <th>������ʼ������(Сʱ)</th>
             <th>������ɹ�����(Сʱ)</th>
             <th>������ɹ�����(�ٷֱ�)</th>
             <th>ʣ�����������(Сʱ)</th>
             <th>������������</th>
             <th>������ϸ</th>
             <th>�쳣���񣨹���������û�а��ձ�׼����</th>         
       </tr>

<s:iterator value="employees">
 	<tr>
 		<td><s:property value="name"/></td>
 		<td><s:property value="group"/></td>
 		<td><s:property value="sprintInitialTime"/></td>
 		<td><s:property value="sprintFinishedTime"/></td>
 		<td><s:property value="sprintFinishedPercent"/>%</td>
 		<td><s:property value="sprintRemainingTime"/></td>
 		<td><s:property value="sprintTotalTask"/></td>
 		<td>
 			<!--<s:iterator value="tasks" id="task">
 				<s:if test="#task.estimation!=0">					
 				 	Task���ƣ�<s:property value="#task.name" />
 					TaskID��<s:property value="#task.uid" />
 					Task��������<s:property value="#task.initial" />
 					Taskʣ�๤������<s:property value="#task.estimation" /><br><br>	
 				</s:if>		
 			</s:iterator>-->
 		</td>
 		<td>
 			<s:iterator value="errorTask">
 				<s:property value="name" />
 				<s:property value="uid" /> 
 			</s:iterator>
 		</td>
 	</tr>
	
  
</s:iterator>
</table>
<a href="Initial" >������ҳ</a>
</body>
</html>