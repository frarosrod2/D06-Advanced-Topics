
package acme.features.patron.banner.creditCard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/patron/credit-card")
public class PatronCreditCardController extends AbstractController<Patron, CreditCard> {

	@Autowired
	private PatronCreditCardCreateService	createService;

	@Autowired
	private PatronCreditCardUpdateService	updateService;

	@Autowired
	private PatronCreditCardShowService		showService;

	@Autowired
	private PatronCreditCardDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
