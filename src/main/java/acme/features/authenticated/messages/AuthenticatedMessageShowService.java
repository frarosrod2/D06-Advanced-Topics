package acme.features.authenticated.messages;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.involvedUsers.InvolvedUser;
import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message>{

	@Autowired
	private AuthenticatedMessageRepository repository;
	
	@Override
	public boolean authorise(Request<Message> request) {
		assert request != null;
		
		boolean res = false;
		
		Message message = this.repository.findOneMessageById(request.getModel().getInteger("id"));
		int forumId = message.getForum().getId();
		Collection<InvolvedUser> iu = this.repository.findInvolvedUserByForumId(forumId);
		Long count = iu.stream().filter(x->x.getAuthenticated().getId() == request.getPrincipal().getActiveRoleId()).count();
		
		if (count != 0) {
			res = true;
		}
		
		return res;
	}

	@Override
	public void unbind(Request<Message> request, Message entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "moment", "tags", "body");
		
	}

	@Override
	public Message findOne(Request<Message> request) {
		assert request != null;
		
		Message message;
		int id;
		
		id = request.getModel().getInteger("id");
		message = this.repository.findOneMessageById(id);
		
		return message;
	}

}
