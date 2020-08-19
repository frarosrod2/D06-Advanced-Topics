<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.involvedUser.list.label.authenticatedUserName" path="authenticated.userAccount.username"/>
</acme:list>

	<acme:form-return code="authenticated.involvedUser.list.label.return"/>