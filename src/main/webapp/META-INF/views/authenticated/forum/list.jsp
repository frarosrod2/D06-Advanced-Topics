<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.forum.list.label.title" path="title"/>
	<acme:list-column code="authenticated.forum.list.label.moment" path="moment"/>
</acme:list>

<acme:form>
	
	<acme:form-return code="authenticated.forum.list.button.return"/>
	<acme:form-return code="master.menu.authenticated.forums.create" action="/authenticated/forum/create"/>
</acme:form>