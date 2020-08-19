<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="administrator.bookkeeperRequest.form.label.name" path="name"/>
	<acme:form-textbox code="administrator.bookkeeperRequest.form.label.responsabilityStatement" path="responsabilityStatement"/>
</acme:form>

<acme:form>
	<acme:form-select code="administrator.bookkeeperRequest.form.label.state" path="state">
		<acme:form-option code="administrator.bookkeeperRequest.form.label.confirmed.pending" value="pending"/>
		<acme:form-option code="administrator.bookkeeperRequest.form.label.confirmed.accepted" value="accepted"/>	
		<acme:form-option code="administrator.bookkeeperRequest.form.label.confirmed.rejected" value="rejected"/>			
	</acme:form-select>
	
	<acme:form-submit code="administrator.bookkeeperRequest.form.button.update" action="/administrator/bookkeeper-request/update?id=${id}"/>
	<acme:form-return code="administrator.bookkeeperRequest.form.button.return"/>
</acme:form>