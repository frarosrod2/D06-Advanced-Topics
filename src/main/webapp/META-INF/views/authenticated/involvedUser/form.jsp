<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<acme:form>
	<acme:form-hidden path="forumId" />
	<jstl:if test="${command == 'create'}">
	<acme:form-textbox code="authenticated.involved-user.form.label.searchUser" path="searchUser" />
	</jstl:if>
	<jstl:if test="${command == 'show' || command == 'delete'}">
		<acme:form-textbox code="authenticated.involved-user.form.label.username" path="authenticated.userAccount.username" readonly="true"/>
	</jstl:if>

	<acme:form-submit test="${command == 'create'}" code="authenticated.involved-user.form.button.create" action="create" />
	<acme:form-submit test="${command != 'create'}" code="authenticated.involved-user.form.button.delete" action="delete" />
	<acme:form-return code="authenticated.involved-user.form.button.return" />

</acme:form>