<%@page language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="${finalMode}">
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.round" path="round"/>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title"/>
	<acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description"/>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.money" path="money"/>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.link" path="link"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-checkbox code="entrepreneur.investmentRound.form.label.finalMode" path="finalMode" />
	</jstl:if>
	
	<jstl:if test="${command != 'create'}">
	<acme:form-panel code="entrepreneur.investmentRound.form.label.workProgramme"/>
		<table class="table table-striped">	
			<tbody>
				<c:forEach items="${workProgramme}" var="activity">
						<tr>
							<td>
								<acme:print value="${activity.title}" />
							</td>
							<td>
								<acme:print value="${activity.budget}" />
							</td>
							<td> 
						<jstl:if  test="${!finalMode}">
							<div class="btn-group">
								<acme:form-return code="entrepreneur.investmentRound.form.button.update-activities" action="/entrepreneur/activity/update?id=${activity.id}"/>
								<acme:form-submit method="post" code="entrepreneur.investmentRound.form.button.delete-activities" action="/entrepreneur/activity/delete?id=${activity.id}"/>
							</div>
						</jstl:if>
					</td>
					</tr>
			</c:forEach>
			</tbody>
		</table>
	</jstl:if>
	
	<button type="button" formmethod="get" onclick="javascript: redirect('/authenticated/accounting-record/list_mine?id=${param.id}')" class="btn btn-primary">
		<acme:message code="entrepreneur.investmentRound.form.button.accountingRecords" />
	</button>

	<jstl:if test="${!finalMode}">
	<jstl:if test="${command != 'create'}">
		<button type="button"
			onclick="javascript: pushReturnUrl('/entrepreneur/investment-round/show?id=${id}'); redirect('/entrepreneur/activity/create?investmentRoundId=${id}')"
			class="btn btn-primary">
			<acme:message code="entrepreneur.investmentRound.form.button.add-activities" />
		</button>
		</jstl:if>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code="entrepreneur.investmentRound.form.button.create"
		action="/entrepreneur/investment-round/create" />

	<acme:form-submit test="${command != 'create' and !finalMode}"
		code="entrepreneur.investmentRound.form.button.update" action="/entrepreneur/investment-round/update" />

	<acme:form-submit test="${command != 'create'}"
		code="entrepreneur.investmentRound.form.button.delete" action="/entrepreneur/investment-round/delete" />
		
	<acme:form-return code="entrepreneur.investmentRound.form.button.return" />	
</acme:form> 