<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="entrepreneur.application.list.label.ticker" path="ticker"/>
	<acme:list-column code="entrepreneur.application.list.label.creationMoment" path="creationMoment"/>
	<acme:list-column code="entrepreneur.application.list.label.statement" path="statement"/>
	<acme:list-column code="entrepreneur.application.list.label.status" path="status"/>
</acme:list>