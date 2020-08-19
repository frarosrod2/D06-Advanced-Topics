<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non- use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="patron.banner.form.label.picture" path="picture"/>
	<acme:form-textbox code="patron.banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="patron.banner.form.label.targetURL" path="targetURL"/>
		
	<!-- Botones -->
	<acme:form-submit test="${command == 'show'}" code="patron.banner.form.button.update"
		action="/patron/banner/update"/>
	<acme:form-submit test="${command == 'show'}" code="patron.banner.form.button.delete"
		action="/patron/banner/delete"/>
	<acme:form-submit test="${command == 'create'}" code="patron.banner.form.button.create"
		action="/patron/banner/create"/>
	<acme:form-submit test="${command == 'update'}" code="patron.banner.form.button.update"
		action="/patron/banner/update"/>
	<acme:form-submit test="${command == 'delete'}" code="patron.banner.form.button.delete"
		action="/patron/banner/delete"/>
		
	<jstl:if test="${command != 'create' and not hasCard}">
		<acme:form-submit method="get" code="patron.banner.form.button.creditCard.create" action="/patron/credit-card/create?bannerId=${bannerId}"/>	
	</jstl:if>
	<jstl:if test="${command != 'create' and hasCard}">
		<acme:form-submit method="get" code="patron.banner.form.button.creditCard.update" action="/patron/credit-card/update?creditCard=${creditCard}"/>
		<acme:form-submit method="get" code="patron.banner.form.button.creditCard.show" action="/patron/credit-card/show?creditCard=${creditCard}"/>		
	</jstl:if>	
		
  	<acme:form-return code="patron.banner.form.button.return"/>
</acme:form>