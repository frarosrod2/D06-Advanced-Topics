
package acme.features.authenticated.involvedUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.involvedUsers.InvolvedUser;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedInvolvedUsersDeleteService implements AbstractDeleteService<Authenticated, InvolvedUser> {

	@Autowired
	private AuthenticatedInvolvedUsersRepository repository;


	@Override
	public boolean authorise(final Request<InvolvedUser> request) {
		assert request != null;

		int involvedUserId = request.getModel().getInteger("id");
		InvolvedUser involved = this.repository.findOne(involvedUserId);

		boolean isCreator = involved.getForum().getCreator().getId() == request.getPrincipal().getActiveRoleId();

		return isCreator;
	}

	@Override
	public void bind(final Request<InvolvedUser> request, final InvolvedUser entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvolvedUser> request, final InvolvedUser entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "authenticated.userAccount.username");
	}

	@Override
	public InvolvedUser findOne(final Request<InvolvedUser> request) {
		assert request != null;

		InvolvedUser result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOne(id);
		//		request.unbind(result, request.getModel(), "authenticated.userAccount.username"); // Provisional

		return result;
	}

	@Override
	public void validate(final Request<InvolvedUser> request, final InvolvedUser entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean notCreator = entity.getForum().getCreator().getId() != entity.getAuthenticated().getId();
		errors.state(request, notCreator, "authenticated.userAccount.username", "authenticated.involvedUser.form.error.delete-creator");
	}

	@Override
	public void delete(final Request<InvolvedUser> request, final InvolvedUser entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
