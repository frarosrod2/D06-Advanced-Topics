<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form >
	<acme:form-hidden path="investmentRoundId" />
<jstl:if test="${command == 'show'}">
	<acme:form-textbox code="bookkeeper.accountingRecord.form.label.title" path="title" readonly="true"/>
	<acme:form-moment code="bookkeeper.accountingRecord.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-textbox code="bookkeeper.accountingRecord.form.label.status" path="status" readonly="true"/>
	<acme:form-textarea code="bookkeeper.accountingRecord.form.label.body" path="body" readonly="true"/>
 </jstl:if>

<jstl:if test="${command == 'create'}">
	<acme:form-textbox code="bookkeeper.accountingRecord.form.label.title" path="title"/>
	<acme:form-select code="bookkeeper.accountingRecord.form.label.status" path="status">
		<acme:form-option code="bookkeeper.accountingRecord.form.label.status.draft" value="DRAFT"/>
		<acme:form-option code="bookkeeper.accountingRecord.form.label.status.published" value="PUBLISHED"/>	
	</acme:form-select>
	<acme:form-textarea code="bookkeeper.accountingRecord.form.label.body" path="body"/>
 </jstl:if>
 
	<acme:form-submit test="${command == 'create' }" code="bookkeeper.accountingRecord.form.button.create"
	action="create"/>
  	<acme:form-return code="bookkeeper.accountingRecord.form.button.return"/>
</acme:form>