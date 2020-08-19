
package acme.features.authenticated.inquires;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.inquiries.Inquiry;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/inquiry/")
public class AuthenticatedInquiryController extends AbstractController<Authenticated, Inquiry> {

	@Autowired
	private AuthenticatedInquiryListService	listService;

	@Autowired
	private AuthenticatedInquiryShowService			showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
