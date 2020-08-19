
package acme.features.authenticated.technologyRecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/technology-record")
public class AuthenticatedTechnologyRecordController extends AbstractController<Authenticated, TechnologyRecord> {

	@Autowired
	private AuthenticatedTechnologyRecordListService	listService;

	@Autowired
	private AuthenticatedTechnologyRecordShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
