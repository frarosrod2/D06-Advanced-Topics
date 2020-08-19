<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.overture.list.label.title" path="title"/>
	<acme:list-column code="authenticated.overture.list.label.deadline" path="deadline"/>
	<acme:list-column code="authenticated.overture.list.label.range" path="range"/>
</acme:list>

	<acme:form-return code="authenticated.overture.form.button.return"/>