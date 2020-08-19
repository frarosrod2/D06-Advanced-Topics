
package acme.features.administrator.customisation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.customisations.Customisation;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/customisation/")
public class AdministratorCustomisationController extends AbstractController<Administrator, Customisation> {

	@Autowired
	private AdministratorCustomisationDisplayService	displayService;

	@Autowired
	private AdministratorCustomisationUpdateService		updateService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.DISPLAY, BasicCommand.SHOW, this.displayService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
