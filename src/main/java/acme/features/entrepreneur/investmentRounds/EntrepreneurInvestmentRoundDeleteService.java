
package acme.features.entrepreneur.investmentRounds;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.activities.Activity;
import acme.entities.forums.Forum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.involvedUsers.InvolvedUser;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurInvestmentRoundDeleteService implements AbstractDeleteService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		boolean result;
		int investmentRoundId;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		investmentRoundId = request.getModel().getInteger("id");
		investmentRound = this.repository.findOneInvestmentRoundById(investmentRoundId);
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "reference", "salary", "deadline", "moreInfo", "finalMode", "description", "duties", "text", "tagging");
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;
		InvestmentRound result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneInvestmentRoundById(id);
		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Integer applicationsCount = this.repository.findAllApplicationsByInvestmentRoundId(entity.getId());
		errors.state(request, applicationsCount == 0, "title", "entrepreneur.investmentRound.error.applied");

	}

	@Override
	public void delete(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;
		Collection<Activity> workProgramme = this.repository.findAllActivitiesByInvestmentRoundId(entity.getId());
		Collection<AccountingRecord> accountingRecords = this.repository.findAllAccountingRecordsByInvestmentRoundId(entity.getId());
		Forum forum = this.repository.findForumByInvestmentRoundId(entity.getId());
		Collection<InvolvedUser> iU = this.repository.findById(forum.getId());
		//forum.setInvestmentRound(null);
		this.repository.deleteAll(workProgramme);
		this.repository.deleteAll(accountingRecords);
		this.repository.delete(entity);
		this.repository.deleteAll(iU);
		this.repository.delete(forum);

	}

}
