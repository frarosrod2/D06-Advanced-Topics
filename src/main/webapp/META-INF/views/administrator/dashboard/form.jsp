<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %> 
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true">
	<acme:form-panel code="administrator.dashboard.form.label.quantities">
		<div class="row">
			<div class="col-md-4">
				<acme:form-integer code="administrator.dashboard.form.label.numberOfNotices" path="numberOfNotices"/>
			</div>
			<div class="col-md-4">
				<acme:form-integer code="administrator.dashboard.form.label.numberOfTechnologyRecords" path="numberOfTechnologyRecords"/>
			</div>
			<div class="col-md-4">
				<acme:form-integer code="administrator.dashboard.form.label.numberOfToolRecords" path="numberOfToolRecords"/>
			</div>
		</div>
	</acme:form-panel>
	
	<acme:form-panel code="administrator.dashboard.form.label.inquiries">
		<div class="row">
			<div class="col-md-3">
				<acme:form-integer code="administrator.dashboard.form.label.minimumMoneyActiveInquiries" path="minimumMoneyActiveInquiries"/>
			</div>
			<div class="col-md-3">
				<acme:form-integer code="administrator.dashboard.form.label.maximumMoneyActiveInquiries" path="maximumMoneyActiveInquiries"/>
			</div>
			<div class="col-md-3">
				<acme:form-integer code="administrator.dashboard.form.label.averageMoneyActiveInquiries" path="averageMoneyActiveInquiries"/>
			</div>
			<div class="col-md-3">
				<acme:form-integer code="administrator.dashboard.form.label.standardDeviationMoneyActiveInquiries" path="standardDeviationMoneyActiveInquiries"/>
			</div>
		</div>
	</acme:form-panel>

	<acme:form-panel code="administrator.dashboard.form.label.overtures">
		<div class="row">
			<div class="col-md-3">
				<acme:form-integer code="administrator.dashboard.form.label.minimumMoneyActiveOvertures" path="minimumMoneyActiveOvertures"/>
			</div>
			<div class="col-md-3">
				<acme:form-integer code="administrator.dashboard.form.label.maximumMoneyActiveOvertures" path="maximumMoneyActiveOvertures"/>
			</div>
			<div class="col-md-3">
				<acme:form-integer code="administrator.dashboard.form.label.averageMoneyActiveOvertures" path="averageMoneyActiveOvertures"/>
			</div>
			<div class="col-md-3">
				<acme:form-integer code="administrator.dashboard.form.label.standardDeviationMoneyActiveOvertures" path="standardDeviationMoneyActiveOvertures"/>
			</div>
		</div>
	</acme:form-panel>	
	
	<!-- 	 -->	
	
	<acme:form-panel code="administrator.dashboard.form.label.investmentRounds">
		<div class="row">
			<div class="col-md-3">
				<acme:form-integer placeholder="0" code="administrator.dashboard.form.label.averageNumberInvestmentRoundsEntrepreneur" path="averageNumberInvestmentRoundsEntrepreneur"/>
			</div>
		</div>
	</acme:form-panel>
	<acme:form-panel code="administrator.dashboard.form.label.application">
		<div class="row">
			<div class="col-md-3">
				<acme:form-integer placeholder="0" code="administrator.dashboard.form.label.averageNumberApplicationsEntrepreneur" path="averageNumberApplicationsEntrepreneur"/>
			</div>
			<div class="col-md-3">
				<acme:form-integer placeholder="0" code="administrator.dashboard.form.label.averageNumberApplicationsInvestor" path="averageNumberApplicationsInvestor"/>
			</div>
		</div>
	</acme:form-panel>
	
	<!-- 	 -->
	
	<acme:form-panel code="administrator.dashboard.chart.label.technologyRecordsBySectors">
		<div class="row">
			<div class="col-md-6">
				<canvas id="graph1"></canvas>
			</div>
			<div class="col-md-6">
				<canvas id="graph2"></canvas>
			</div>
		</div>	
	</acme:form-panel>
	
	<acme:form-panel code="administrator.dashboard.chart.label.toolRecordsBySectors">
		<div class="row">
			<div class="col-md-6">
				<canvas id="graph3"></canvas>
			</div>
			<div class="col-md-6">
				<canvas id="graph4"></canvas>
			</div>
		</div>	
	</acme:form-panel>
	
	<div class="row">
		<div class="col-md-6">
			<acme:form-panel code="administrator.dashboard.chart.label.ratioOpenVsClosedTechnologies">
				<canvas id="graph5"></canvas>
			</acme:form-panel>
		</div>
		
		<div class="col-md-6">
			<acme:form-panel code="administrator.dashboard.chart.label.ratioOpenVsClosedTools">
				<canvas id="graph6"></canvas>
			</acme:form-panel>
		</div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<acme:form-panel code="administrator.dashboard.chart.label.ratioOfApplicationsGroupedByStatus">
				<canvas id="graph7"></canvas>
			</acme:form-panel>
		</div>
		
		<div class="col-md-6">
			<acme:form-panel code="administrator.dashboard.chart.label.ratioOfInvestmentRoundsGroupedByKind">
				<canvas id="graph8"></canvas>
			</acme:form-panel>
		</div>

		<div class="col-md-6">
			<acme:form-panel code="administrator.dashboard.chart.label.applicationsPerDay">
					<canvas id="graph9"></canvas>
			</acme:form-panel>
		</div>
		
	</div>
</acme:form>

<script type="text/javascript">
	function getRandomColor() {
	    var letters = '0123456789ABCDEF'.split('');
	    var color = '#';
	    for (var i = 0; i < 6; i++ ) {
	        color += letters[Math.floor(Math.random() * 16)];
	    }
	    return color;
	}
	
	function getNRandomColors(num){
		var colors = [];
		var i = 0;
		while (i<num){
			colors.push(getRandomColor());
			i=i+1;
		}
		return colors
	}
	
	function formatDate(date){
	    var dd = date.getDate();
	    var mm = date.getMonth()+1;
	    var yyyy = date.getFullYear();
	    
	    if(dd<10) {
	    	dd='0'+dd;
	    }
	    if(mm<10) {
	    	mm='0'+mm;
	    }
	    
	    return dd+'/'+mm+'/'+yyyy;
	 }

	function getDaysLast15Days () {
	    var result = [];
	    
	    for (var i=0; i<15; i++) {
	        var date = new Date();
	        date.setDate(date.getDate() - i);
	        result.push(formatDate(date))
	    }

	    return result.reverse();
	 }

	$(document).ready(function(){
		
		var technologyRecordsSectors = [
			<c:forEach items="${technologyRecordsSectors}" var="activitySector">"<c:out value='${activitySector}'></c:out>",</c:forEach>
		];
		
		var numberOfTechnologyRecordsBySector = [
			<c:forEach items="${numberOfTechnologyRecordsBySector}" var="number"><c:out value='${number}'></c:out>,</c:forEach>
		];
		
		var toolRecordsSectors = [
			<c:forEach items="${toolRecordsSectors}" var="activitySector">"<c:out value='${activitySector}'></c:out>",</c:forEach>
		];

		var numberOfToolRecordsBySector = [
			<c:forEach items="${numberOfToolRecordsBySector}" var="number"><c:out value='${number}'></c:out>,</c:forEach>
		]
		
		var ratioTechnologiesLabels = [
			"<acme:message code='administrator.dashboard.chart.label.open'/>",
			"<acme:message code='administrator.dashboard.chart.label.closed'/>"
		]
		
		var ratioTechnologiesData = [
			<c:out value='${ratioOpenTechnologies}'></c:out>,
			<c:out value='${ratioClosedTechnologies}'></c:out>
		]
		
		var ratioToolsData = [
			<c:out value='${ratioOpenTools}'></c:out>,
			<c:out value='${ratioClosedTools}'></c:out>
		]
		
		var ratioToolsLabels = [
			"<acme:message code='administrator.dashboard.chart.label.open'/>",
			"<acme:message code='administrator.dashboard.chart.label.closed'/>"
		]
		
		var applicationStatusLabels = [
			"<acme:message code='administrator.dashboard.chart.label.pending'/>",
			"<acme:message code='administrator.dashboard.chart.label.accepted'/>",
			"<acme:message code='administrator.dashboard.chart.label.rejected'/>"
		]
		
		var applicationStatusData = [
			<c:out value='${ratioOfPendingApplications}'></c:out>,
			<c:out value='${ratioOfAcceptedApplications}'></c:out>,
			<c:out value='${ratioOfRejectedApplications}'></c:out>
		]
		
		var investmentRoundKindLabels = [
			"<acme:message code='administrator.dashboard.chart.label.seed'/>",
			"<acme:message code='administrator.dashboard.chart.label.angel'/>",
			"<acme:message code='administrator.dashboard.chart.label.seriesa'/>",
			"<acme:message code='administrator.dashboard.chart.label.seriesb'/>",
			"<acme:message code='administrator.dashboard.chart.label.seriesc'/>",
			"<acme:message code='administrator.dashboard.chart.label.bridge'/>"
		]
		
		var investmentRoundKindData = [
			<c:out value='${ratioOfSeedInvestmentRound}'></c:out>,
			<c:out value='${ratioOfAngelInvestmentRound}'></c:out>,
			<c:out value='${ratioOfSeriesAInvestmentRound}'></c:out>,
			<c:out value='${ratioOfSeriesBInvestmentRound}'></c:out>,
			<c:out value='${ratioOfSeriesCInvestmentRound}'></c:out>,
			<c:out value='${ratioOfBridgeInvestmentRound}'></c:out>
		]

		// D05
		var last15Days = getDaysLast15Days();
		var pendingApplicationsLastDays = {
				<c:forEach items="${pendingApplicationsPerDay}" var="day">
				"${day[1]}": ${day[0]},
				</c:forEach>
		}
		var rejectedApplicationsLastDays = {
				<c:forEach items="${rejectedApplicationsPerDay}" var="day">
				"${day[1]}": ${day[0]},
				</c:forEach>
		}
		var acceptedApplicationsLastDays = {
				<c:forEach items="${acceptedApplicationsPerDay}" var="day">
				"${day[1]}": ${day[0]},
				</c:forEach>
		}
		
		function getTimeseriesData(dataDict, lastDays) {
			var res = [];
			for (var i in lastDays) {
				if (dataDict.hasOwnProperty(lastDays[i])) {
					res.push(dataDict[lastDays[i]]);
				} else {
					res.push(0);
				}
			}
			return res;
		}
		
		
		var barGraph1 = {
				labels: technologyRecordsSectors,
				datasets: [{
					label: "<acme:message code='administrator.dashboard.chart.label.technologyRecords'/>",
					backgroundColor: "rgba(54, 162, 235, 0.5)",
					borderColor: "rgb(54, 162, 235)",
					borderWidth: 1,
					data: numberOfTechnologyRecordsBySector,
				},]
		}
		
		var pieGraph2 = {
				labels: technologyRecordsSectors,
				datasets: [{
					label: "<acme:message code='administrator.dashboard.chart.label.technologyRecords'/>",
					backgroundColor: getNRandomColors(<c:out value='${fn:length(technologyRecordsSectors)}' />),
					borderWidth: 1,
					data: numberOfTechnologyRecordsBySector,
				},]
		}
		
		var barGraph3 = {
				labels: toolRecordsSectors,
				datasets: [{
					label: "<acme:message code='administrator.dashboard.chart.label.toolRecords'/>",
					backgroundColor: "rgba(54, 162, 235, 0.5)",
					borderColor: "rgb(54, 162, 235)",
					borderWidth: 1,
					data: numberOfToolRecordsBySector,
				},]
		}
		
		var pieGraph4 = {
				labels: toolRecordsSectors,
				datasets: [{
					label: "<acme:message code='administrator.dashboard.chart.label.toolRecords'/>",
					backgroundColor: getNRandomColors(<c:out value='${fn:length(toolRecordsSectors)}' />),
					borderWidth: 1,
					data: numberOfToolRecordsBySector,
				},]
		}
		
		var pieGraph5 = {
				labels: ratioTechnologiesLabels,
				datasets: [{
					label: "<acme:message code='administrator.dashboard.chart.label.ratioOpenVsClosedTechnologies'/>",
					backgroundColor: ["#4BC0C0", "#999999"],
					borderWidth: 1,
					data: ratioTechnologiesData,
				},]
		}
		
		var pieGraph6 = {
				labels: ratioToolsLabels,
				datasets: [{
					label: "<acme:message code='administrator.dashboard.chart.label.ratioOpenVsClosedTools'/>",
					backgroundColor: ["#4BC0C0", "#999999"],
					borderWidth: 1,
					data: ratioToolsData,
				},]
		}
		
		var pieGraph7 = {
				labels: applicationStatusLabels,
				datasets: [{
					label: "<acme:message code='administrator.dashboard.chart.label.ratioOfApplicationsGroupedByStatus'/>",
					backgroundColor: ["#FFCC00", "#00CC00", "#CC0000"],
					borderWidth: 1,
					data: applicationStatusData,
				},]
		}
		
		
		
		var pieGraph8 = {
				labels: investmentRoundKindLabels,
				datasets: [{
					label: "<acme:message code='administrator.dashboard.chart.label.ratioOfInvestmentRoundsGroupedByKind'/>",
					backgroundColor: ["#1E90FF", "#FF00FF", "#FFFF00", "#FF0000", "#00FF00", "#2F4F4F"],
					borderWidth: 1,
					data: investmentRoundKindData,
				},]
		}
		
		var barGraph9 = {
				labels: last15Days,
				datasets: [
				{
					label: "Pending",
					backgroundColor: "#FFCD56",
					borderColor: "#FFCD56",
					data: getTimeseriesData(pendingApplicationsLastDays, last15Days),
					fill: false,
					lineTension: 0,  
					
				},
				{
					label: "Accepted",
					backgroundColor: "#4BC0C0",
					borderColor: "#4BC0C0",
					data: getTimeseriesData(acceptedApplicationsLastDays, last15Days),
					fill: false,
					lineTension: 0,
				},
				{
					label: "Rejected",
					backgroundColor: "#FF6384",
					borderColor: "#FF6384",
					data: getTimeseriesData(rejectedApplicationsLastDays, last15Days),
					fill: false,
					lineTension: 0,
				}
			]
		}
			window.onload = function() {
			var ctxGraph1 = document.getElementById('graph1').getContext('2d');
			var ctxGraph2 = document.getElementById('graph2').getContext('2d');
			var ctxGraph3 = document.getElementById('graph3').getContext('2d');
			var ctxGraph4 = document.getElementById('graph4').getContext('2d');
			var ctxGraph5 = document.getElementById('graph5').getContext('2d'); 
			var ctxGraph6 = document.getElementById('graph6').getContext('2d'); 
			var ctxGraph7 = document.getElementById('graph7').getContext('2d'); 
			var ctxGraph8 = document.getElementById('graph8').getContext('2d'); 
			var ctxGraph9 = document.getElementById('graph9').getContext('2d'); 

			window.graph1 = new Chart(ctxGraph1, {
				type: 'bar',
				data: barGraph1,
				options: {
			        scales: {
			            yAxes: [{
			                ticks: {
			                	beginAtZero: true,
			                	stepSize: 1,
			                }
			            }]
			        }
			    }
			});
			
			window.graph2 = new Chart(ctxGraph2, {
				type: 'pie',
				data: pieGraph2,
			});
			
			window.graph3 = new Chart(ctxGraph3, {
				type: 'bar',
				data: barGraph3,
				options: {
			        scales: {
			            yAxes: [{
			                ticks: {
			                	beginAtZero: true,
			                	stepSize: 1,
			                }
			            }]
			        }
			    }
			});
			
			window.graph4 = new Chart(ctxGraph4, {
				type: 'pie',
				data: pieGraph4,
			});
			
			window.graph5 = new Chart(ctxGraph5, {
				type: 'pie',
				data: pieGraph5,
			});
			
			window.graph6 = new Chart(ctxGraph6, {
				type: 'pie',
				data: pieGraph6,
			});
			
			window.graph7 = new Chart(ctxGraph7, {
				type: 'pie',
				data: pieGraph7,
			});
			
			window.graph8 = new Chart(ctxGraph8, {
				type: 'pie',
				data: pieGraph8,
			});
			
			window.graph9 = new Chart(ctxGraph9, {
				type: 'bar',
				data: barGraph9,
				options: {
			        scales: {
			            xAxes: [{
			            	ticks: {
			            	    autoSkip: false,
			            	    stepSize: 1,
			            	}
			            }],
			            yAxes: [{
			            	ticks: {
			            		beginAtZero: true,
			            	    stepSize: 1,
			            	}
			            }]
			        }
			    }
			})


		};
	});
	
</script>

<style>
	.form-control {
	    margin-top: 5px;
	    background-color: #00000026!important;
	    color: white;
	    font-weight: bold;
	    font-size: 2rem;
	    height: auto;
	}
	
	.form-group {
		margin-bottom: 1rem;
	    background: gray;
	    border-radius: 3px;
	    padding: 10px;
	    color: white;
	}
	
	fieldset {
		background-color: #ededed;
		padding: 0 20px;	
	}
</style>