
package acme.features.authenticated.forums;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.activities.Activity;
import acme.entities.applications.Application;
import acme.entities.forums.Forum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.involvedUsers.InvolvedUser;
import acme.entities.messages.Message;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedForumDeleteService implements AbstractDeleteService<Authenticated, Forum> {

	@Autowired
	private AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		boolean result;
		int forumId;
		Forum forum;
		Forum forumEntre;
		Authenticated authenticated;
		Principal principal;

		forumId = request.getModel().getInteger("id");
		forum = this.repository.getForumById(forumId);
		authenticated = forum.getCreator();
		principal = request.getPrincipal();
		forumEntre = this.repository.getForumByEntrepreneur(principal.getActiveRoleId());

		result = authenticated.getUserAccount().getId() == principal.getAccountId() && !forum.equals(forumEntre);

		return result;
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "messages");

	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;

		Forum result;

		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.getForumById(id);

		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		Collection<Message> messages = this.repository.findAllMessagesByForumId(entity.getId());
		Collection<InvolvedUser> iUsers = this.repository.getInvolvedUsersByForum(entity.getId());
		InvestmentRound invest = this.repository.getInventmentRoundByForum(entity.getId());
		Collection<Activity> workProgramme = this.repository.findAllActivitiesByInvestmentRoundId(invest.getId());
		Collection<AccountingRecord> accountingRecords = this.repository.findAllAccountingRecordsByInvestmentRoundId(invest.getId());
		Collection<Application> applications = this.repository.findAllApplicationsByInvestmentRoundId(invest.getId());

		if (invest != null) {
			this.repository.deleteAll(workProgramme);
			this.repository.deleteAll(accountingRecords);
			this.repository.deleteAll(applications);
			this.repository.delete(invest);
		}
		this.repository.deleteAll(iUsers);
		this.repository.deleteAll(messages);
		this.repository.delete(entity);

	}

}
