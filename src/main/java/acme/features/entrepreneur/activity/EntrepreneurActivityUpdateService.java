
package acme.features.entrepreneur.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import acme.entities.activities.Activity;
import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.datatypes.Money;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurActivityUpdateService implements AbstractUpdateService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		Integer activityId = request.getModel().getInteger("id");
		InvestmentRound investmentRound = this.repository.findInvestmentRoundByActivityId(activityId);

		return !investmentRound.isFinalMode() && investmentRound.getEntrepreneur().getUserAccount().getId() == request.getPrincipal().getAccountId();
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "start", "end", "budget");
	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		assert request != null;
		Activity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Check if money is > 0 and its currency is in EUR
		Money money = entity.getBudget();
		if (!errors.hasErrors("budget")) {
			boolean isPositive = money.getAmount() > 0;
			errors.state(request, isPositive, "budget", "entrepreneur.investmentRound.error.negative-money");
			boolean isEUR = money.getCurrency().equals("EUR") || money.getCurrency().equals("€");
			errors.state(request, isEUR, "budget", "entrepreneur.investmentRound.error.money-not-EUR");
		}

		// Check spam

		Customisation customisation = this.repository.findCustomisation();

		String[] spamWords = customisation.getSpamWords().split(", ");
		Double threshold = customisation.getSpamThreshold();
		Boolean isSpamTitle = false;

		int numberWordsTitle;
		int numberSpam;

		if (!errors.hasErrors("title")) {
			String title = entity.getTitle().toLowerCase();
			numberWordsTitle = title.split("\\W+").length;
			numberSpam = 0;
			for (String s : spamWords) {
				numberSpam += StringUtils.countOccurrencesOf(title, s);
			}
			isSpamTitle = numberWordsTitle > 0 && numberSpam * 100 / numberWordsTitle >= threshold;
			errors.state(request, !isSpamTitle, "title", "entrepreneur.investmentRound.error.isSpam");
		}

	}

	@Override
	public void update(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Activity> request, final Response<Activity> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
