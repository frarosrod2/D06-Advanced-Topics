
package acme.features.administrator.challenges;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "rookieGoal", "rookieReward", "averageGoal", "averageReward", "expertGoal", "expertReward");
	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		assert request != null;
		Challenge result = new Challenge();
		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean currencyOk, amountOk;
		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 30);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.challenge.error.deadlineFuture");
		}

		if (!errors.hasErrors("rookieReward")) {
			currencyOk = entity.getRookieReward().getCurrency().equals("EUR") || entity.getRookieReward().getCurrency().equals("€");
			errors.state(request, currencyOk, "rookieReward", "administrator.challenge.error.currencyNotEUR");

			amountOk = entity.getRookieReward().getAmount() > 0;
			errors.state(request, amountOk, "rookieReward", "administrator.challenge.error.amount");
		}

		if (!errors.hasErrors("averageReward")) {
			currencyOk = entity.getAverageReward().getCurrency().equals("EUR") || entity.getAverageReward().getCurrency().equals("€");
			errors.state(request, currencyOk, "averageReward", "administrator.challenge.error.currencyNotEUR");

			amountOk = entity.getAverageReward().getAmount() > 0;
			errors.state(request, amountOk, "averageReward", "administrator.challenge.error.amount");
		}
		if (!errors.hasErrors("expertReward")) {
			currencyOk = entity.getExpertReward().getCurrency().equals("EUR") || entity.getExpertReward().getCurrency().equals("€");
			errors.state(request, currencyOk, "expertReward", "administrator.challenge.error.currencyNotEUR");

			amountOk = entity.getExpertReward().getAmount() > 0;
			errors.state(request, amountOk, "expertReward", "administrator.challenge.error.amount");
		}

		if (!errors.hasErrors("rookieGoal")) {
			currencyOk = entity.getRookieGoal().getCurrency().equals("EUR") || entity.getRookieGoal().getCurrency().equals("€");
			errors.state(request, currencyOk, "rookieGoal", "administrator.challenge.error.currencyNotEUR");

			amountOk = entity.getRookieGoal().getAmount() > 0;
			errors.state(request, amountOk, "rookieGoal", "administrator.challenge.error.amount");
		}

		if (!errors.hasErrors("averageGoal")) {
			currencyOk = entity.getAverageGoal().getCurrency().equals("EUR") || entity.getAverageGoal().getCurrency().equals("€");
			errors.state(request, currencyOk, "averageGoal", "administrator.challenge.error.currencyNotEUR");

			amountOk = entity.getAverageGoal().getAmount() > 0;
			errors.state(request, amountOk, "averageGoal", "administrator.challenge.error.amount");
		}
		if (!errors.hasErrors("expertGoal")) {
			currencyOk = entity.getExpertGoal().getCurrency().equals("EUR") || entity.getExpertGoal().getCurrency().equals("€");
			errors.state(request, currencyOk, "expertGoal", "administrator.challenge.error.currencyNotEUR");

			amountOk = entity.getExpertGoal().getAmount() > 0;
			errors.state(request, amountOk, "expertGoal", "administrator.challenge.error.amount");
		}

	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
