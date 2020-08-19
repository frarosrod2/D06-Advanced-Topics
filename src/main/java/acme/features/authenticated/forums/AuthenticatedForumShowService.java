
package acme.features.authenticated.forums;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedForumShowService implements AbstractShowService<Authenticated, Forum> {

	//Internal state----------------------------------------------

	@Autowired
	AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		Collection<Forum> userForums = this.repository.getInvolvedForums(request.getPrincipal().getActiveRoleId());
		Forum forum = this.repository.getForumById(request.getModel().getInteger("id"));

		return userForums.contains(forum);
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int idForum;
		idForum = request.getModel().getInteger("id");

		Collection<String> involved = this.repository.getInvolvedUsers(idForum);
		model.setAttribute("involved", involved);
		request.unbind(entity, model, "title", "moment", "messages", "creator.userAccount.username", "creator.id");

	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;

		Forum result = null;
		int forumId = request.getModel().getInteger("id");
		result = this.repository.getForumById(forumId);

		return result;
	}
}
