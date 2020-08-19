<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.accountingRecord.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.accountingRecord.form.label.status" path="status"/>
	<acme:form-textbox code="authenticated.accountingRecord.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textarea code="authenticated.accountingRecord.form.label.body" path="body"/>
  	
  	<acme:form-return code="authenticated.accountingRecord.form.button.return"/>
</acme:form>
