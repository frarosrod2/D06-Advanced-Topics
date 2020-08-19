
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.dashboards.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfNotices", "numberOfTechnologyRecords", "numberOfToolRecords", "minimumMoneyActiveInquiries", "maximumMoneyActiveInquiries", "averageMoneyActiveInquiries", "standardDeviationMoneyActiveInquiries",
			"minimumMoneyActiveOvertures", "maximumMoneyActiveOvertures", "averageMoneyActiveOvertures", "standardDeviationMoneyActiveOvertures", "numberOfTechnologyRecordsBySector", "numberOfToolRecordsBySector", "technologyRecordsSectors",
			"toolRecordsSectors", "ratioOpenTechnologies", "ratioClosedTechnologies", "ratioOpenTools", "ratioClosedTools", "averageNumberInvestmentRoundsEntrepreneur", "averageNumberApplicationsEntrepreneur", "averageNumberApplicationsInvestor",
			"ratioOfSeedInvestmentRound", "ratioOfAngelInvestmentRound", "ratioOfSeriesAInvestmentRound", "ratioOfSeriesBInvestmentRound", "ratioOfSeriesCInvestmentRound", "ratioOfBridgeInvestmentRound", "ratioOfPendingApplications",
			"ratioOfAcceptedApplications", "ratioOfRejectedApplications", "pendingApplicationsPerDay", "rejectedApplicationsPerDay", "acceptedApplicationsPerDay");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result = new Dashboard();

		result.setNumberOfNotices(this.repository.getNumberOfNotices());
		result.setNumberOfTechnologyRecords(this.repository.getNumberOfTechnologyRecords());
		result.setNumberOfToolRecords(this.repository.getNumberOfToolRecords());

		result.setMinimumMoneyActiveInquiries((double) this.repository.getMinimumMoneyActiveInquiries()[0]);
		result.setMaximumMoneyActiveInquiries((double) this.repository.getMaxMoneyActiveInquiries()[0]);
		result.setAverageMoneyActiveInquiries((double) this.repository.getAverageMoneyActiveInquiries()[0]);
		result.setStandardDeviationMoneyActiveInquiries((double) this.repository.getStandardDeviationMoneyActiveInquiries()[0]);

		result.setMinimumMoneyActiveOvertures((double) this.repository.getMinimumMoneyActiveOvertures()[0]);
		result.setMaximumMoneyActiveOvertures((double) this.repository.getMaxMoneyActiveOvertures()[0]);
		result.setAverageMoneyActiveOvertures((double) this.repository.getAverageMoneyActiveOvertures()[0]);
		result.setStandardDeviationMoneyActiveOvertures((double) this.repository.getStandardDeviationMoneyActiveOvertures()[0]);

		result.setAverageNumberInvestmentRoundsEntrepreneur(this.repository.getAverageNumberInvestmentRoundsEntrepreneur());
		result.setAverageNumberApplicationsEntrepreneur(this.repository.getAverageNumberApplicationsEntrepreneur());
		result.setAverageNumberApplicationsInvestor(this.repository.getAverageNumberApplicationsInvestor());

		result.setNumberOfTechnologyRecordsBySector(this.repository.getNumberOfTechnologyRecordsBySector());
		result.setNumberOfToolRecordsBySector(this.repository.getNumberOfToolRecordsBySector());
		result.setTechnologyRecordsSectors(this.repository.getTechnologyRecordsSectors());
		result.setToolRecordsSectors(this.repository.getToolRecordsSectors());

		result.setRatioOpenTechnologies(this.repository.getRatioOpenTechnologies());
		result.setRatioOpenTools(this.repository.getRatioOpenTools());
		result.setRatioClosedTechnologies(this.repository.getRatioClosedTechnologies());
		result.setRatioClosedTools(this.repository.getRatioClosedTools());

		result.setRatioOfSeedInvestmentRound(this.repository.getRatioOfSeedInvestmentRound());
		result.setRatioOfAngelInvestmentRound(this.repository.getRatioOfAngelInvestmentRound());
		result.setRatioOfSeriesAInvestmentRound(this.repository.getRatioOfSeriesAInvestmentRound());
		result.setRatioOfSeriesBInvestmentRound(this.repository.getRatioOfSeriesBInvestmentRound());
		result.setRatioOfSeriesCInvestmentRound(this.repository.getRatioOfSeriesCInvestmentRound());
		result.setRatioOfBridgeInvestmentRound(this.repository.getRatioOfBridgeInvestmentRound());
		result.setRatioOfPendingApplications(this.repository.getRatioOfPendingApplications());
		result.setRatioOfAcceptedApplications(this.repository.getRatioOfAcceptedApplications());
		result.setRatioOfRejectedApplications(this.repository.getRatioOfRejectedApplications());

		result.setPendingApplicationsPerDay(this.repository.getPendingApplicationsPerDay());
		result.setAcceptedApplicationsPerDay(this.repository.getRejectedApplicationsPerDay());
		result.setAcceptedApplicationsPerDay(this.repository.getAcceptedApplicationsPerDay());

		return result;
	}

}
