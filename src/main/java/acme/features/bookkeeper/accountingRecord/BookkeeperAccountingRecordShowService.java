
package acme.features.bookkeeper.accountingRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class BookkeeperAccountingRecordShowService implements AbstractShowService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;
		boolean result;
		int id;

		AccountingRecord accounting;
		Bookkeeper bookkeeper;
		Principal principal;
		id = request.getModel().getInteger("id");
		accounting = this.repository.findOneById(id);
		bookkeeper = accounting.getBookkeeper();
		principal = request.getPrincipal();
		result = accounting.getStatus().equals("PUBLISHED") || !accounting.getStatus().equals("PUBLISHED") && bookkeeper.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int accountingRecorddId = request.getModel().getInteger("id");
		AccountingRecord auc = this.repository.findOneById(accountingRecorddId);
		int propietarioId = auc.getBookkeeper().getUserAccount().getId();
		Principal principal = request.getPrincipal();
		int myId = principal.getAccountId();
		boolean hasAccess = propietarioId != myId;
		model.setAttribute("hasAccess", hasAccess);

		request.unbind(entity, model, "title", "status", "creationMoment", "body");
	}

	@Override
	public AccountingRecord findOne(final Request<AccountingRecord> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		AccountingRecord result = this.repository.findOneById(id);
		return result;
	}

}
