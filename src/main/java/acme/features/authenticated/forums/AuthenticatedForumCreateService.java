
package acme.features.authenticated.forums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.involvedUsers.InvolvedUser;
import acme.entities.messages.Message;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedForumCreateService implements AbstractCreateService<Authenticated, Forum> {

	@Autowired
	private AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");

	}

	@Override
	public Forum instantiate(final Request<Forum> request) {
		assert request != null;

		Date moment;
		Forum result;
		result = new Forum();

		Collection<Message> messages = new ArrayList<Message>();

		int id;
		id = request.getPrincipal().getAccountId();

		moment = new Date(System.currentTimeMillis() - 1);
		Authenticated user = this.repository.getAuthenticatedByAccountId(id);
		result.setMoment(moment);
		result.setCreator(user);
		result.setMessages(messages);
		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		Integer accountId = request.getPrincipal().getAccountId();

		Authenticated currentAuthenticated = this.repository.getAuthenticatedByAccountId(accountId);

		InvolvedUser creator = new InvolvedUser();
		creator.setForum(entity);
		creator.setAuthenticated(currentAuthenticated);

		this.repository.save(entity);
		this.repository.save(creator);
	}

}
