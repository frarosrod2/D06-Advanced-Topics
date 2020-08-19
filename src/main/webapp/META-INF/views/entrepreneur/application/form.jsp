<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="entrepreneur.application.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-moment code="entrepreneur.application.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.investmentOffer" path="investmentOffer" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.status" path="status" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.investmentRound" path="investmentRound.title" readonly="true"/>
		
	<jstl:if test="${status == 'pending'}">
	<acme:form-textarea code="entrepreneur.application.form.label.justification" path="justification"/>
	</jstl:if><jstl:if test="${status == 'accepted'}">
	<acme:form-textarea code="entrepreneur.application.form.label.justification" path="justification" readonly="true"/>
	</jstl:if><jstl:if test="${status == 'rejected'}">
	<acme:form-textarea code="entrepreneur.application.form.label.justification" path="justification" readonly="true"/>
	</jstl:if>


	<acme:form-submit test="${status == 'pending'}" code="entrepreneur.application.form.label.accept" action="/entrepreneur/application/accept"/>
	<acme:form-submit test="${status == 'pending'}" code="entrepreneur.application.form.label.reject" action="/entrepreneur/application/reject"/>
	
  	<acme:form-return code="entrepreneur.application.form.button.return"/>
</acme:form>
	
	