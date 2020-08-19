
package acme.features.patron.banner.creditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class PatronCreditCardShowService implements AbstractShowService<Patron, CreditCard> {

	@Autowired
	PatronCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;
		boolean result;
		Patron patron;
		Principal principal;

		int ccId = request.getModel().getInteger("creditCard");
		patron = this.repository.findOnePatronByCCId(ccId);

		principal = request.getPrincipal();
		result = patron.getUserAccount().getId() == principal.getAccountId();

		return result;
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

}
