
package acme.features.entrepreneur.investmentRounds;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/investment-round/")
public class EntrepreneurInvestmentRoundController extends AbstractController<Entrepreneur, InvestmentRound> {

	@Autowired
	private EntrepreneurInvestmentRoundShowService		showService;

	@Autowired
	private EntrepreneurInvestmentRoundListMineService	listMineService;

	@Autowired
	private EntrepreneurInvestmentRoundCreateService	createService;

	@Autowired
	private EntrepreneurInvestmentRoundUpdateService	updateService;

	@Autowired
	private EntrepreneurInvestmentRoundDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
