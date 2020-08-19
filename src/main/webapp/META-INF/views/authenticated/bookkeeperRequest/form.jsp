<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
		<acme:form-textarea code="authenticated.bookkeeperRequest.form.label.name" path="name"/>
		<acme:form-textarea code="authenticated.bookkeeperRequest.form.label.responsabilityStatement" path="responsabilityStatement"/>
	
		<acme:form-submit test="${command == 'create'}"
		code="authenticated.bookkeeperRequest.form.button.create" action="/authenticated/bookkeeper-request/create"/>
		<acme:form-return code="authenticated.bookkeeperRequest.form.button.return" />	
	
</acme:form>