
package acme.features.anonymous.pazosBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.PazosBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousPazosBulletinCreateService implements AbstractCreateService<Anonymous, PazosBulletin> {

	@Autowired
	AnonymousPazosBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PazosBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<PazosBulletin> request, final PazosBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<PazosBulletin> request, final PazosBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "foodHandler", "experience", "contact");
	}

	@Override
	public PazosBulletin instantiate(final Request<PazosBulletin> request) {
		assert request != null;

		PazosBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new PazosBulletin();
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<PazosBulletin> request, final PazosBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<PazosBulletin> request, final PazosBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}
}
