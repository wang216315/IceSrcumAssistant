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
             
             <th>部门</th>
             <th>团队</th>    
             <th>团队负责人</th>
             <th>项目名称</th>  
             <th>需求来源</th>
             <th>需求名称</th>  
             <th>需求类型</th>
             <th>需求优先级</th>    
             <th>负责人</th>  
             <th>整体工作量预测（人天）</th>
             <th>研发开始时间</th>
             <th>研发完成时间</th>    
             <th>计划完成时间</th> 
             <th>需求状态</th>
             <th>当前进展</th>  
             <th>备注</th>
             <th>jira编号</th>                   
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
<a href="DownloadPMOData" >下载</a>
</body>
</html>