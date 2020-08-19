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

<acme:form readonly="${command=='display'}" >
	<acme:form-textbox code="administrator.customisation.form.label.spamWords" path="spamWords" />
	<acme:form-double code="administrator.customisation.form.label.spamThreshold" path="spamThreshold" />
	<acme:form-textbox code="administrator.customisation.form.label.activitySectors" path="activitySectors" />
	
  	<acme:form-return code="administrator.customisation.form.button.return" />
  	
  	<jstl:if test="${command == 'update'}">
  		<acme:form-submit code="administrator.customisation.form.button.update" action="/administrator/customisation/update" />
  	</jstl:if>
  	
  	<jstl:if test="${command == 'display'}">
  		<acme:form-return code="administrator.customisation.form.button.edit" action="/administrator/customisation/update" />
  	</jstl:if>
</acme:form>
