
package acme.features.administrator.inquiries;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiries.Inquiry;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorInquiryCreateService implements AbstractCreateService<Administrator, Inquiry> {

	@Autowired
	AdministratorInquiryRepository repository;


	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "email", "minMoney", "maxMoney");
	}

	@Override
	public Inquiry instantiate(final Request<Inquiry> request) {
		assert request != null;
		Inquiry result = new Inquiry();

		Date creation;

		creation = new Date(System.currentTimeMillis() - 1);
		result.setCreation(creation);

		return result;
	}

	@Override
	public void validate(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 7);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.inquiry.error.deadlineFuture");
		}

		if (!errors.hasErrors("minMoney")) {
			errors.state(request, entity.getMinMoney().getCurrency().equals("EUR") || entity.getMinMoney().getCurrency().equals("€"), "minMoney", "administrator.inquiry.form.error.onlyEuro");
		}

		if (!errors.hasErrors("maxMoney")) {
			errors.state(request, entity.getMaxMoney().getCurrency().equals("EUR") || entity.getMaxMoney().getCurrency().equals("€"), "maxMoney", "administrator.inquiry.form.error.onlyEuro");
		}

		if (!errors.hasErrors("maxMoney")) {
			errors.state(request, entity.getMaxMoney().getAmount() > entity.getMinMoney().getAmount(), "maxMoney", "administrator.inquiry.form.error.maxAndMinError");
		}

	}

	@Override
	public void create(final Request<Inquiry> request, final Inquiry entity) {
		assert request != null;
		assert entity != null;

		Date creation;

		creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);
		this.repository.save(entity);

	}

}
