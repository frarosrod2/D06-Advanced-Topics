<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.pazosBulletin.form.label.author" path="author" placeholder="Jim H"/>
	<acme:form-textarea code="anonymous.pazosBulletin.form.label.text" path="text" placeholder="FH no exp"/>
	<acme:form-textarea code="anonymous.pazosBulletin.form.label.contact" path="contact" placeholder="jim@email.com"/>
	<acme:form-checkbox code="anonymous.pazosBulletin.form.label.foodHandler" path="foodHandler"/>
	<acme:form-checkbox code="anonymous.pazosBulletin.form.label.experience" path="experience"/>
	<acme:form-submit code="anonymous.pazosBulletin.form.button.create" action="/anonymous/pazos-bulletin/create"/>
	<acme:form-return code="anonymous.pazosBulletin.form.button.return"/>
	
</acme:form>