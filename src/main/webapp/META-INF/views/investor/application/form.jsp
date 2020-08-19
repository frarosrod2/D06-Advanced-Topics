<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<input name="investmentRoundId" id="investmentRoundId" type="hidden" value="${param.investmentRoundId}"/>
	<acme:form-textbox code="investor.application.form.label.ticker" path="ticker"/>
	<jstl:if test="${command != 'create' }">
	<acme:form-moment code="investor.application.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-textbox code="investor.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="investor.application.form.label.statement" path="statement"/>
	<acme:form-money code="investor.application.form.label.investmentOffer" path="investmentOffer"/>
		
		<acme:form-submit test="${command == 'create' }" code="investor.application.form.button.createApplication" action="/investor/application/create"/>
	
  	<acme:form-return code="investor.application.form.button.return"/>
</acme:form>
	
	