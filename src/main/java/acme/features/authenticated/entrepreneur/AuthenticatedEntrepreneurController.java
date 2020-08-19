
package acme.features.authenticated.entrepreneur;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/entrepreneur/")
public class AuthenticatedEntrepreneurController extends AbstractController<Authenticated, Entrepreneur> {

	@Autowired
	private AuthenticatedEntrepreneurCreateService	createService;

	@Autowired
	private AuthenticatedEntrepreneurUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
