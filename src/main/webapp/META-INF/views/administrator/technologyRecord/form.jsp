<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.technologyRecord.form.label.title" path="title"/>
	<acme:form-textbox code="administrator.technologyRecord.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="administrator.technologyRecord.form.label.inventor" path="inventor"/>
	<acme:form-textarea code="administrator.technologyRecord.form.label.description" path="description"/>
	<acme:form-url code="administrator.technologyRecord.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.technologyRecord.form.label.email" path="email"/>
	<acme:form-checkbox code="administrator.technologyRecord.form.label.openSource" path="openSource"/>
	<acme:form-integer code="administrator.technologyRecord.form.label.stars" path="stars"/>
	
	<acme:form-submit test="${command == 'show'}" 
	code="administrator.technologyRecord.form.button.update"
  	action="/administrator/technology-record/update"/>
  	<acme:form-submit test="${command == 'show'}" 
	code="administrator.technologyRecord.form.button.delete"
  	action="/administrator/technology-record/delete"/>
  	<acme:form-submit test="${command == 'create'}" 
	code="administrator.technologyRecord.form.button.create"
  	action="/administrator/technology-record/create"/>
  	<acme:form-submit test="${command == 'update'}" 
	code="administrator.technologyRecord.form.button.update"
  	action="/administrator/technology-record/update"/>
  	<acme:form-submit test="${command == 'delete'}" 
	code="administrator.technologyRecord.form.button.delete"
  	action="/administrator/technology-record/delete"/>
  	
  	<acme:form-return code="administrator.technologyRecord.form.button.return"/>
  	
</acme:form>