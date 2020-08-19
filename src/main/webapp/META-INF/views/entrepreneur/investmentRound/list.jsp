<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="entrepreneur.investmentRound.list.label.ticker" path="ticker"/>
	<acme:list-column code="entrepreneur.investmentRound.list.label.round" path="round"/>
	<acme:list-column code="entrepreneur.investmentRound.list.label.title" path="title"/>
</acme:list>

	<acme:form-return code="entrepreneur.investmentRound.form.button.return"/>