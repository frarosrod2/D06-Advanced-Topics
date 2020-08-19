
package acme.features.investor.applications;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import acme.entities.applications.Application;
import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	private InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		int investmentRoundId = request.getModel().getInteger("investmentRoundId");
		InvestmentRound investmentRound = this.repository.findInvestmentRoundById(investmentRoundId);
		boolean result = investmentRound.isFinalMode();
		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment", "status");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Principal principal = request.getPrincipal();
		model.setAttribute(principal.getActiveRole().getName(), principal);

		request.unbind(entity, model, "ticker", "creationMoment", "statement", "status", "investmentOffer");
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result;
		result = new Application();
		result.setStatus("pending");

		Principal principal = request.getPrincipal();
		int investorId = principal.getActiveRoleId();
		Investor investor = this.repository.findInvestorById(investorId);
		result.setInvestor(investor);

		int investmentRoundId = request.getModel().getInteger("investmentRoundId");
		InvestmentRound investmentRound = this.repository.findInvestmentRoundById(investmentRoundId);
		result.setInvestmentRound(investmentRound);

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String tic = entity.getTicker();

		Collection<String> allTic = new ArrayList<String>();
		Collection<Application> app = this.repository.findMany();

		if (!errors.hasErrors("ticker")) {
			for (Application a : app) {
				allTic.add(a.getTicker());
			}
			for (String s : allTic) {
				if (s.equals(tic)) {
					errors.state(request, false, "ticker", "investor.ticker.error.ticker");
				}
			}
		}
		// Check if money is > 0 and its currency is in EUR
		Money money = entity.getInvestmentOffer();
		if (!errors.hasErrors("investmentOffer")) {
			boolean isPositive = money.getAmount() > 0;
			errors.state(request, isPositive, "investmentOffer", "entrepreneur.investmentRound.error.negative-money");
			boolean isEUR = money.getCurrency().equals("EUR") || money.getCurrency().equals("€");
			errors.state(request, isEUR, "investmentOffer", "entrepreneur.investmentRound.error.money-not-EUR");
		}

		// Check spam

		Customisation customisation = this.repository.findCustomisation();

		String[] spamWords = customisation.getSpamWords().split(", ");
		Double threshold = customisation.getSpamThreshold();
		Boolean isSpamTitle = false;

		int numberWordsTitle;
		int numberSpam;

		if (!errors.hasErrors("statement")) {
			String title = entity.getStatement().toLowerCase();
			numberWordsTitle = title.split("\\W+").length;
			numberSpam = 0;
			for (String s : spamWords) {
				numberSpam += StringUtils.countOccurrencesOf(title, s);
			}
			isSpamTitle = numberWordsTitle > 0 && numberSpam * 100 / numberWordsTitle >= threshold;
			errors.state(request, !isSpamTitle, "statement", "entrepreneur.investmentRound.error.isSpam");
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);

	}

}
