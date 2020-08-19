<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.bookkeeperRequest.list.label.name" path="name"/>
	<acme:list-column code="administrator.bookkeeperRequest.list.label.responsabilityStatement" path="responsabilityStatement"/>
</acme:list>
	<acme:form-return code="administrator.bookkeeperRequest.list.button.return"/>

