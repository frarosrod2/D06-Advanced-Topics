
package acme.features.entrepreneur.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import acme.entities.activities.Activity;
import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurActivityCreateService implements AbstractCreateService<Entrepreneur, Activity> {

	@Autowired
	private EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		int investmentRoundId = request.getModel().getInteger("investmentRoundId");
		Principal principal = request.getPrincipal();
		InvestmentRound investmentRound = this.repository.findOneInvestmentRoundById(investmentRoundId);
		return !investmentRound.isFinalMode() && investmentRound.getEntrepreneur().getUserAccount().getId() == principal.getAccountId();
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
	public Activity instantiate(final Request<Activity> request) {
		assert request != null;
		Activity activity;
		activity = new Activity();
		return activity;
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
	public void create(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		int investmentRoundId = request.getModel().getInteger("investmentRoundId");
		InvestmentRound investmentRound = this.repository.findOneInvestmentRoundById(investmentRoundId);

		Activity createdActivity = this.repository.save(entity);
		investmentRound.getWorkProgramme().add(createdActivity);
	}

}
