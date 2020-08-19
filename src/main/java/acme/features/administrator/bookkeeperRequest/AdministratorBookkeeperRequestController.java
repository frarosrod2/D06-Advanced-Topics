package acme.features.administrator.bookkeeperRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.BookkeeperRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@RequestMapping("/administrator/bookkeeper-request")
@Controller
public class AdministratorBookkeeperRequestController extends AbstractController<Administrator, BookkeeperRequest>{

	@Autowired
	private AdministratorBookkeeperRequestListService listService;
	
	@Autowired
	private AdministratorBookkeeperRequestShowService showService;
	
	@Autowired
	private AdministratorBookkeeperRequestUpdateService updateService;

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, listService);
		super.addBasicCommand(BasicCommand.SHOW, showService);
		super.addBasicCommand(BasicCommand.UPDATE, updateService);
	}
	
	
}
