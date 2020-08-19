<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	
	<acme:list-column code="anonymous.rosaBulletin.list.label.moment" path="moment" width="5%"/>
	
	<acme:list-column code="anonymous.rosaBulletin.list.label.author" path="author" width="5%"/>
	
	<acme:list-column code="anonymous.rosaBulletin.list.label.title" path="title" width="5%"/>
	
	<acme:list-column code="anonymous.rosaBulletin.list.label.description" path="description" width="5%"/>
	
</acme:list>