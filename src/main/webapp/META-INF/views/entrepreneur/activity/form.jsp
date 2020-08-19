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

<acme:form>
	<input id="investmentRoundId" name="investmentRoundId" type="hidden" value="${param.investmentRoundId}"/> 
	<acme:form-textbox code="entrepreneur.workProgramme.form.label.activity.title" path="title" />
	<acme:form-moment code="entrepreneur.workProgramme.form.label.activity.start" path="start" />
	<acme:form-moment code="entrepreneur.workProgramme.form.label.activity.end" path="end" />
	<acme:form-money code="entrepreneur.workProgramme.form.label.activity.budget" path="budget" />
	
	<acme:form-submit test="${command == 'create'}" code="entrepreneur.investmentRound.form.button.create" action="/entrepreneur/activity/create"/>
	<acme:form-submit test="${command == 'update'}" code="entrepreneur.investmentRound.form.button.update" action="/entrepreneur/activity/update"/>
	<acme:form-return code="entrepreneur.investmentRound.form.button.return"/>
</acme:form>