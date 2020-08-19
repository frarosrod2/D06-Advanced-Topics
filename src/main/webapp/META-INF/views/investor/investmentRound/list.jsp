<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.investmentRound.list.label.ticker" path="ticker"/>
	<acme:list-column code="authenticated.investmentRound.list.label.round" path="round"/>
	<acme:list-column code="authenticated.investmentRound.list.label.title" path="title"/>
</acme:list>

	<acme:form-return code="authenticated.investmentRound.form.button.return"/>