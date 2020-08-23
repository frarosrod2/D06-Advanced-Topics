
package acme.features.entrepreneur.investmentRounds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.activities.Activity;
import acme.entities.applications.Application;
import acme.entities.forums.Forum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.involvedUsers.InvolvedUser;
import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.features.authenticated.forums.AuthenticatedForumRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, InvestmentRound> {

	@Autowired
	private EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	private AuthenticatedForumRepository			forumRepository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		return true;
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

		request.unbind(entity, model, "ticker", "round", "title", "description", "money", "link");

	}

	@Override
	public InvestmentRound instantiate(final Request<InvestmentRound> request) {
		InvestmentRound result;
		Entrepreneur entrepreneur;

		Collection<Activity> workProgramme = new ArrayList<Activity>();
		Collection<Application> applications = new ArrayList<Application>();
		Collection<AccountingRecord> accountingRecords = new ArrayList<AccountingRecord>();
		result = new InvestmentRound();
		entrepreneur = this.repository.findOneEntrepreneurById(request.getPrincipal().getActiveRoleId());
		result.setEntrepreneur(entrepreneur);
		result.setFinalMode(false);
		Date creation;
		creation = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(creation);
		result.setWorkProgramme(workProgramme);
		result.setApplications(applications);
		result.setAccountingRecords(accountingRecords);

		Forum forum = new Forum();
		Collection<Message> messages = new ArrayList<Message>();
		forum.setMessages(messages);

		//Create forum
		forum.setTitle("Temporal");
		forum.setMoment(creation);
		//Add User
		forum.setCreator(this.repository.getAuthenticatedByAccountId(request.getPrincipal().getAccountId()));
		result.setForum(forum);

		InvolvedUser i = new InvolvedUser();
		i.setForum(forum);
		i.setAuthenticated(this.repository.getAuthenticatedByAccountId(request.getPrincipal().getAccountId()));

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Check if the new reference is duplicated
		boolean isDuplicated = this.repository.findOneByTicker(entity.getTicker()) != null;
		errors.state(request, !isDuplicated, "ticker", "entrepreneur.investmentRound.error.must-be-different-ticker");

		// Check if salary is > 0 and its currency is in EUR
		Money salary = entity.getMoney();
		if (!errors.hasErrors("money")) {
			boolean isPositive = salary.getAmount() > 0;
			errors.state(request, isPositive, "money", "entrepreneur.investmentRound.error.negative-money");
			boolean isEUR = salary.getCurrency().equals("EUR") || salary.getCurrency().equals("€");
			errors.state(request, isEUR, "money", "entrepreneur.investmentRound.error.money-not-EUR");
		}

	}

	@Override
	public void create(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;
		Entrepreneur entrepreneur = this.repository.findOneEntrepreneurById(request.getPrincipal().getActiveRoleId());
		entity.setEntrepreneur(entrepreneur);

		//Create forum
		Forum forum = entity.getForum();
		forum.setTitle(entity.getTitle());
		Date creation;
		creation = new Date(System.currentTimeMillis() - 1);
		forum.setMoment(creation);
		//forum.setInvestmentRound(entity);
		//Add Users
		forum.setCreator(this.repository.getAuthenticatedByAccountId(request.getPrincipal().getAccountId()));
		InvolvedUser i = new InvolvedUser();
		i.setForum(forum);
		i.setAuthenticated(this.repository.getAuthenticatedByAccountId(request.getPrincipal().getAccountId()));
		this.forumRepository.save(forum);
		this.repository.save(i);

		entity.setForum(forum);

		this.repository.save(entity);
	}
}
