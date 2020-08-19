<%@page language="java"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<h2>
		<acme:message code="authenticated.message.header.h2" />:
		<strong><acme:print value="${forum.title}" /></strong>
		
	</h2>
	
	<input type="hidden" name="forumId" value="${ forum.id }" />
	
	<acme:form-textbox code="authenticated.message.label.title" path="title"/>
	<acme:form-textbox code="authenticated.message.label.tags" path="tags"/>
	<acme:form-textarea code="authenticated.message.label.body" path="body"/>
	<acme:form-checkbox code="authenticated.message.label.confirmPost" path="confirmPost"/>
	
	
  	<acme:form-return code="authenticated.message.form.button.return"/>
  	<acme:form-return code="authenticated.message.form.button.returnToForum" action="/authenticated/forum/show/?id=${forum.id}"/>
   	<acme:form-submit test="${ command == 'create' }" code="authenticated.message.form.button.create" action="/authenticated/message/create" /> 
</acme:form>