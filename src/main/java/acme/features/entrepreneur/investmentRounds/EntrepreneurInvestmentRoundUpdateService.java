
package acme.features.entrepreneur.investmentRounds;

import java.util.Collection;

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
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

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
		request.getModel().setAttribute("workProgramme", entity.getWorkProgramme());

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("workProgramme", entity.getWorkProgramme());

		request.unbind(entity, model, "ticker", "round", "title", "description", "money", "link", "creationMoment", "finalMode", "workProgramme");
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Check if the new reference is duplicated
		InvestmentRound i = this.repository.findOneByTicker(entity.getTicker());
		if (i != null) {
			if (i.getId() != entity.getId()) {
				boolean isDuplicated = true;
				errors.state(request, !isDuplicated, "ticker", "entrepreneur.investmentRound.error.must-be-different-ticker");
			}
		}
		// Check if the investmentRound is in final mode
		InvestmentRound investmentRound;
		investmentRound = this.repository.findOneInvestmentRoundById(entity.getId());
		errors.state(request, !investmentRound.isFinalMode(), "finalMode", "entrepreneur.investmentRound.error.investmentRound-published");

		// Check if salary is > 0 and its currency is in EUR
		Money salary = entity.getMoney();
		if (!errors.hasErrors("money")) {
			boolean isPositive = salary.getAmount() > 0;
			errors.state(request, isPositive, "money", "entrepreneur.investmentRound.error.negative-money");
			boolean isEUR = salary.getCurrency().equals("EUR") || salary.getCurrency().equals("€");
			errors.state(request, isEUR, "money", "entrepreneur.investmentRound.error.money-not-EUR");
		}

		if (!errors.hasErrors("finalMode")) {

			// If you want to publish the investmentRound
			if (request.getModel().getBoolean("finalMode")) {

				// Check if the investmentRound has a description
				boolean hasDescriptor = !request.getModel().getString("description").isEmpty();
				errors.state(request, hasDescriptor, "description", "entrepreneur.investmentRound.error.no-description");

				// Check if the work programme sum up to the total amount of money in the round
				int investmentRoundId = entity.getId();

				Collection<Activity> investmentRoundActivities = this.repository.findAllActivitiesByInvestmentRoundId(investmentRoundId);
				Double sum = 0.;

				for (Activity a : investmentRoundActivities) {
					sum += a.getBudget().getAmount();

				}

				Boolean workProgrammeSum = sum.equals(entity.getMoney().getAmount());
				if (!workProgrammeSum) {
					request.getModel().setAttribute("finalMode", false);
				}
				errors.state(request, workProgrammeSum, "finalMode", "entrepreneur.investmentRound.error.workProgrammeSum");

				// Check spam

				Customisation customisation = this.repository.findCustomisation();

				String[] spamWords = customisation.getSpamWords().split(", ");
				Double threshold = customisation.getSpamThreshold();
				Boolean isSpamTitle = false;
				Boolean isSpamDescription = false;
				Boolean isSpamActivityTitle = false;
				Boolean isSpamTicker = false;
				Boolean isSpamLink = false;

				int numberWordsTitle;
				int numberWordsLink;
				int numberWordsDescription;
				int numberWordsActivityTitle;
				int numberSpam;
				int numberTicker;

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

				if (!errors.hasErrors("ticker")) {
					String ticker = entity.getTicker().toLowerCase();
					numberTicker = ticker.split("\\W+").length;
					numberSpam = 0;
					for (String s : spamWords) {
						numberSpam += StringUtils.countOccurrencesOf(ticker, s);
					}
					isSpamTicker = numberTicker > 0 && numberSpam * 100 / numberTicker >= threshold;
					errors.state(request, !isSpamTicker, "reference", "entrepreneur.investmentRound.error.isSpam");
				}

				if (!errors.hasErrors("description")) {
					String description = entity.getDescription().toLowerCase();
					numberWordsDescription = description.split("\\W+").length;
					numberSpam = 0;
					for (String s : spamWords) {
						numberSpam += StringUtils.countOccurrencesOf(description, s);
					}
					isSpamDescription = numberWordsDescription > 0 && numberSpam * 100 / numberWordsDescription >= threshold;
					errors.state(request, !isSpamDescription, "description", "entrepreneur.investmentRound.error.isSpam");
				}

				if (!errors.hasErrors("workProgramme")) {
					Collection<Activity> workProgramme = this.repository.findAllActivitiesByInvestmentRoundId(entity.getId());
					for (Activity activity : workProgramme) {

						String activityTitle = activity.getTitle().toLowerCase();

						numberWordsActivityTitle = activityTitle.split("\\W+").length;
						numberSpam = 0;
						for (String s : spamWords) {
							numberSpam += StringUtils.countOccurrencesOf(activityTitle, s);
						}

						isSpamActivityTitle = numberWordsActivityTitle > 0 && numberSpam * 100 / numberWordsActivityTitle >= threshold;
						errors.state(request, !isSpamActivityTitle, "activity_title_" + activity.getId(), "entrepreneur.investmentRound.error.isSpam");

					}

				}

				String link = entity.getLink().toLowerCase();
				if (!link.isEmpty()) {
					if (!errors.hasErrors("link")) {
						numberWordsLink = link.split("\\W+").length;
						numberSpam = 0;
						for (String s : spamWords) {
							numberSpam += StringUtils.countOccurrencesOf(link, s);
						}
						isSpamLink = numberWordsLink > 0 && numberSpam * 100 / numberWordsLink >= threshold;
						errors.state(request, !isSpamLink, "link", "entrepreneur.investmentRound.error.isSpam");
					}
				}

			}
		}

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
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
