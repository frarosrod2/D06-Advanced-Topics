<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.rosaBulletin.form.label.author" path="author" placeholder="Tom R"/>
	<acme:form-textbox code="anonymous.rosaBulletin.form.label.title" path="title" placeholder="Engineer"/>
	<acme:form-textarea code="anonymous.rosaBulletin.form.label.description" path="description" placeholder="A simple description"/>
	<acme:form-submit code="anonymous.rosaBulletin.form.button.create" action="/anonymous/rosa-bulletin/create"/>
	<acme:form-return code="anonymous.rosaBulletin.form.button.return"/>
</acme:form>