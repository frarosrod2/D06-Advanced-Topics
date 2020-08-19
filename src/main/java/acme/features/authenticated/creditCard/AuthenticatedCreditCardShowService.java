
package acme.features.authenticated.creditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedCreditCardShowService implements AbstractShowService<Authenticated, CreditCard> {

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
