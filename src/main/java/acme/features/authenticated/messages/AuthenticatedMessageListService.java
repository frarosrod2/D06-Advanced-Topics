package acme.features.authenticated.messages;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.involvedUsers.InvolvedUser;
import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListService implements AbstractListService<Authenticated, Message>{

	@Autowired
	private AuthenticatedMessageRepository repository;
	
	@Override
	public boolean authorise(Request<Message> request) {
		boolean res = false;
		
		int forumId = request.getModel().getInteger("forumId");
		Collection<InvolvedUser> involvedUsers = this.repository.findInvolvedUserByForumId(forumId);
		
		Long count = involvedUsers.stream().filter(x->x.getAuthenticated().getId() == request.getPrincipal().getActiveRoleId()).count();
		
		if (count != 0) {
			res = true;
		}
		return res;
	}

	@Override
	public void unbind(Request<Message> request, Message entity, Model model) {
		request.unbind(entity, model, "title", "moment", "tags", "body", "creator");
		
	}

	@Override
	public Collection<Message> findMany(Request<Message> request) {
		assert request != null;
		
		Collection<Message> result;
		result = this.repository.findMessagesByForumId(request.getModel().getInteger("forumId"));
		
		return result;
	}
	
	

}
