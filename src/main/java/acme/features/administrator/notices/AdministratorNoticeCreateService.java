
package acme.features.administrator.notices;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNoticeCreateService implements AbstractCreateService<Administrator, Notice> {

	@Autowired
	AdministratorNoticeRepository repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "title", "deadline", "body", "optionalLinks");
		model.setAttribute("confirm", false);
	}

	@Override
	public Notice instantiate(final Request<Notice> request) {
		assert request != null;
		Notice result;

		Date creation;
		result = new Notice();
		creation = new Date(System.currentTimeMillis() - 1);
		result.setCreation(creation);

		return result;
	}

	@Override
	public void validate(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Validacion Checkbox

		errors.state(request, request.getModel().hasAttribute("confirm") && request.getModel().getBoolean("confirm"), "confirm", "administrator.message.form.errors.confirm");

	}

	@Override
	public void create(final Request<Notice> request, final Notice entity) {
		assert request != null;
		assert entity != null;

		Date creation;

		creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);
		this.repository.save(entity);

	}

}
