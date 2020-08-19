<%@page language="java"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	
	<c:if test="${ command == 'create' }">
		<acme:form-textbox code="authenticated.forum.show.label.title" path="title"/>		
	</c:if>
	<acme:form-submit test="${ command == 'create'}" code="authenticated.forum.form.button.create" action="/authenticated/forum/create" />
		
	<c:if test="${ command == 'show' }">
		<acme:form-textbox code="authenticated.forum.show.label.title" path="title" readonly="true"/>
	
		<acme:form-moment code="authenticated.forum.show.label.moment" path="moment" readonly="true" />
		
		<acme:form-textbox code="authenticated.forum.form.label.creator" path="creator.userAccount.username" readonly="true"/>
		
	
		<acme:form-panel code="authenticated.forum.show.legend.users">
			<table class="table table-striped mt-3">
			<thead>
				<tr>
					<th><acme:message code="authenticated.forum.show.user.label.username"/></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty involved}">
					<tr>
						<td colspan="4"><acme:message code="authenticated.forum.show.message.noUsers"/></td>
					</tr>
				</c:if>
				<c:if test="${not empty involved }">
					<c:forEach items="${involved}" var="user">
						<tr>
							<td><acme:print value="${user}" /></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</acme:form-panel>
	
	<acme:form-submit test="${command == 'show' && requestScope['creator.id'] == principal.getActiveRoleId()}" code="authenticated.forum.form.button.add-involvedUser" action="/authenticated/involved-user/create?forumId=${id}"
		method="get" />	
	
	<acme:form-submit test="${command == 'show' && requestScope['creator.id'] == principal.getActiveRoleId()}" code="authenticated.forum.form.button.delete-involved" action="/authenticated/involved-user/list?id=${id}"
	method="get" />
	
	<acme:form-panel code="authenticated.forum.show.legend.messages">
		<acme:form-return code="authenticated.forum.form.button.addMessage" action="/authenticated/message/create/?forumId=${id}"/>
		<table class="table table-striped mt-3">
			<thead>
				<tr>
					<th><acme:message code="authenticated.forum.show.message.label.title"/></th>
					<th><acme:message code="authenticated.forum.show.message.label.tags"/></th>
					<th><acme:message code="authenticated.forum.show.message.label.body"/></th>
					<th><acme:message code="authenticated.forum.show.message.label.moment"/></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty messages}">
					<tr>
						<td colspan="4"><acme:message code="authenticated.forum.show.message.noMessages"/></td>
					</tr>
				</c:if>
				<c:if test="${not empty messages }">
					<c:forEach items="${messages}" var="message">
						<tr>
							<td><acme:print value="${message.title}" /></td>
							<td>
								<c:forEach var="tag" items="${message.tagList}">
									<span class="badge badge-secondary"><acme:print value="${tag}"/></span>
								</c:forEach>
							</td>
							<td><acme:print value="${message.body}" /></td>
							<td><acme:print value="${message.moment}" /></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</acme:form-panel>
	
	</c:if>
	
	
	<acme:form-submit test="${command != 'create' && requestScope['creator.id'] == principal.getActiveRoleId()}" code="authenticated.forum.form.button.delete" action="/authenticated/forum/delete"/>	
	
	
	<acme:form-return code="authenticated.forum.form.button.return"/>
	
	
</acme:form>

<script>
$( document ).ready(function() {
    
	function updateUsersHelptext() {
		$("#users-involved-help-text").text($("#users-involved option:selected").toArray().map(option=>option.text).join(", "));
	}
	
	$("#users-involved").on("change", updateUsersHelptext);
	updateUsersHelptext();
});
</script>