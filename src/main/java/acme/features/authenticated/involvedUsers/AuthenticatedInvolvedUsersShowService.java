
package acme.features.authenticated.involvedUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.involvedUsers.InvolvedUser;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInvolvedUsersShowService implements AbstractShowService<Authenticated, InvolvedUser> {

	@Autowired
	private AuthenticatedInvolvedUsersRepository repository;


	@Override
	public boolean authorise(final Request<InvolvedUser> request) {
		assert request != null;
		int involvedId = request.getModel().getInteger("id");
		InvolvedUser involvedUser = this.repository.findOne(involvedId);
		int forumId = involvedUser.getForum().getId();
		int authenticatedId = request.getPrincipal().getActiveRoleId();
		boolean result = this.repository.findIsForumUser(forumId, authenticatedId);

		return result;
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

		return result;
	}

}
