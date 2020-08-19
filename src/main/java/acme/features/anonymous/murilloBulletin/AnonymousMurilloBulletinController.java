
package acme.features.anonymous.murilloBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletin.MurilloBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/murillo-bulletin/")
public class AnonymousMurilloBulletinController extends AbstractController<Anonymous, MurilloBulletin> {

	@Autowired
	private AnonymousMurilloBulletinListService		listService;

	@Autowired
	private AnonymousMurilloBulletinCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
