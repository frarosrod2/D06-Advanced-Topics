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
	<acme:form-textbox code="authenticated.patron.form.label.name" path="name"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.patron.form.button.create" action="/authenticated/patron/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.patron.form.button.update" action="/authenticated/patron/update"/>
	
	<jstl:if test="${command != 'create' and not hasCard}">
		<acme:form-submit method="get" code="authenticated.banner.form.button.creditCard.create" action="/authenticated/credit-card/create?patronId=${patronId}"/>	
	</jstl:if>
	<jstl:if test="${command != 'create' and hasCard}">
		<acme:form-submit method="get" code="authenticated.banner.form.button.creditCard.update" action="/authenticated/credit-card/update?creditCard=${creditCard}"/>
		<acme:form-submit method="get" code="authenticated.banner.form.button.creditCard.show" action="/authenticated/credit-card/show?creditCard=${creditCard}"/>		
	</jstl:if>	
	
	
	<acme:form-return code="authenticated.patron.form.button.return"/>
</acme:form>