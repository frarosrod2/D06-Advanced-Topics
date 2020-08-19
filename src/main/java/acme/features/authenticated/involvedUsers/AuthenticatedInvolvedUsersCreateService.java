
package acme.features.authenticated.involvedUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.involvedUsers.InvolvedUser;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedInvolvedUsersCreateService implements AbstractCreateService<Authenticated, InvolvedUser> {

	@Autowired
	private AuthenticatedInvolvedUsersRepository repository;


	@Override
	public boolean authorise(final Request<InvolvedUser> request) {
		assert request != null;

		boolean res = false;
		int forumId = request.getModel().getInteger("forumId");

		Forum forum = this.repository.findForumById(forumId);

		if (forum.getCreator().getId() == request.getPrincipal().getActiveRoleId()) {
			res = true;
		}

		return res;
	}

	@Override
	public void bind(final Request<InvolvedUser> request, final InvolvedUser entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Authenticated user;
		String username = request.getModel().getString("searchUser");
		user = this.repository.findOneAuthenticatedByUsername(username);
		entity.setAuthenticated(user);
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<InvolvedUser> request, final InvolvedUser entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("forumId", request.getModel().getInteger("forumId"));
		request.unbind(entity, model, "authenticated.userAccount.username", "searchUser");

	}

	@Override
	public InvolvedUser instantiate(final Request<InvolvedUser> request) {
		assert request != null;

		InvolvedUser result = new InvolvedUser();
		int forumdId;
		Forum forum;

		forumdId = request.getModel().getInteger("forumId");
		forum = this.repository.findForumById(forumdId);

		result.setForum(forum);
		if (request.getModel().hasAttribute("authenticated.userAccount.username")) {
			String username = request.getModel().getString("authenticated.userAccount.username");
			result.setAuthenticated(this.repository.findOneAuthenticatedByUsername(username));
		}
		return result;
	}

	@Override
	public void validate(final Request<InvolvedUser> request, final InvolvedUser entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("searchUser")) {
			errors.state(request, this.repository.countSearchUser(entity.getSearchUser()) == 1, "searchUser", "authenticated.involvedIn.error.searchUser");

			errors.state(request, this.repository.countSearchUserInInvolvedUser(entity.getSearchUser(), request.getModel().getInteger("forumId")) < 1, "searchUser", "authenticated.involvedIn.error.searchUserInInvolved");
		}
	}

	@Override
	public void create(final Request<InvolvedUser> request, final InvolvedUser entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
