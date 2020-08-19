
package acme.features.authenticated.creditCard;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedCreditCardUpdateService implements AbstractUpdateService<Authenticated, CreditCard> {

	@Autowired
	AuthenticatedCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		int ccId = request.getModel().getInteger("creditCard");
		Patron patron = this.repository.findPatronByCCId(ccId);

		return patron.getUserAccount().getId() == request.getPrincipal().getAccountId();
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "patronId");
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holderName", "number", "brand", "expMonth", "cvv", "expYear");

		int cardId = request.getModel().getInteger("creditCard");

		model.setAttribute("creditCard", cardId);
	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		int id;

		id = request.getModel().getInteger("creditCard");
		result = this.repository.findOneCCById(id);

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Check if credit card is in past
		Calendar calendar;
		Date present;
		Calendar test;
		Date check;

		// Check if year is in past
		if (!errors.hasErrors("expYear")) {
			calendar = new GregorianCalendar();
			test = new GregorianCalendar();
			present = calendar.getTime();
			test.set(2000 + entity.getExpYear(), entity.getExpMonth(), 1);
			check = test.getTime();
			boolean isExpiredYear = check.after(present);
			// Check if year is current year
			if (!isExpiredYear) {
				calendar.set(Calendar.MONTH, entity.getExpMonth());
				calendar.set(Calendar.DATE, 1);
				Date testing = calendar.getTime();
				if (testing.equals(check)) {
					// Check if month is in past
					if (!errors.hasErrors("expMonth")) {
						calendar = new GregorianCalendar();
						test = new GregorianCalendar();
						present = calendar.getTime();
						test.set(2000 + entity.getExpYear(), entity.getExpMonth(), 1);
						check = test.getTime();
						boolean isExpiredMonth = check.after(present);
						errors.state(request, isExpiredMonth, "expMonth", "authenticated.creditCard.error.past-month");
					}
				} else {
					errors.state(request, isExpiredYear, "expYear", "authenticated.creditCard.error.past-year");
				}
			}
		}

	}

	@Override
	public void update(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
