
package acme.forms.dashboards;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialization identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ------------------------------------------------------------

	//listing
	Integer						numberOfNotices;
	Integer						numberOfTechnologyRecords;
	Integer						numberOfToolRecords;
	Double						minimumMoneyActiveInquiries;
	Double						maximumMoneyActiveInquiries;
	Double						averageMoneyActiveInquiries;
	Double						standardDeviationMoneyActiveInquiries;
	Double						minimumMoneyActiveOvertures;
	Double						maximumMoneyActiveOvertures;
	Double						averageMoneyActiveOvertures;
	Double						standardDeviationMoneyActiveOvertures;

	Double						averageNumberInvestmentRoundsEntrepreneur;
	Double						averageNumberApplicationsEntrepreneur;
	Double						averageNumberApplicationsInvestor;

	//chart
	Object[]					numberOfTechnologyRecordsBySector;
	Object[]					numberOfToolRecordsBySector;
	Object[]					technologyRecordsSectors;
	Object[]					toolRecordsSectors;

	Double						ratioClosedTechnologies;
	Double						ratioOpenTechnologies;
	Double						ratioClosedTools;
	Double						ratioOpenTools;

	Double						ratioOfSeedInvestmentRound;
	Double						ratioOfAngelInvestmentRound;
	Double						ratioOfSeriesAInvestmentRound;
	Double						ratioOfSeriesBInvestmentRound;
	Double						ratioOfSeriesCInvestmentRound;
	Double						ratioOfBridgeInvestmentRound;
	Double						ratioOfPendingApplications;
	Double						ratioOfAcceptedApplications;
	Double						ratioOfRejectedApplications;

	// D05

	Object[]					pendingApplicationsPerDay;
	Object[]					rejectedApplicationsPerDay;
	Object[]					acceptedApplicationsPerDay;
}
