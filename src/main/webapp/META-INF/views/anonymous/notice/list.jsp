<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.notice.list.label.picture" path="picture"/>
	<acme:list-column code="anonymous.notice.list.label.title" path="title"/>
	<acme:list-column code="anonymous.notice.list.label.creation" path="creation"/>
</acme:list>

	<acme:form-return code="anonymous.notice.form.button.return"/>