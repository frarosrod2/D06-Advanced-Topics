<%@page language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="administrator.notice.form.label.title" path="title"/>
	<acme:form-textbox code="administrator.notice.form.label.picture" path="picture"/>
	<c:if test="${ command == 'show' }">
	<acme:form-moment code="administrator.notice.form.label.creation" path="creation"/>
	</c:if>
	<acme:form-moment code="administrator.notice.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.notice.form.label.body" path="body"/>
	<c:if test="${ command == 'show' }">
	<acme:form-panel code="administrator.notice.form.label.linkList">
	
		<table class="table table-striped">	
			<tbody>
				<c:forEach items="${linkList}" var="link">
						<tr>
							<td>${link}</td>
						</tr>
			</c:forEach>
			</tbody>
		</table>
	</acme:form-panel>
	</c:if>
	<c:if test="${ command == 'create' }">
	<acme:form-textarea code="administrator.notice.form.label.optionalLinks" path="optionalLinks"/>
	<acme:form-checkbox code="administrator.notice.form.label.confirm" path="confirm"/>
	</c:if>
  	<acme:form-submit test="${command == 'create'}" 
	code="administrator.notice.form.button.create"
  	action="/administrator/notice/create"/>
	<acme:form-return code="administrator.notice.form.button.return" />	
</acme:form> 