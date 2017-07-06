<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title>欢迎</title>
</head>
<body>
<table border="1">
       <tr>
             <th>名字</th>
             <th>小组</th>
             <th>迭代初始工作量(小时)</th>
             <th>迭代完成工作量(小时)</th>
             <th>迭代完成工作量(百分比)</th>
             <th>剩余迭代工作量(小时)</th>
             <th>认领任务数量</th>
             <th>任务明细</th>
             <th>异常任务（工作量评估没有按照标准程序）</th>         
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
 				 	Task名称：<s:property value="#task.name" />
 					TaskID：<s:property value="#task.uid" />
 					Task工作量：<s:property value="#task.initial" />
 					Task剩余工作量：<s:property value="#task.estimation" /><br><br>	
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
<a href="Initial" >返回首页</a>
</body>
</html>