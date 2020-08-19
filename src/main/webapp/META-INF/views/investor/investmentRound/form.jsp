<%@page language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="authenticated.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.round" path="round"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.investmentRound.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textarea code="authenticated.investmentRound.form.label.description" path="description"/>
	<acme:form-money code="authenticated.investmentRound.form.label.money" path="money"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.link" path="link"/>
	<acme:form-panel code="authenticated.investmentRound.form.label.workProgramme">
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
	
	<button type="button" formmethod="get" onclick="javascript: redirect('/authenticated/accounting-record/list_mine?id=${param.id}')" class="btn btn-primary">
		<acme:message code="authenticated.investmentRound.form.button.accountingRecords" />
	</button>
	
	<acme:form-submit code="authenticated.investmentRound.form.button.createApplication" action="/investor/application/create?investmentRoundId=${id}" method="get"/>
	
	<acme:form-return code="authenticated.investmentRound.form.button.return" />	
</acme:form> 