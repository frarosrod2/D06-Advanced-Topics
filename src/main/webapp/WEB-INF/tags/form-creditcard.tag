<%--
- form-money.tag
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@tag language="java" body-content="empty"
	import="acme.framework.helpers.MessageHelper"
%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%@attribute name="path" required="true" type="java.lang.String"%>
<%@attribute name="code" required="true" type="java.lang.String"%>
<%@attribute name="readonly" required="false" type="java.lang.Boolean"%>


<jstl:set var="pathHolderName" value="${path}.holderName"/>
<jstl:set var="pathBrand" value="${path}.brand"/>
<jstl:set var="pathNumber" value="${path}.number"/>
<jstl:set var="pathCvv" value="${path}.cvv"/>
<jstl:set var="pathExpMonth" value="${path}.expMonth"/>
<jstl:set var="pathExpYear" value="${path}.expYear"/>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false"/>
</jstl:if>

<fieldset <jstl:if test="${readonly}">disabled</jstl:if>>
	<legend><acme:message code="${code}" /></legend>
	
	<div class="form-group">
		<label for="${pathHolderName}">
			<acme:message code="${MessageHelper.getMessage('default.placeholder.creditcard.holderName')}"/>
		</label>
		<input 
			id="${pathHolderName}" 
			name="${pathHolderName}"
			value="<acme:print value='${requestScope[pathHolderName]}'/>"
			type="text"
			class="form-control"
			placeHolder="${MessageHelper.getMessage('default.placeholder.creditcard.holderName')}" 
			<jstl:if test="${readonly}"> readonly</jstl:if>
		/>
		<acme:form-errors path="${pathHolderName}"/>
	</div>
	
	<div class="form-group">
		<label for="${pathBrand}">
			<acme:message code="${MessageHelper.getMessage('default.placeholder.creditcard.brand')}"/>
		</label>
		<input 
			id="${pathBrand}" 
			name="${pathBrand}"
			value="<acme:print value='${requestScope[pathBrand]}'/>"
			type="text"
			class="form-control"
			placeHolder="<acme:message code="${MessageHelper.getMessage('default.placeholder.creditcard.brand')}"/>"
		/>
		<acme:form-errors path="${pathBrand}"/>
	</div>
	
	<div class="form-row">
		<div class="form-group col-md-9">
			<label for="${pathNumber}">
				<acme:message code="${MessageHelper.getMessage('default.placeholder.creditcard.number')}"/>
			</label>
			<input 
				id="${pathNumber}" 
				name="${pathNumber}"
				value="<acme:print value='${requestScope[pathNumber]}'/>"
				type="text"
				class="form-control"
				placeHolder="<acme:message code="${MessageHelper.getMessage('default.placeholder.creditcard.number')}"/>"
			/>
			<acme:form-errors path="${pathNumber}"/>
		</div>
		
		<div class="form-group col-md-3">
			<label for="${pathCvv}">
				<acme:message code="${MessageHelper.getMessage('default.placeholder.creditcard.cvv')}"/>
			</label>
			<input 
				id="${pathCvv}" 
				name="${pathCvv}"
				value="<acme:print value='${requestScope[pathCvv]}'/>"
				type="text"
				class="form-control"
				placeHolder="<acme:message code="${MessageHelper.getMessage('default.placeholder.creditcard.cvv')}"/>"
			/>
			<acme:form-errors path="${pathCvv}"/>
		</div>
	</div>
	
	<div class="form-row">
		<div class="form-group col">
			<label for="${path}.expMonth">
				<acme:message code="${MessageHelper.getMessage('default.placeholder.creditcard.expMonth')}"/>
			</label>
			<input 
				id="${pathExpMonth}" 
				name="${pathExpMonth}"
				value="<acme:print value='${requestScope[pathExpMonth]}'/>"
				type="text"
				class="form-control"
				placeHolder="<acme:message code="${MessageHelper.getMessage('default.placeholder.creditcard.expMonth')}"/>"
			/>
			<acme:form-errors path="${pathExpMonth}"/>
		</div>
		
		<div class="form-group col">
			<label for="${pathExpYear}">
				<acme:message code="${MessageHelper.getMessage('default.placeholder.creditcard.expYear')}"/>
			</label>
			<input 
				id="${pathExpYear}" 
				name="${pathExpYear}"
				value="<acme:print value='${requestScope[pathExpYear]}'/>"
				type="text"
				class="form-control"
				placeHolder="<acme:message code="${MessageHelper.getMessage('default.placeholder.creditcard.expYear')}"/>"
			/>
			<acme:form-errors path="${pathExpYear}"/>
		</div>
	</div>
	<acme:form-errors path="${path}"/>
	
</fieldset>