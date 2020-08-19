
package acme.features.anonymous.pazosBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletin.PazosBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/pazos-bulletin/")
public class AnonymousPazosBulletinController extends AbstractController<Anonymous, PazosBulletin> {

	@Autowired
	private AnonymousPazosBulletinListService	listService;

	@Autowired
	private AnonymousPazosBulletinCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
