
package acme.features.authenticated.creditCard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.creditCards.CreditCard;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/credit-card")
public class AuthenticatedCreditCardController extends AbstractController<Authenticated, CreditCard> {

	@Autowired
	private AuthenticatedCreditCardCreateService	createService;

	@Autowired
	private AuthenticatedCreditCardUpdateService	updateService;

	@Autowired
	private AuthenticatedCreditCardShowService		showService;

	@Autowired
	private AuthenticatedCreditCardDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
