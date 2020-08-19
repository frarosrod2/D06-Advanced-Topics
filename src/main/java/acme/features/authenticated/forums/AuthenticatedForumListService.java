
package acme.features.authenticated.forums;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedForumListService implements AbstractListService<Authenticated, Forum> {

	//Internal state----------------------------------------------

	@Autowired
	AuthenticatedForumRepository repository;


	//AbstractListService<Authenticated, Challenge> interface ------------

	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");
	}

	@Override
	public Collection<Forum> findMany(final Request<Forum> request) {
		assert request != null;

		Collection<Forum> result;

		result = this.repository.getInvolvedForums(request.getPrincipal().getActiveRoleId());

		return result;
	}

}
