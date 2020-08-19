<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.overture.list.label.title" path="title"/>
	<acme:list-column code="administrator.overture.list.label.deadline" path="deadline"/>
	<acme:list-column code="administrator.overture.list.label.range" path="range"/>
</acme:list>

	<acme:form-return code="administrator.overture.form.button.return"/>