
package acme.features.anonymous.murilloBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.MurilloBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousMurilloBulletinCreateService implements AbstractCreateService<Anonymous, MurilloBulletin> {

	@Autowired
	AnonymousMurilloBulletinRepository repository;


	@Override
	public boolean authorise(final Request<MurilloBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<MurilloBulletin> request, final MurilloBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<MurilloBulletin> request, final MurilloBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "title", "moment", "volumen", "number", "text");
	}

	@Override
	public MurilloBulletin instantiate(final Request<MurilloBulletin> request) {
		assert request != null;

		MurilloBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new MurilloBulletin();
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<MurilloBulletin> request, final MurilloBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<MurilloBulletin> request, final MurilloBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}
}
