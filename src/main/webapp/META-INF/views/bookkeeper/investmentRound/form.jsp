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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form  readonly="true">
	<acme:form-textbox code="bookkeeper.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="bookkeeper.investmentRound.form.label.round" path="round"/>
	<acme:form-textbox code="bookkeeper.investmentRound.form.label.title" path="title"/>
	<acme:form-moment code="bookkeeper.investmentRound.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textarea code="bookkeeper.investmentRound.form.label.description" path="description"/>
	<acme:form-money code="bookkeeper.investmentRound.form.label.money" path="money"/>
	<acme:form-textbox code="bookkeeper.investmentRound.form.label.link" path="link"/>
	<acme:form-panel code="bookkeeper.investmentRound.form.label.workProgramme">
		<table class="table table-striped">	
			<tbody>
				<c:forEach items="${workProgramme}" var="activity">
						<tr>
							<td>${activity.title}</td>
						</tr>
			</c:forEach>
			</tbody>
		</table>
	</acme:form-panel>

	<button type="button" onclick="javascript: redirect('/bookkeeper/accounting-record/create?investmentRoundId=${id}')" class="btn btn-primary">
		<acme:message code="bookkeeper.accountingRecord.form.button.createAccounting" />
	</button>

	<button type="button" formmethod="get" onclick="javascript: redirect('/bookkeeper/accounting-record/list_mine?id=${param.id}')" class="btn btn-primary">
		<acme:message code="bookkeeper.investmentRound.form.button.accountings"/>
	</button>	
  	<acme:form-return code="bookkeeper.investmentRound.form.button.return"/>
</acme:form>