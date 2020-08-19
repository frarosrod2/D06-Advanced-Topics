<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.murilloBulletin.form.label.author" path="author" placeholder="Author"/>
	<acme:form-textbox code="anonymous.murilloBulletin.form.label.title" path="title" placeholder="Title"/>
	<acme:form-integer code="anonymous.murilloBulletin.form.label.volumen" path="volumen" placeholder="1-999"/>
	<acme:form-integer code="anonymous.murilloBulletin.form.label.number" path="number" placeholder="1-20"/>
	<acme:form-textarea code="anonymous.murilloBulletin.form.label.text" path="text" placeholder="Body text"/>
	<acme:form-submit code="anonymous.murilloBulletin.form.button.create" action="/anonymous/murillo-bulletin/create"/>
	<acme:form-return code="anonymous.murilloBulletin.form.button.return"/>
</acme:form>