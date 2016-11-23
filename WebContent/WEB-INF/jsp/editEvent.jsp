<!-- Created by Dipanjan Karmakar -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


	<p>${user_name}</p>
	<div align="center">
		<form:form action="saveEditedEvent" method="post" commandName="eventsForm">
			<form:hidden path="event_id"/>
			<table border="1" style="background-color:#85c1e9;color:#34495e;font:Arial;border-radius:6px;font-size:12px;width:50%;">
				<tr>
					<td colspan="2" align="center"><h2>Enter these fields to
							fetch data</h2></td>
				</tr>
				<tr>
					<td>Enter Description of the event:</td>
					<td><form:input path="event_desc" /></td>
				</tr>
				<tr>
					<td>Mention Resource Needed :</td>
					<td><form:input path="resources_needed" /></td>
				</tr>
				
				<tr>
					<td>Are all resource satisfied :</td>
					<td><form:select path="is_resources_satisfied" >
						<form:option selected="true" value="false" label="NO"/>
   						<form:option value="true" label="YES"/>
   						</form:select>
					</td>
				</tr>
				
				<tr>
					<td>Place of event:</td>
					<td><form:input path="place" /></td>
				</tr>
				<tr>
					<td>Date/Time for event :</td>
					<td><form:input path="event_date_time" id="datetimepicker" type="text"/></td>
				</tr>
				<tr>
				<td>Validity :</td>
				<td>
					<table>
						<tr><td><form:radiobutton path="is_archived" value="false"/>Active</td></tr>
						<tr><td><form:radiobutton path="is_archived" value="true"/>Archived</td></tr>
					</table>
				</td>
				</tr>
				<!-- <tr>
				<td>Comments: </td>
					<td>
						<div class="block">
        				<textarea id="textbox" style="height: 20px; width: 200px;" class="commentbox"></textarea>
        					<div class="commentsblock">
       			 			</div>
    					</div>
					
					<td>
				</tr> -->
				<tr>
				<td>Comments:</td>
				
				<td>
					<c:if test="${not empty eventsForm.prevComments}">
						<c:forEach items="${eventsForm.prevComments}" var="comment">
							<c:set var="commentParts" value="${fn:split(comment.commentString, '~')}" />
							<p><span style="color:INDIANRED;"><b><i><c:out value="${commentParts[0]}" /></i></b></span>  <c:out value="${commentParts[1]}" /></p>
						</c:forEach>
					</c:if>
						<form:input path="commentToAdd" style="height: 20px; width: 200px;" class="commentbox" type="text"/>
				</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form:form>
	</div>