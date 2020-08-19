package acme.features.bookkeeper.accountingRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.customisations.Customisation;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class BookkeeperAccountingRecordUpdateService implements AbstractUpdateService<Bookkeeper, AccountingRecord>{

	@Autowired
	private BookkeeperAccountingRecordRepository repository;
	
	@Override
	public boolean authorise(Request<AccountingRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<AccountingRecord> request, AccountingRecord entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(Request<AccountingRecord> request, AccountingRecord entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("investmentRoundId", request.getModel().getInteger("investmentRoundId"));
		request.unbind(entity, model, "title", "creationMoment", "status", "body");
		
	}

	@Override
	public AccountingRecord findOne(Request<AccountingRecord> request) {
		assert request != null;
		
		AccountingRecord result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		
		return result;
	}

	@Override
	public void validate(Request<AccountingRecord> request, AccountingRecord entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Check spam

		Customisation customisation = this.repository.findCustomisation();

		String[] spamWords = customisation.getSpamWords().split(", ");
		Double threshold = customisation.getSpamThreshold();
		Boolean isSpamTitle = false;
		Boolean isSpamBody = false;

		int numberWordsTitle;
		int numberSpam;
		int numberBody;

		if (!errors.hasErrors("title")) {
			String title = entity.getTitle().toLowerCase();
			numberWordsTitle = title.split("\\W+").length;
			numberSpam = 0;
			for (String s : spamWords) {
				numberSpam += StringUtils.countOccurrencesOf(title, s);
			}
			isSpamTitle = numberWordsTitle > 0 && numberSpam * 100 / numberWordsTitle >= threshold;
			errors.state(request, !isSpamTitle, "title", "entrepreneur.investmentRound.error.isSpam");
		}

		if (!errors.hasErrors("body")) {
			String ticker = entity.getBody().toLowerCase();
			numberBody = ticker.split("\\W+").length;
			numberSpam = 0;
			for (String s : spamWords) {
				numberSpam += StringUtils.countOccurrencesOf(ticker, s);
			}
			isSpamBody = numberBody > 0 && numberSpam * 100 / numberBody >= threshold;
			errors.state(request, !isSpamBody, "body", "entrepreneur.investmentRound.error.isSpam");
		}

	}

	@Override
	public void update(Request<AccountingRecord> request, AccountingRecord entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	
	

}
