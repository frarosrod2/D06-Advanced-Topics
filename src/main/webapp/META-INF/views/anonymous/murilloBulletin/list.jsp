<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	
	<acme:list-column code="anonymous.murilloBulletin.list.label.moment" path="moment" width="5%"/>
	
	<acme:list-column code="anonymous.murilloBulletin.list.label.author" path="author" width="5%"/>
	
	<acme:list-column code="anonymous.murilloBulletin.list.label.title" path="title" width="5%"/>
	
	<acme:list-column code="anonymous.murilloBulletin.list.label.volumen" path="volumen" width="5%"/>
	
	<acme:list-column code="anonymous.murilloBulletin.list.label.number" path="number" width="5%"/>
	
	<acme:list-column code="anonymous.murilloBulletin.list.label.text" path="text" width="5%"/>
	
</acme:list>